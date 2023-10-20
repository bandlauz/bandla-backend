package uz.bandla.service.impl;

import uz.bandla.dto.auth.request.CheckConfirmationCodeDTO;
import uz.bandla.entity.SmsEntity;
import uz.bandla.exp.auth.ShortIntervalException;
import uz.bandla.exp.auth.VerificationCodeNotValidException;
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

    @Override
    public void sendConfirmationCode(String phoneNumber) {
        Optional<SmsEntity> optional = smsService.getLastSmsVerification(phoneNumber);
        if (optional.isPresent() && !VerificationUtil.isAfter2Minutes(optional.get().getCreatedDate())) {
            throw new ShortIntervalException("Short interval exception");
        }

        String code = RandomUtil.generateSmsCode();
        String message = String.format("bandla.uz \n code:%s", code);

        SmsEntity sms = new SmsEntity();
        sms.setPhoneNumber(phoneNumber);
        sms.setMessage(message);
        sms.setCode(code);
        sms.setType(SmsType.VERIFICATION);

        System.out.println(sms.getCode());
        smsService.sendSms(sms);
    }

    @Override
    public void checkConfirmationCode(CheckConfirmationCodeDTO dto) {
        Optional<SmsEntity> optional = smsService.getLastSmsVerification(dto.getPhoneNumber());
        if (optional.isEmpty()) {
            throw new VerificationCodeNotValidException();
        }

        SmsEntity sms = optional.get();

        if (!sms.getCode().equals(dto.getCode()) ||
                !VerificationUtil.isAfter2Minutes(sms.getCreatedDate()) ||
                sms.isUsed()) {
            throw new VerificationCodeNotValidException();
        }

        sms.setUsed(true);
        smsService.save(sms);
    }
}
