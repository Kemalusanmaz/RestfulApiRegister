package com.example.demo.appUser;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails; //SpringFrameworkten kullanıcı kayıdı için oluşturulan Spring Securitry interface import edilir.

import java.util.Collection;
import java.util.Collections;

@Getter //Lombok paketinden gelen bir anotasyon. Otomatik olarak getter oluşturur.
@Setter //Lombok paketinden gelen bir anotasyon. Otomatik olarak setter oluşturur.
@EqualsAndHashCode //Lombok anotasyonu, otomatik olarak equals ve hashCode metodlarını oluşturur.
@NoArgsConstructor //Lombok anotasyonu, parametresiz bir constructor ekler. Bu, JPA entity sınıflarında genellikle kullanılır.
@Entity //JPA entity sınıfı olduğunu belirtir, yani bu sınıf veritabanında bir tabloya karşılık gelir.
public class AppUser implements UserDetails{

    //Kullanıcı kayıt sistemi için kullanılacak parametreler app user sınıfında tanımlanır.

    @Id // Bu alanın birincil anahtar (primary key) olduğunu belirtir
    @SequenceGenerator( //Bu anotasyon, veritabanında bir sequence (dizi) oluşturulmasını sağlar.
            name="student_sequence", //Bu sequence, student_sequence adıyla belirlenir
            sequenceName = "student_sequence",
            allocationSize = 1 //ve bir artış büyüklüğü (allocation size) belirlenir.
    )
    @GeneratedValue( //Bu anotasyon, bir alanın otomatik olarak artan bir şekilde değer alacağını belirtir.
            strategy = GenerationType.SEQUENCE, // strategy ile bu artışın nasıl olacağı belirlenir.
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole AppUserRole; //Nu değişken Enum sınıfından referans alınmıştır.
    private Boolean locked;
    private Boolean enabled;

//Bu constructor, kullanıcı nesnesi oluştururken kullanılabilir. Kullanıcıdan alınan parametrelerle bir AppUser nesnesi oluşturur.
    public AppUser(String name,
                   String userName,
                   String email,
                   String password,
                   com.example.demo.appUser.AppUserRole appUserRole,
                   Boolean locked,
                   Boolean enabled) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.AppUserRole = appUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }

//Spring framework'ten gelen UserDetails interface içinde bulunan operasyonlar implement edilir ve fonksiyonlar tanımlanır.

    //Bu metot genellikle kullanıcının sahip olduğu rolleri belirlemek için kullanılır.
    //   Örneğin, bir kullanıcı "ADMIN" rolüne sahipse, bu metodun döndürdüğü koleksiyonda SimpleGrantedAuthority("ADMIN") bulunacaktır.
    // Spring Security, bu yetkileri kullanarak kullanıcının erişim kontrolünü gerçekleştirir.
    //kullanıcının yetkilerini temsil eden bir GrantedAuthority koleksiyonunu döndürür.
    // Bu metodun amacı, kullanıcının sahip olduğu yetkileri Spring Security ile uyumlu bir şekilde ifade etmektir.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //metodun döndürdüğü veri tipi, kullanıcının yetkilerini temsil eden bir GrantedAuthority koleksiyonunu ifade eder.
        // GrantedAuthority, Spring Security tarafından kullanılan bir arayüzdür ve bir yetkiyi temsil eder.
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(AppUserRole.name()); //kullanıcının rolünü temsil eden bir SimpleGrantedAuthority nesnesi oluşturuluyor.
        //AppUserRole.name() metodu, kullanıcının rolünün ismini string olarak döndürür.
        return Collections.singletonList(authority); //oluşturulan SimpleGrantedAuthority nesnesini içeren bir koleksiyon döndürülüyor.
        // Kullanıcının yalnızca bir rolü olduğunu varsayarsak, bu koleksiyon yalnızca bir eleman içerecektir.
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
