package com.example.demo.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//Bu interface kullanıcı repository'sini temsil eder.

@Repository //Bu anotasyon, Spring için bir repository olduğunu belirtir ve veri tabanı işlemleriyle ilgili olduğunu belirtir.
@Transactional(readOnly = true) //Bu anotasyon, bu repo sınıfının sadece veri okuma işlemleri gerçekleştiren metotları içerdiğini belirtir.
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email); //Bu metot, emaile göre bir kullanıcıyı aramk için kullanılır.
    //Optional sınıfı, metodu çağıran tarafın sonucun null olup olmadığını kontrol etmesine olanak tanır.
    // Eğer email'e sahip bir kullanıcı bulunursa, bu kullanıcıyı içeren bir Optional nesnesi döner.
}
