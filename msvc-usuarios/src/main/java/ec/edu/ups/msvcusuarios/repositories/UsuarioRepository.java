package ec.edu.ups.msvcusuarios.repositories;

import ec.edu.ups.msvcusuarios.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
