package autenticacion;

public class RequisitoLongitud implements RequisitoContrasena {

    @Override
    public String descripcion() {
        return "Una contrasena segura debe tener un minimo de 8 caracteres";
    }

    @Override
    public String ejemplo() {
        return "hola";
    }

    @Override
    public void validar(String contrasena) {
        if(contrasena.length() < 8){
            throw new ContrasenaCortaException("No se cumple con la condicion: " + descripcion());
        }
    }
}
