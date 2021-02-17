package com.danzan.springjwt.Childs.Service;

import com.danzan.springjwt.Childs.models.Organization;

import java.util.List;

public interface OrgService {
    Organization findOrgById(Integer id);
    List<Organization> findAllOrg();

    Organization saveOrg(Organization organization);

    void deleteById(Integer id);
}
