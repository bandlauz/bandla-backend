package uz.nazarovctrl.bandla.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nazarovctrl.bandla.repository.ProfileRepository;



@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;



}
