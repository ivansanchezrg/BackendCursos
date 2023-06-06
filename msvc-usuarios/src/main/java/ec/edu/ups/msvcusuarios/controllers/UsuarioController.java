package ec.edu.ups.msvcusuarios.controllers;

import ec.edu.ups.msvcusuarios.models.Usuario;
import ec.edu.ups.msvcusuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario>getAllUsuarios(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.byId(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Usuario usuario){
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.byId(id);
        if (usuarioOptional.isPresent()){
            Usuario usuarioDB = usuarioOptional.get();
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setCorreo(usuario.getCorreo());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDB));
        }return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.byId(id);
        if (usuarioOptional.isPresent()){
            Usuario usuarioDB = usuarioOptional.get();
            service.delete(id);
            return ResponseEntity.noContent().build();
        }return ResponseEntity.notFound().build();
    }
}
