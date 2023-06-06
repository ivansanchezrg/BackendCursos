package ec.edu.ups.msvccursos.services;

import ec.edu.ups.msvccursos.models.Curso;
import ec.edu.ups.msvccursos.models.Usuario;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> getAll();
    Optional<Curso> byId(Long id);
    Curso save (Curso curso);
    void delete(Long id);

    //Estos métodos se utilizan para conectarme con el otro microservicio.
    Optional<Usuario> addUsuarioCurso(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> deleteUsuarioAsignadoCurso(Usuario usuario, Long cursoId);
}
