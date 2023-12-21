package com.example.demo.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Bu anotasyon, sınıfın bir RESTful API kontrolcüsü olduğunu belirtir.
// Yani, bu sınıf HTTP isteklerine yanıt veren bir Spring MVC kontrolcüsüdür.
@RequestMapping(path = "api/v1/registration") // Bu anotasyon, bu kontrolcünün hangi URL'ler üzerinden istek alacağını belirtir.
// Bu kontrolcü, "api/v1/registration" path'i altındaki isteklere yanıt verir.
@AllArgsConstructor //Lombok kütüphanesinden gelen bu anotasyon, tüm sınıf alanları için bir parametreli constructor ekler.
// Bu sayede bağımlılıkların enjekte edilmesi sağlanır.
public class RegistrationController {
    private RegistrationService registrationService;


    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request); //Bu metot, HTTP POST isteği ile gönderilen RegistrationRequest objesini alır (@RequestBody anotasyonuyla) ve
        // bu objeyi kullanarak RegistrationService sınıfındaki register metotunu çağırır. Sonucu geri döndürür.
    }


}
