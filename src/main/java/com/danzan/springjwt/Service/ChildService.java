package com.danzan.springjwt.Service;

import com.danzan.springjwt.models.Child;

import java.util.List;

public interface ChildService {
    Child findChildById(Integer id);
    List<Child> findAllChild();

    Child saveChild(Child child);

    void deleteById(Integer id);
}
