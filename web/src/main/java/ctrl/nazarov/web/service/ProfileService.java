package ctrl.nazarov.web.service;

import ctrl.nazarov.web.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;



}
