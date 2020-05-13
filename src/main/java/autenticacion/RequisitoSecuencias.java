package autenticacion;

public class RequisitoSecuencias implements RequisitoContrasena {
    @Override
    public String descripcion() {
        return "Una contrasena segura no debe ser una secuencia entera de caracteres";
    }

    @Override
    public String ejemplo() {
        return "abcdef";
    }

    @Override
    public void validar(String contrasena) {
        if (esSecuencia(contrasena)){
            throw new ContrasenaSecuenciaException("No se cumlpe con la condicion: " + descripcion());
        }
    }

    private boolean esSecuencia(String string){

        for (int i = 1 ; i < string.length() ; i++){
            char anterior = string.charAt(i - 1);
            char actual = string.charAt(i);

            if(anterior + 1 != actual) return false;
        }
        return true;
    }
}
