/*
* Контроллер авторизации и регистрации пользователя
*/

package com.danzan.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.danzan.springjwt.Service.UserAuthorityImpl;
import com.danzan.springjwt.models.ERole;
import com.danzan.springjwt.models.Role;
import com.danzan.springjwt.models.User;
import com.danzan.springjwt.payload.request.LoginRequest;
import com.danzan.springjwt.payload.request.SignupRequest;
import com.danzan.springjwt.payload.response.JwtResponse;
import com.danzan.springjwt.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danzan.springjwt.repository.RoleRepository;
import com.danzan.springjwt.repository.UserRepository;
import com.danzan.springjwt.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserAuthorityImpl userDetails = (UserAuthorityImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		/*
		Вместе с токеном выдаем информацию о пользователе, это важный параметр от него в Ангуляре
		будем отталкиваться по правам read or write в карточках
		 */
		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));

	}

	@PostMapping("/registration")
	@PreAuthorize("hasRole('ADMIN')")

	// Перед созданием проверяем на уникальность username и e-mail, если все ок идем дальше
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Ошибка: пользователь с таким никнеймом уже зарегистрирован в системе"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Ошибка: данный почтовый ящик уже зарегистрирован"));
		}

		// Создаем пользователя
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 signUpRequest.getOrganization(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Ошибка: такой роли нет."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Ошибка: такой роли нет."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Ошибка: такой роли нет."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Ошибка: такой роли нет."));
					roles.add(userRole);
				}
			});
		}

		user.setUserRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse(user.getUsername() + " успешно зарегистрирован в системе"));
	}
}
