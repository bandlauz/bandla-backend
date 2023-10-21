package uz.bandla.service;

import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;

public interface VerificationService {
    void sendConfirmationCode(String phoneNumber);

    void checkConfirmationCode(CheckConfirmationCodeDTO dto);
}
