package ec.edu.ups.msvcusuarios.services;

import ec.edu.ups.msvcusuarios.models.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

public interface UsuarioService {
    List<Usuario>getAll();
    Optional<Usuario>byId(Long id);
    Usuario save(Usuario usuario);
    void delete(Long id);
}
