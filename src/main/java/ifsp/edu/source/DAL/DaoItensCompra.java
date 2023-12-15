package ifsp.edu.source.DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifsp.edu.source.Model.ItensCompra;

public class DaoItensCompra {

    public boolean incluir(ItensCompra itensCompra) {
        DataBaseCom.conectar();

        String sqlString = "insert into itens_compra (id_venda, id_produto, quantidade) values (?, ?, ?)";
        try {
            PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);
            ps.setLong(1, itensCompra.getIdVenda());
            ps.setLong(2, itensCompra.getIdProduto());
            ps.setInt(3, itensCompra.getQuantidade());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }	

    public boolean alterar(ItensCompra itensCompra) {
        DataBaseCom.conectar();
        try {
            String sqlString = "update itens_compra set quantidade=? where id_venda=? and id_produto=?";
            PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);

            ps.setInt(1, itensCompra.getQuantidade());
            ps.setLong(2, itensCompra.getIdVenda());
            ps.setLong(3, itensCompra.getIdProduto());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean excluir(long produtocompraId) {
        DataBaseCom.conectar();
        String sqlString = "delete from itens_compra where id=?";
        try {
            PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);
            ps.setLong(1, produtocompraId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<ItensCompra> listar() {
        DataBaseCom.conectar();
        List<ItensCompra> lista = new ArrayList<>();
        try {
            // Utiliza um INNER JOIN para combinar dados das tabelas itens_compra e produto
            String query = "SELECT itens_compra.id, "
                         + "itens_compra.compra_id, "
                         + "itens_compra.produto_id, "
                         + "itens_compra.quantidade, "
                         + "produto.nome AS nome_produto, "
                         + "produto.preco AS preco_produto "
                         + "FROM itens_compra "
                         + "INNER JOIN produto ON itens_compra.produto_id = produto.id";

            ResultSet rs = DataBaseCom.getStatement().executeQuery(query);

            while (rs.next()) {
                ItensCompra itensCompra = new ItensCompra();
                itensCompra.setId(rs.getLong("id"));
                itensCompra.setIdVenda(rs.getLong("compra_id"));
                itensCompra.setIdProduto(rs.getLong("produto_id"));
                itensCompra.setQuantidade(rs.getInt("quantidade"));
                lista.add(itensCompra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public ItensCompra findByIds(long compraId) {
        DataBaseCom.conectar();
        ItensCompra itemCompra = null;
        try {
            String sqlString = "SELECT * FROM itens_compra WHERE compra_id=?";
            PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);
            ps.setLong(1, compraId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                itemCompra = new ItensCompra();
                itemCompra.setIdVenda(rs.getLong("id"));
                itemCompra.setQuantidade(rs.getInt("quantidade"));
                itemCompra.setIdVenda(rs.getLong("id_venda"));
                itemCompra.setIdProduto(rs.getLong("id_produto"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return itemCompra;
}}
