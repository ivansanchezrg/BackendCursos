package ec.edu.ups.msvccursos.clients;

import ec.edu.ups.msvccursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-usuarios", url = "localhost:8001")
public interface UsuarioClientRest {

    @GetMapping
    public Usuario getUsuario(@PathVariable Long id);

    @PostMapping("/")
    Usuario creat (@RequestBody Usuario usuario);
}
