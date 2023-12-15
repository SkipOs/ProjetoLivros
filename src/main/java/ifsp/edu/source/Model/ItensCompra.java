package ifsp.edu.source.Model;

public class ItensCompra {

    private long idVenda;
    private long idProduto;
    private int quantidade;

    public ItensCompra() {
        // Construtor vazio necessário para o Spring
    }

    public ItensCompra(long idVenda, long idProduto, int quantidade) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
