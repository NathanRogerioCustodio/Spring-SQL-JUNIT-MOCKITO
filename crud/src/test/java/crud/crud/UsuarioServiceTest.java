package crud.crud;

import crud.crud.Exception.UsuarioNaoEncontradoException;
import crud.crud.model.Usuario;
import crud.crud.repository.UsuarioRepository;
import crud.crud.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setName("New User");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario createdUsuario = usuarioService.createUsuario(usuario);

        assertNotNull(createdUsuario);
        assertEquals("New User", createdUsuario.getName());
        verify(usuarioRepository, times(1)).save(usuario);
    }


    @Test
    void testFindUsuarioByIdSucesso(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setName("Teste");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario foundUsuario = usuarioService.findUsuarioById(1L);
        assertNotNull(foundUsuario);
        assertEquals("Teste", foundUsuario.getName());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindUsuarioByIdFalha(){
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.findUsuarioById(1L));
        verify(usuarioRepository, times(1)).findById(1L);
    }
}
