package globant.talentodigital.loginrrssv3.loginrrssv3.service;
import globant.talentodigital.loginrrssv3.loginrrssv3.entities.Usuarios;

public interface IUserService {
    void saveUser(String email, String password, String userName);
    void updateUser(Usuarios user);
}
