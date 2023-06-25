package uz.nazarovctrl.library.service;

import ch.qos.logback.core.testUtil.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nazarovctrl.library.entity.SmsHistoryEntity;
import uz.nazarovctrl.library.repository.SmsHistoryRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsHistoryService smsHistoryService;


    public void sendSms(String phoneNumber) {
        Random random = new Random();
        int code = random.nextInt(1000, 10000);
        smsHistoryService.addHistory(phoneNumber, String.valueOf(code));
    }
}
