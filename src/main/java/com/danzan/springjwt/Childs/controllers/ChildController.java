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
@RequestMapping(path = "/api")
public class ChildController {

    @Autowired
    ChildRepository childRepository;

    @GetMapping("/childs/{id}")
    public ResponseEntity<Child> getTutorialById(@PathVariable("id") Integer id) {
        Optional<Child> childData = childRepository.findById(id);

        if (childData.isPresent()) {
            return new ResponseEntity<>(childData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Если у пользователя нет админских или модераторских прав, то он видит только карточки своего учреждения.
    @GetMapping("/childs")
    public ResponseEntity<Iterable<Child>> getAllChilds(@RequestParam(required = false) String surName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String getRole = auth.getAuthorities().toString();
        if (getRole.equals("[ROLE_ADMIN]") || getRole.equals("[ROLE_MODERATOR]")) {
            return ResponseEntity.ok(childRepository.findAll());
        } else {
            Object principal = auth.getPrincipal();
            String username = null;
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            return ResponseEntity.ok(childRepository.findAllNative(username));
        }
    }

    // Список снилсов для валидации в форме, на существование снилса при создании ребенка.
    @GetMapping("/snils")
    public ResponseEntity<List<Object>> getAllChilds() {
        return ResponseEntity.ok(childRepository.snilsList());
    }



    @DeleteMapping("/childs/{id}")
    public ResponseEntity<Child> deleteChild(@PathVariable Integer id) {
        childRepository.deleteById(id);
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
