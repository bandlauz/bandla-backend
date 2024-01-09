package uz.bandla.sysadmin_panel.service;

import org.springframework.http.ResponseEntity;
import uz.bandla.dto.Response;
import uz.bandla.dto.company.response.CompanyDTO;

import java.util.List;

public interface CompanyService {
    ResponseEntity<Response<List<CompanyDTO>>> getList();
}
