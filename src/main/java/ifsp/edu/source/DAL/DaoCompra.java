package ifsp.edu.source.DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifsp.edu.source.Model.Compra;
import ifsp.edu.source.Model.Pessoa;

public class DaoCompra {

    public boolean incluir(Compra compra) {
        DataBaseCom.conectar();

        String sqlString = "insert into compra (id, cliente_id, data) values (?, ?, ?)";
        try {
            PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);
            ps.setLong(1, compra.getId());
            ps.setLong(2, compra.getCliente().getId());
            ps.setDate(3, new java.sql.Date(compra.getData().getTime()));

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean alterar(Compra compra) {
        DataBaseCom.conectar();
        if (findById(compra.getId()) == null) {
            return false;
        }
        try {
            String sqlString = "update compra set cliente_id=?, data=? where id=?";
            PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);

            ps.setLong(1, compra.getCliente().getId());
            ps.setDate(2, new java.sql.Date(compra.getData().getTime()));
            ps.setLong(3, compra.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Compra findById(long id) {
        DataBaseCom.conectar();
        Compra compra = null;
        try {
            ResultSet rs = DataBaseCom.getStatement().executeQuery("select * from compra where id=" + id);
            while (rs.next()) {
                compra = new Compra();
                compra.setId(rs.getLong("id"));
                compra.setCliente(findPessoaById(rs.getLong("cliente_id")));
                compra.setData(rs.getDate("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compra;
    }

    private Pessoa findPessoaById(long id) {
        DaoPessoa daoPessoa = new DaoPessoa();
        return daoPessoa.findById(id);
    }

    public boolean excluir(Compra compra) {
        DataBaseCom.conectar();
        String sqlString = "delete from compra where id=" + compra.getId();
        try {
            int rowsAffected = DataBaseCom.getStatement().executeUpdate(sqlString);
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean excluir(long id) {
        DataBaseCom.conectar();
        String sqlString = "delete from compra where id=" + id;
        try {
            int rowsAffected = DataBaseCom.getStatement().executeUpdate(sqlString);
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Compra> listar() {
        List<Compra> lista = new ArrayList<>();
        try {
            ResultSet rs = DataBaseCom.getStatement().executeQuery("select * from compra");
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getLong("id"));
                compra.setCliente(findPessoaById(rs.getLong("cliente_id")));
                compra.setData(rs.getDate("data"));
                lista.add(compra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}

