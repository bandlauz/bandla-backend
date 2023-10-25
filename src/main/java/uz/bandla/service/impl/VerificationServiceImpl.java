package uz.bandla.service.impl;

import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.bandla.entity.SmsEntity;
import uz.bandla.exp.auth.ShortIntervalException;
import uz.bandla.exp.auth.VerificationCodeNotValidException;
import uz.bandla.favor.SmsFavor;
import uz.bandla.util.RandomUtil;
import uz.bandla.util.VerificationUtil;
import uz.bandla.enums.SmsType;
import uz.bandla.service.SmsService;
import uz.bandla.service.VerificationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    private final SmsService smsService;
    private final SmsFavor smsFavor;

    @Override
    public void sendConfirmationCode(String phoneNumber) {
        Optional<SmsEntity> optional = getLastSmsVerification(phoneNumber);
        if (optional.isPresent() &&
                VerificationUtil.isShortInterval(optional.get().getCreatedDate())) {
            throw new ShortIntervalException("Short interval exception");
        }

        String code = RandomUtil.generateSmsCode();
        String message = String.format("bandla.uz \n code:%s", code);

        SmsEntity sms = new SmsEntity(phoneNumber, message, code, SmsType.VERIFICATION);
        smsService.sendSms(sms);
    }

    @Override
    public void checkConfirmationCode(CheckConfirmationCodeDTO dto) {
        Optional<SmsEntity> optional = getLastSmsVerification(dto.getPhoneNumber());
        if (optional.isEmpty() ||
                !VerificationUtil.isValid(optional.get(), dto.getCode())) {
            throw new VerificationCodeNotValidException();
        }

        SmsEntity sms = optional.get();
        sms.setUsed(true);
        smsFavor.save(sms);
    }

    private Optional<SmsEntity> getLastSmsVerification(String phoneNumber) {
        return smsFavor.getLastSms(phoneNumber, SmsType.VERIFICATION);
    }
}
