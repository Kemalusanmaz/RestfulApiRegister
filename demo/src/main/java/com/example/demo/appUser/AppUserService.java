package com.example.demo.appUser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service // Bu anotasyon, appUserService sınıfının bir Spring servisi olduğunu belirtir.
// Spring tarafından yönetilen ve iş mantığıyla ilgili operasyonları içeren sınıflar için kullanılır.
@AllArgsConstructor //Lombok kütüphanesinden gelen bu anotasyon, sınıfın tüm alanlarını içeren bir parametreli constructor ekler.
// Bu, bağımlılıkların enjekte edilmesini sağlar. AppUserRepository ve USER_NOT_FOUND_MSG alanları bu constructor aracılığıyla enjekte edilir.
public class AppUserService implements UserDetailsService { //UserDetailsService arayüzü, Spring Security tarafından kullanılan bir arayüzdür.
//Bu arayüzü implemente eden sınıflar, kullanıcının kimlik bilgilerini yüklemekle sorumludur.

    private final AppUserRepository appUserRepository;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found.";


    //Bu metot, Spring Security'nin kimlik doğrulama mekanizması tarafından kullanılır.
    // Belirtilen email'e sahip bir kullanıcıyı AppUserRepository üzerinden arar ve bulunamazsa bir UsernameNotFoundException fırlatır.
    // Bulunan kullanıcı bilgilerini, Spring Security tarafından kullanılan bir UserDetails nesnesine dönüştürerek geri döner.

      @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
        // AppUserRepository'den gelen findByEmail metodu aracılığıyla email'e göre bir kullanıcı araması yapar.
        // Optional nesnesi içinde bir kullanıcı bulunursa, bu kullanıcı döner. Bulunamazsa orElseThrow ile bir hata fırlatılır.
        //Eğer Optional içinde bir kullanıcı bulunmazsa, bu kısım devreye girer ve UsernameNotFoundException fırlatılır.
        // Bu hata, kullanıcının bulunamadığını belirten bir mesaj içerir ve Spring Security'ye kullanıcı doğrulama işlemi sırasında bir hata olduğunu bildirir.
    }
}
