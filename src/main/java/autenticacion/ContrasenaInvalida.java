package autenticacion;

public class ContrasenaInvalida extends RuntimeException{
    public ContrasenaInvalida(String s) {
        super(s);
    }
}
