package com.example.demo.registration;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
            return "works"; //Bu metot, RegistrationRequest tipinde bir nesne alır ve şu anda sadece "works" metnini döndürür.
    }
}
