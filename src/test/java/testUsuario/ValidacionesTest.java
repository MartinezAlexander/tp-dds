package testUsuario;

import org.junit.Assert;
import org.junit.Test;
import usuarios.ContrasenaComunException;
import usuarios.TipoUsuario;
import usuarios.Usuario;

public class ValidacionesTest {

    @Test(expected = ContrasenaComunException.class)
    public void peoresContrasenas(){
        new Usuario("Agustin", "1234", TipoUsuario.USUARIO).validarContrasena();
    }
}
