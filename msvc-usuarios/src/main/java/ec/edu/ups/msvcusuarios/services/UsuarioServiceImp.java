package ec.edu.ups.msvcusuarios.services;

import ec.edu.ups.msvcusuarios.models.Usuario;
import ec.edu.ups.msvcusuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> byId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
