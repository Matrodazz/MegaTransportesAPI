package megat.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import megat.api.models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    
}
