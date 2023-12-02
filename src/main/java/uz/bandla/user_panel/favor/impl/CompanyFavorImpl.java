package uz.bandla.user_panel.favor.impl;

import uz.bandla.annotations.Favor;
import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.user_panel.favor.CompanyFavor;
import uz.bandla.repository.CompanyRepository;

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
}