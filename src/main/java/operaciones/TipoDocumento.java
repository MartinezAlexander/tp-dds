package operaciones;

public enum TipoDocumento {
    TICKET, FACTURA, COTIZACION;

    public String getNombre() {
        return this.name();
    }
    }