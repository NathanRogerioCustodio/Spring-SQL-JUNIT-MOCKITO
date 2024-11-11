package crud.crud;

import crud.crud.model.Usuario;
import crud.crud.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void testCreateAndFindUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setName("New Integration User");
        usuarioRepository.save(usuario);

        // Ajuste o caminho conforme o mapeamento do controlador
        // Se estiver usando @RequestParam, use: get("/usuario/id").param("id", usuario.getId().toString())
        mockMvc.perform(get("/usuario/" + usuario.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Integration User"));
    }

    @Test
    void testDeleteUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setName("User To Delete");
        usuarioRepository.save(usuario);

        // Deletar o usuário
        mockMvc.perform(delete("/usuario/" + usuario.getId()))
                .andExpect(status().isOk());

        // Verificar se o usuário foi deletado (espera status 404 Not Found)
        mockMvc.perform(get("/usuario/" + usuario.getId()))
                .andExpect(status().isNotFound());
    }
}
