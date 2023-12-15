package ifsp.edu.source.DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifsp.edu.source.Model.Pessoa;

public class DaoPessoa {

    public boolean incluir(Pessoa pessoa) {
        DataBaseCom.conectar();

        String sqlString = "insert into pessoa (id, nome) values (?, ?)";
        try {
            PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);
            ps.setLong(1, pessoa.getId());
            ps.setString(2, pessoa.getNome());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean alterar(Pessoa pessoa) {
        DataBaseCom.conectar();
        if (findById(pessoa.getId()) == null) {
            return false;
        }
        try {
            String sqlString = "update pessoa set nome=? where id=?";
            PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);

            ps.setString(1, pessoa.getNome());
            ps.setLong(2, pessoa.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Pessoa findById(long id) {
        DataBaseCom.conectar();
        Pessoa pessoa = null;
        try {
            ResultSet rs = DataBaseCom.getStatement().executeQuery("select * from pessoa where id=" + id);
            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setId(rs.getLong("id"));
                pessoa.setNome(rs.getString("nome"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    public boolean excluir(Pessoa pessoa) {
        DataBaseCom.conectar();
        String sqlString = "delete from pessoa where id=" + pessoa.getId();
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
        String sqlString = "delete from pessoa where id=" + id;
        try {
            int rowsAffected = DataBaseCom.getStatement().executeUpdate(sqlString);
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Pessoa> listar() {
        List<Pessoa> lista = new ArrayList<>();
        try {
            ResultSet rs = DataBaseCom.getStatement().executeQuery("select * from pessoa");
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getLong("id"));
                pessoa.setNome(rs.getString("nome"));
                lista.add(pessoa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
