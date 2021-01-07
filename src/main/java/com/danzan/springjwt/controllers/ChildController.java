package com.danzan.springjwt.controllers;

import com.danzan.springjwt.models.Child;
import com.danzan.springjwt.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api")
public class ChildController {
    @Autowired
    ChildRepository childRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping("/sortedchilds")
    public ResponseEntity<List<Child>> getAllChilds(@RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Child> childs = childRepository.findAll(Sort.by(orders));

            if (childs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(childs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/childs")
    public ResponseEntity<Map<String, Object>> getAllTutorialsPage(
            @RequestParam(required = false) String child,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Child> childs = new ArrayList<Child>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Child> pageChild;
            if (child == null) {
                // Если в значении поиска ничего нет, выпаливаем весь список
                pageChild = childRepository.findAll(pagingSort);
            } else {
                // Сплитим по пробелу значение запроса по ФИО
                String[] childSplit = child.split("\\s");
                switch (childSplit.length) {
                    case 1:
                        pageChild = childRepository.findBySurNameContaining(child, pagingSort);
                        break;
                    case 2:
                        pageChild = childRepository.findBySurNameAndFirstNameContaining(childSplit[0], childSplit[1], pagingSort);
                        break;
                    case 3:
                        pageChild = childRepository.findBySurNameAndFirstNameAndPatronymicContaining(childSplit[0], childSplit[1], childSplit[2], pagingSort);
                        break;
                    default:
                        pageChild = childRepository.findAll(pagingSort);
                        break;
                }
            }
            childs = pageChild.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("childs", childs);
            response.put("currentPage", pageChild.getNumber());
            response.put("totalItems", pageChild.getTotalElements());
            response.put("totalPages", pageChild.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/childs/isActive")
    public ResponseEntity<Map<String, Object>> findByPublished(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        try {
            List<Child> childs = new ArrayList<Child>();
            Pageable paging = PageRequest.of(page, size);

            Page<Child> pageChild = childRepository.findByIsActive(true, paging);
            childs = pageChild.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("childs", childs);
            response.put("currentPage", pageChild.getNumber());
            response.put("totalItems", pageChild.getTotalElements());
            response.put("totalPages", pageChild.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/childs/{id}")
    public ResponseEntity<Child> getTutorialById(@PathVariable("id") Integer id) {
        Optional<Child> childData = childRepository.findById(id);

        if (childData.isPresent()) {
            return new ResponseEntity<>(childData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
