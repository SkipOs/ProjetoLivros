package ifsp.edu.source.Model;

import java.util.ArrayList;
import java.util.List;

public class Compra {
    private long id;
    private long cliente;
    private List<ItensCompra> itensCompra;  // Lista de itens da compra

    public Compra() {
        this.itensCompra = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCliente() {
        return cliente;
    }

    public void setCliente(long id) {
        this.cliente = id;
    }

    public List<ItensCompra> getItens() {
        return itensCompra;
    }

    public void setItens(List<ItensCompra> itens) {
        this.itensCompra = itens;
    }
}
