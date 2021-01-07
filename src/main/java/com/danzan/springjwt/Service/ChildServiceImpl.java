package com.danzan.springjwt.Service;

import com.danzan.springjwt.models.Child;
import com.danzan.springjwt.repository.ChildRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;

    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    // выдать только определенную карту
    @Override
    public Child findChildById(@PathVariable Integer id) {
        return childRepository.findById(id).get();
    }

    // выдать все карты
    @Override
    public List<Child> findAllChild() {
        return childRepository.findAll();
    }

    // сохранение карты
    @Override
    public Child saveChild(Child child) {
        return childRepository.save(child);
    }

    // удаление карты
    @Override
    public void deleteById(@PathVariable Integer id) {
        childRepository.deleteById(id);
    }

}
