package ifsp.edu.source.DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifsp.edu.source.Model.Produto;

public class DaoProduto {
    
    public boolean incluir(Produto produto) {
        DataBaseCom.conectar();

        try {
            String sql = "INSERT INTO produto (nome, quantidade, preco) VALUES (?, ?, ?)";
            PreparedStatement stmt = DataBaseCom.getConnection().prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DataBaseCom.desconectar();
        }
    }

    public boolean atualizar(Produto produto) {
        DataBaseCom.conectar();

        try {
            String sql = "UPDATE produto SET nome = ?, quantidade = ?, preco = ? WHERE id = ?";
            PreparedStatement stmt = DataBaseCom.getConnection().prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getId());
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DataBaseCom.desconectar();
        }
    }

    public List<Produto> listar() {
        DataBaseCom.conectar();
        List<Produto> produtos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produto";
            PreparedStatement stmt = DataBaseCom.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DataBaseCom.desconectar();
        }
    }
}
