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

    public boolean excluir(ItensCompra itensCompra) {
        DataBaseCom.conectar();
        String sqlString = "delete from itens_compra where id_venda=? and id_produto=?";
        try {
            PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);
            ps.setLong(1, itensCompra.getIdVenda());
            ps.setLong(2, itensCompra.getIdProduto());

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
            ResultSet rs = DataBaseCom.getStatement().executeQuery("select * from itens_compra");
            while (rs.next()) {
                ItensCompra itensCompra = new ItensCompra();
                itensCompra.setIdVenda(rs.getLong("id_venda"));
                itensCompra.setIdProduto(rs.getLong("id_produto"));
                itensCompra.setQuantidade(rs.getInt("quantidade"));
                lista.add(itensCompra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
