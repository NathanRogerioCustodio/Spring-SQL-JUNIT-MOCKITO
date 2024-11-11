package crud.crud.controller;


import crud.crud.model.Usuario;
import crud.crud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> createUsuario (@RequestBody Usuario usuario){
        Usuario newUsuario = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> listUsuarios (){
        List<Usuario> usuarios = this.usuarioService.listUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUsuarioById(@PathVariable Long id){
        Usuario newUsuario = this.usuarioService.findUsuarioById(id);
        return new ResponseEntity<>(newUsuario, HttpStatus.OK);
    }
    @PutMapping
    public Usuario updateUsuario (@RequestBody Usuario usuario){
        return usuarioService.createUsuario(usuario);

    }
    @DeleteMapping("{id}")
    void deleteUsuario (@PathVariable Long id){
        usuarioService.deleteUsuario(id);
    }
}
