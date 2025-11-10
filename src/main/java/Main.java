import com.ocupamais.model.Usuario;
import com.ocupamais.service.UsuarioService;

public class Main {
    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();

        Usuario novo = new Usuario("Maria", "maria@exemplo.com",
                        "senhaExemplo123", "USUARIO", "Centro");

        usuarioService.cadastrar(novo);

        System.out.println("Usuários cadastrados:");
        usuarioService.listarTodos().forEach(System.out::println);

    }
}
