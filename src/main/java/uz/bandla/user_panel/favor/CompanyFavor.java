package uz.bandla.user_panel.favor;

import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;

public interface CompanyFavor {
    void save(CompanyEntity entity);

    boolean hasCompany(ProfileEntity profile);
}