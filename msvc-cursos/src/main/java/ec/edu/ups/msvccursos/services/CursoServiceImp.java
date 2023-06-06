package ec.edu.ups.msvccursos.services;

import ec.edu.ups.msvccursos.clients.UsuarioClientRest;
import ec.edu.ups.msvccursos.models.Curso;
import ec.edu.ups.msvccursos.models.CursoUsuario;
import ec.edu.ups.msvccursos.models.Usuario;
import ec.edu.ups.msvccursos.repositories.CursoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImp implements CursoService {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private UsuarioClientRest clientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> getAll() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> byId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    //Métodos para comunicarme con el microservicio cursos.
    @Override
    @Transactional
    public Optional<Usuario> addUsuarioCurso(Usuario usuario, Long cursoId) {
        Optional<Curso> optional = repository.findById(cursoId);
        if(optional.isPresent()){
            Usuario usuarioMscv = clientRest.getUsuario(usuario.getId());

            Curso curso = optional.get(); //COn esto obtenemos el curso.
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setIdUsuario(usuarioMscv.getId());

            curso.addCursoUsuario(cursoUsuario);
            repository.save(curso);
            return  optional.of(usuarioMscv);

        }return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> optional = repository.findById(cursoId);
        if(optional.isPresent()){
            Usuario usuarioMscv = clientRest.creat(usuario);

            Curso curso = optional.get(); //COn esto obtenemos el curso.
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setIdUsuario(usuarioMscv.getId());

            curso.addCursoUsuario(cursoUsuario);
            repository.save(curso);
            return  optional.of(usuarioMscv);

        }return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> deleteUsuarioAsignadoCurso(Usuario usuario, Long cursoId) {
        Optional<Curso> optional = repository.findById(cursoId);
        if(optional.isPresent()){
            Usuario usuarioMscv = clientRest.getUsuario(usuario.getId());

            Curso curso = optional.get(); //COn esto obtenemos el curso.
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setIdUsuario(usuarioMscv.getId());

            curso.deleteCursoUsuario(cursoUsuario);
            repository.save(curso);
            return  optional.of(usuarioMscv);

        }return Optional.empty();
    }
}
