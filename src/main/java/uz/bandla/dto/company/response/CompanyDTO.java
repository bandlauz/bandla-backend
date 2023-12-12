package uz.bandla.dto.company.response;

import uz.bandla.enums.CompanyStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class CompanyDTO {
    private Integer id;
    private String name;
    private String address;
    private String photoUrl;
    private CompanyStatus status;
    private LocalDateTime createdDate;
}