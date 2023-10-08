package uz.nazarovctrl.bandla.service;

import uz.nazarovctrl.bandla.dto.auth.request.CheckConfirmationCodeDTO;

public interface VerificationService {
    void sendConfirmationCode(String phoneNumber);

    void checkConfirmationCode(CheckConfirmationCodeDTO dto);
}
