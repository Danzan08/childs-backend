package com.danzan.springjwt.Childs.Service;

import com.danzan.springjwt.Childs.models.Child;

import java.util.List;

public interface ChildService {
    Child findChildById(Integer id);
    List<Child> findAllChild();

    Child saveChild(Child child);

    void deleteById(Integer id);
}
