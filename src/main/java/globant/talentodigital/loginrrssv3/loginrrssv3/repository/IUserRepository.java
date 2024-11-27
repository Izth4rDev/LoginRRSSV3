package globant.talentodigital.loginrrssv3.loginrrssv3.repository;

import globant.talentodigital.loginrrssv3.loginrrssv3.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByCorreoElectronico(String email);
}
