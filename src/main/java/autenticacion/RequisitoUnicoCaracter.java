package autenticacion;

public class RequisitoUnicoCaracter implements RequisitoContrasena {
    @Override
    public String descripcion() {
        return "Una contrasena segura no deberia estar compuesta por solo un unico caracter";
    }

    @Override
    public String ejemplo() {
        return "aaaaaaaa";
    }

    @Override
    public void validar(String contrasena) {
        if (unicoCaracter(contrasena)){
            throw new ContrasenaIgualException("No se cumlpe con la condicion: " + descripcion());
        }
    }

    private boolean unicoCaracter(String password){
        char unicoCaracter = password.charAt(0);
        for(char caracter : password.toCharArray()){
            if(caracter != unicoCaracter) return false;
        }
        return true;
    }
}
