package ec.edu.ups.msvccursos.controllers;

import ec.edu.ups.msvccursos.models.Curso;
import ec.edu.ups.msvccursos.models.Usuario;
import ec.edu.ups.msvccursos.services.CursoService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    public ResponseEntity <List<Curso>> getAllCursos(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurso(@PathVariable Long id){
        Optional<Curso> cursoOptional = service.byId(id);
        if(cursoOptional.isPresent()){
            return ResponseEntity.ok(cursoOptional.get());
        }return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Curso curso){
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Curso curso, @PathVariable Long id){
        System.out.println("NOMMMMMM --->>>" + curso.getNombre());
        Optional<Curso> cursoOptional = service.byId(id);
        if (cursoOptional.isPresent()){
            Curso cursoDB = cursoOptional.get();
            cursoDB.setNombre(curso.getNombre());
            System.out.println("nombre --->>>" + curso.getNombre());
            cursoDB.setCosto(curso.getCosto());
            cursoDB.setDescripcion(curso.getDescripcion());
            cursoDB.setNombreInstructor(curso.getNombreInstructor());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
        }return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Curso> cursoOptional = service.byId(id);
        if (cursoOptional.isPresent()){
            Curso cursoDB = cursoOptional.get();
            service.delete(cursoOptional.get().getId());
            return ResponseEntity.noContent().build();
        }return ResponseEntity.notFound().build();
    }


    //Métodos para comunicacion entre microservicios
    @PutMapping("/asignar-usuario/{idCurso}")
    public ResponseEntity<?>asignarUsuarioCurso(@RequestBody Usuario usuario, @PathVariable Long idCurso){
        Optional<Usuario> optional = null;
        try {
            optional = service.addUsuarioCurso(usuario,idCurso);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body
                    (Collections.singletonMap("mensaje","No existe el usuario o error en la comunicacion" + e.getMessage()));
        }

        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }return ResponseEntity.notFound().build();
    }

    //Agrear un usuario que no exista en la base de datos
    @PostMapping("/crear-usuario/{id}")
    public ResponseEntity<?>crearUsuarioCurso(@RequestBody Usuario usuario, @PathVariable Long idCurso){
        Optional<Usuario> optional = null;
        try {
            optional = service.crearUsuario(usuario,idCurso);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body
                    (Collections.singletonMap("mensaje","No se pudo crear el usuario o error en la comunicación" + e.getMessage()));
        }

        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{id}")
    public ResponseEntity<?>eliminarUsuarioCurso(@RequestBody Usuario usuario, @PathVariable Long idCurso){
        Optional<Usuario> optional = null;
        try {
            optional = service.deleteUsuarioAsignadoCurso(usuario,idCurso);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body
                    (Collections.singletonMap("mensaje","No se pudo eliminar usuario o error en la comunicacion" + e.getMessage()));
        }

        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optional.get());
        }return ResponseEntity.notFound().build();
    }



}
