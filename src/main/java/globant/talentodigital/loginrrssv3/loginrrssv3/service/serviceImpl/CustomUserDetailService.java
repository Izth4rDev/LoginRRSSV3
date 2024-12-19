package globant.talentodigital.loginrrssv3.loginrrssv3.service.serviceImpl;

import globant.talentodigital.loginrrssv3.loginrrssv3.entities.Usuarios;
import globant.talentodigital.loginrrssv3.loginrrssv3.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuario = userRepository.findByCorreoElectronico(email).
                orElseThrow( () -> new UsernameNotFoundException(email) );

        return User.builder()
                .username(usuario.getCorreoElectronico())
                .password(usuario.getContrasena())
                .roles("USER")
                    .build();
    }
}
