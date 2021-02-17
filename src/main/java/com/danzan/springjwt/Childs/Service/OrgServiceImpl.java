package com.danzan.springjwt.Childs.Service;

import com.danzan.springjwt.Childs.models.Organization;
import com.danzan.springjwt.Childs.repository.OrgRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {

    private final OrgRepository orgRepository;

    public OrgServiceImpl(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }


    @Override
    public Organization findOrgById(Integer id) {
        return orgRepository.findById(id).get();
    }

    @Override
    public List<Organization> findAllOrg() {
        return orgRepository.findAll();
    }

    @Override
    public Organization saveOrg(Organization organization) {
        return orgRepository.save(organization);
    }

    @Override
    public void deleteById(Integer id) {
        orgRepository.deleteById(id);
    }
}
