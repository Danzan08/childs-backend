package com.danzan.springjwt.Service;

import com.danzan.springjwt.models.Organization;

import java.util.List;

public interface OrgService {
    Organization findOrgById(Integer id);
    List<Organization> findAllOrg();

    Organization saveOrg(Organization organization);

    void deleteById(Integer id);
}
