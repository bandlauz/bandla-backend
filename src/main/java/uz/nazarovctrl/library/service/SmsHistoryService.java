package uz.nazarovctrl.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.nazarovctrl.library.entity.SmsHistoryEntity;
import uz.nazarovctrl.library.exp.SmsHistoryNotFound;
import uz.nazarovctrl.library.repository.SmsHistoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SmsHistoryService {
    private final SmsHistoryRepository repository;

    public Long getCountInTwoMinute(String email) {

        LocalDateTime toDate = LocalDateTime.now();
        LocalDateTime fromDate = toDate.minusMinutes(2);

        return repository.countByPhoneNumberAndCreatedOnBetween(email, fromDate, toDate);
    }

    public Boolean check(String phone, String code) {
        Pageable pageable = PageRequest.of(0, 1);

        Page<SmsHistoryEntity> pageObj = repository.findByPhoneNumberAndIsUsedOrderByCreatedOnDesc(phone, false, pageable);
        List<SmsHistoryEntity> content = pageObj.getContent();
        if (content.isEmpty()) {
            throw new SmsHistoryNotFound("Sms history not found");
        }

        SmsHistoryEntity entity = content.get(0);

        if (entity.getCode().equals(code)) {
            entity.setUsed(true);
            repository.save(entity);
            return true;
        }
        return false;

    }

    public void addHistory(String phone, String code) {
        SmsHistoryEntity entity = new SmsHistoryEntity();
        entity.setPhoneNumber(phone);
        entity.setCode(code);
        repository.save(entity);
    }
}
