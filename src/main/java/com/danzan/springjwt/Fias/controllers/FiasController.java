package com.danzan.springjwt.Fias.controllers;

import com.danzan.springjwt.Fias.model.Fias;
import com.danzan.springjwt.Fias.repository.FiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/fias")
public class FiasController {
    @Autowired
    FiasRepository fiasRepository;


    /*Берем город или названия районов*/
    @GetMapping("/{aoguid}")
    public List<Fias> searchByid(@PathVariable("aoguid") UUID aoguid) {
        List<Fias> fiasData = fiasRepository.findCityOrRayonNative(aoguid);
            return fiasData;
    }

    @GetMapping("/")
    public List<Fias> getAll() {
        List<Fias> fiasData = fiasRepository.findAll();
        return fiasData;
    }


}
