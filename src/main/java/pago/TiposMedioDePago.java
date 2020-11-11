package pago;

public enum TiposMedioDePago {
    CAJERO_AUTOMATICO,
    DINERO_EN_CUENTA,
    EFECTIVO,
    TARJETA_DE_CREDITO,
    TARJETA_DE_DEBITO;

    public String getNombre(){ return this.name(); }
}

