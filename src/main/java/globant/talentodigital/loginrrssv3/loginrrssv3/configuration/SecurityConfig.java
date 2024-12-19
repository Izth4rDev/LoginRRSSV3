package globant.talentodigital.loginrrssv3.loginrrssv3.configuration;

import globant.talentodigital.loginrrssv3.loginrrssv3.service.serviceImpl.CustomUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private  final CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/logout", "/register")) // Ignora CSRF para /logout y /register
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/error", "/logout", "/registerForm", "/register").permitAll() // Permite acceso público
                        .anyRequest().authenticated() // Cualquier otra ruta requiere autenticación
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página personalizada de login
                        .defaultSuccessUrl("/profile", true) // Redirige tras login exitoso
                        .failureUrl("/login?error=true") // Maneja errores de login
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/profile", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}





