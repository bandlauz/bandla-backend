package uz.bandla.favor.impl;

import uz.bandla.annotations.Favor;
import uz.bandla.dto.company.response.CompanyDTO;
import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.repository.CompanyRepository;
import uz.bandla.favor.CompanyFavor;

import java.util.List;

@Favor
public class CompanyFavorImpl implements CompanyFavor {

    private final CompanyRepository repository;

    public CompanyFavorImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(CompanyEntity entity) {
        repository.save(entity);
    }

    @Override
    public boolean hasCompany(ProfileEntity profile) {
        return repository.existsByAdmin(profile);
    }

    @Override
    public List<CompanyDTO> getList() {
        return repository.getShortInfoList();
    }
}