package com.danzan.springjwt.Childs.controllers;

import com.danzan.springjwt.Childs.models.Child;
import com.danzan.springjwt.Childs.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChildController {

    final String admin = "[ROLE_ADMIN]";
    final String user = "[ROLE_USER]";
    final String moderator = "[ROLE_MODERATOR]";

    @Autowired
    ChildRepository childRepository;

    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    private String getUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().toString();
    }

    // Если у пользователя нет админских или модераторских прав, то он видит только карточки своего учреждения.
    @GetMapping("/childs")
    public ResponseEntity<Iterable<Child>> getAllChilds(@RequestParam(required = false) String search) {
        if (getUserRole().equals(admin) || getUserRole().equals(user)) {
            return ResponseEntity.ok(childRepository.findAll());
        } else {
            return ResponseEntity.ok(childRepository.findAllWithParams(getUserName()));
        }
    }


    @GetMapping("/childs/{id}")
    public ResponseEntity<Child> getTutorialById(@PathVariable("id") Integer id) {
        Optional<Child> childData = null;
        if (getUserRole().equals(admin) || getUserRole().equals(moderator)) {
            childData = childRepository.findById(id);
        } else {
            childData = childRepository.findByIdWithParam(id, getUserName());
        }
        if (childData.isPresent()) {
            return new ResponseEntity<>(childData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // Список снилсов для валидации в форме, на существование снилса при создании ребенка.
    @GetMapping("/snils")
    public ResponseEntity<List<Object>> getAllChilds() {
        return ResponseEntity.ok(childRepository.snilsList());
    }


    /*Данный контроллер не удаляет карточку,а всего лишь переводит в статус не активный.
    В будущем пригодится для архива данных.*/
    @DeleteMapping("/childs/{id}")
    public ResponseEntity<Child> deleteChild(@PathVariable Integer id) {
        Optional<Child> child = childRepository.findById(id);
        if (!child.isPresent())
            return ResponseEntity.notFound().build();

        Child result = child.get();
        result.setActive(false);
        childRepository.save(result);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/childs/{id}")
    public ResponseEntity<Child> setDescription(@PathVariable Integer id, @RequestBody Child veteranDetails) {
        Optional<Child> child = childRepository.findById(id);
        if (!child.isPresent())
            return ResponseEntity.notFound().build();

        Child result = child.get();
        // result.setDescription(veteranDetails.getDescription());
        childRepository.save(result);
        return ResponseEntity.ok().build();

    }

    @PostMapping(value = "/childs", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Child addChild(@RequestBody Child child) {

        return childRepository.save(child);

    }


}
