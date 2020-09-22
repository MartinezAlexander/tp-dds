package presupuestos;

import operaciones.ItemOperacion;
import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ItemPresupuesto extends EntidadPersistente{
    @ManyToOne
    private ItemOperacion item;
    private BigDecimal valor;
    private String currencyId;

    public ItemPresupuesto(){}
    
    public ItemPresupuesto(ItemOperacion item, BigDecimal valor, String currencyId) {
        this.item = item;
        this.valor = valor;
        this.currencyId = currencyId;
    }

    public BigDecimal getValor() {
        return valor;
    }
    
    public String getCurrencyId(){
    	return currencyId;
    }
}