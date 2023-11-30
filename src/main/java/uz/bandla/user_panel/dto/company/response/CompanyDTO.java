package uz.bandla.user_panel.dto.company.response;

import uz.bandla.enums.CompanyStatus;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CompanyDTO {
    private String name;
    private String address;
    private String photoUrl;
    private CompanyStatus status;
    private LocalDateTime createdDate;
}