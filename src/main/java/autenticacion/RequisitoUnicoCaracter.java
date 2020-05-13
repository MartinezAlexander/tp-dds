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
        for (int i = 1 ; i < password.length() ; i++){

            char anterior = password.charAt(i - 1);
            char actual = password.charAt(i);

            if(anterior != actual) return false;
        }
        return true;
    }
}
