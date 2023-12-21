package com.example.demo.registration;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter //Lombok anotasyonu, tüm sınıf alanları için otomatik olarak getter metodlarını oluşturur.
@AllArgsConstructor // Lombok anotasyonu, tüm sınıf alanları için bir parametreli constructor ekler.
// Bu constructor, sınıfın nesnesini oluştururken tüm alanların değerlerini alır.
@EqualsAndHashCode // Lombok anotasyonu, equals ve hashCode metodlarını otomatik olarak oluşturur.
@ToString //Lombok anotasyonu, toString metodunu otomatik olarak oluşturur.
public class RegistrationRequest {

    //Bu alanlar, bir kullanıcının kayıt olurken gireceği bilgileri temsil eder.
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
}
