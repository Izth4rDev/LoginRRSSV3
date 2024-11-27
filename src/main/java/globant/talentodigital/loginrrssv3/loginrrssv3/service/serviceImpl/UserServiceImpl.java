package globant.talentodigital.loginrrssv3.loginrrssv3.service.serviceImpl;

import globant.talentodigital.loginrrssv3.loginrrssv3.entities.Usuarios;
import globant.talentodigital.loginrrssv3.loginrrssv3.repository.IUserRepository;
import globant.talentodigital.loginrrssv3.loginrrssv3.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(String email, String password, String username) {
        if(email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            throw new IllegalArgumentException("User cannot be null and cannot be empty");
        }

        if(userRepository.findByCorreoElectronico(email).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        Usuarios usuario = new Usuarios();
        usuario.setContrasena(passwordEncoder.encode(password));
        usuario.setCorreoElectronico(email);
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setNombreUsuario(username);

        userRepository.save(usuario);

    }

    @Override
    public void updateUser(Usuarios user) {

    }


}
