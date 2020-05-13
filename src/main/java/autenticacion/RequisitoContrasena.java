package autenticacion;

public interface RequisitoContrasena {
    String descripcion();
    String ejemplo();
    void validar(String contrasena);
}
