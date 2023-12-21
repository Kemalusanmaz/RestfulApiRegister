package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Bu sınıf, BCryptPasswordEncoder bean'ini oluşturan bir Spring konfigürasyon sınıfıdır.
@Configuration
public class PasswordEncoder {
    @Bean //bCryptPasswordEncoder adında bir BCryptPasswordEncoder bean'i oluşturulur.
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Bu yapı, kullanıcı kimlik doğrulama işlemlerini yapılandırmak ve
    // şifreleri güvenli bir şekilde saklamak için kullanılan bir temel Spring Security konfigürasyonunu temsil eder.



}
