package com.danzan.springjwt.Fias.controllers;

import com.danzan.springjwt.Fias.model.Fias;
import com.danzan.springjwt.Fias.repository.FiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/fias")
public class FiasController {
    @Autowired
    FiasRepository fiasRepository;


    /*Берем город или названия районов*/
    @GetMapping("/regions")
    public List<Fias> searchRegions() {

        return fiasRepository.findRegionsNative();
    }

        //*Берем город или названия районов*//*
    @GetMapping("/cities/{parentguid}")
    public List<Fias> searchCities(@PathVariable("parentguid") UUID parentguid) {

        return fiasRepository.findCitiesNative(parentguid);
    }

    //*Поиск по району, если город то минуем*//*
    @GetMapping("/rayons/{parentguid}")
    public List<Fias> searchRayons(@PathVariable("parentguid") UUID parentguid) {

        return fiasRepository.findRayonsNative(parentguid);
    }


    //*Поиск по улице*//*
    @GetMapping("/streets/{parentguid}")
    public List<Fias> searchStreets(@PathVariable("parentguid") UUID parentguid) {

        return fiasRepository.findStreetsNative(parentguid);
    }




    @GetMapping("/")
    public List<Fias> getAll() {
        List<Fias> fiasData = fiasRepository.findAll();
        return fiasData;
    }


}
