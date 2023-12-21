package com.example.demo.security.config;

import com.example.demo.appUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration //Bu anotasyon, sınıfın bir konfigürasyon sınıfı olduğunu belirtir ve Spring tarafından yönetilebileceğini ifade eder.
@AllArgsConstructor //Lombok anotasyonu, tüm sınıf alanları için bir parametreli constructor ekler. Bu, bağımlılıkların enjekte edilmesini sağlar.
@EnableWebSecurity //Bu anotasyon, Spring Security'nin etkinleştirildiğini belirtir.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {  //Bu sınıf, Spring Security'nin temel yapılandırma sınıfıdır

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception { //: Bu metod, HTTP güvenliği ile ilgili yapılandırmaları içerir.
        // Örneğin, belirli URL'lere erişim izinleri, CSRF korumasının devre dışı bırakılması ve diğer güvenlik ayarları burada yapılır.
        http
                //CSRF korumasını devre dışı bırakır. Cross-Site Request Forgery (CSRF) saldırılarına karşı bir güvenlik önlemidir.
                .csrf().disable()
                .authorizeRequests()//Bu metot, URL'ler üzerinde kimin erişim izinlerine sahip olduğunu belirlemek için kullanılır.
                .antMatchers("/api/v*/registration/**") .permitAll()// belirli bir URL deseni ile eşleşen isteklere izin verir.
                // /api/v*/registration/** deseni, /api/v1/registration, /api/v2/registration, vb. gibi URL'leri kapsar ve
                // bu URL'lere herkesin erişimine izin verir
                .anyRequest().authenticated() //diğer tüm isteklerin kimlik doğrulamasının gerektiği anlamına gelir.
                // Yani, kullanıcılar sadece kimlik doğrulama yaptıktan sonra bu URL'lere erişebilir.
                .and().formLogin(); //kullanıcıları form tabanlı bir kimlik doğrulama mekanizması kullanarak kimlik doğrulamaya yönlendirir.
        // formLogin() metodu, kullanıcı adı ve şifre ile oturum açma sayfasını sağlar.
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Bu metod, AuthenticationManagerBuilder sınıfını kullanarak kimlik doğrulama ile ilgili yapılandırmaları içerir.
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){ //Bu metod, DaoAuthenticationProvider bean'ini oluşturur ve
        // şifreleme ve kullanıcı detayları sağlamak için appUserService ve bCryptPasswordEncoder bean'lerini kullanır.
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder); //DaoAuthenticationProvider'ın hangi şifreleme algoritmasını kullanacağını belirtir.
        provider.setUserDetailsService(appUserService); //DaoAuthenticationProvider'ın kullanıcı detaylarını nereden alacağını belirtir.
        return provider; //DaoAuthenticationProvider nesnesi döndürülür.
    }
}
