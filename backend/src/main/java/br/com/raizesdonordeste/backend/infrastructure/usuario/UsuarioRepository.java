package br.com.raizesdonordeste.backend.infrastructure.usuario;

import br.com.raizesdonordeste.backend.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    org.springframework.security.core.userdetails.UserDetails findByEmail(String email);

}