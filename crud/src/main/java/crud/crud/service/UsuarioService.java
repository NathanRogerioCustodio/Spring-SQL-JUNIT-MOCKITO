package crud.crud.service;

import crud.crud.Exception.UsuarioNaoEncontradoException;
import crud.crud.model.Usuario;
import crud.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUsuario(Usuario usuario){
        this.usuarioRepository.save(usuario);
        return usuario;
    }

    public List<Usuario> listUsuarios (){
        return usuarioRepository.findAll();
    }

    public Usuario findUsuarioById (Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado ! "));
    }

    public void deleteUsuario (Long id){
        usuarioRepository.deleteById(id);
    }
}
