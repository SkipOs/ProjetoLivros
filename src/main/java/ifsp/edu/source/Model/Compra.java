package ifsp.edu.source.Model;

import java.util.Date;
import java.util.List;

public class Compra {
    private long id;
    private Pessoa cliente;
    private List<ItensCompra> itensCompra;  // Lista de itens da compra
    private Date data;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public List<ItensCompra> getItens() {
        return itensCompra;
    }

    public void setItens(List<ItensCompra> itens) {
        this.itensCompra = itens;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
