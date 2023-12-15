package ifsp.edu.source.DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifsp.edu.source.Model.Compra;
import ifsp.edu.source.Model.ItensCompra;

public class DaoCompra {

	public boolean incluir(Compra compra) {
		DataBaseCom.conectar();

		String sqlString = "insert into compra (id, cliente_id, data) values (?, ?, ?)";
		try {
			PreparedStatement ps = DataBaseCom.getConnection().prepareStatement(sqlString);
			ps.setLong(1, compra.getId());
			ps.setLong(2, compra.getCliente());

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

			ps.setLong(1, compra.getCliente());
			ps.setLong(2, compra.getId());


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
				compra.setCliente(rs.getLong("cliente_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compra;
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
	        // Consulta SQL usando INNER JOIN para obter informações de compra e itens de compra
	        String query = "SELECT c.id AS compra_id, c.cliente_id, i.id AS item_id, i.produto_id, i.quantidade " +
	                       "FROM compra c " +
	                       "INNER JOIN itens_compra i ON c.id = i.compra_id";

	        ResultSet rs = DataBaseCom.getStatement().executeQuery(query);

	        long currentCompraId = -1;  // Para rastrear a compra atual durante o loop

	        while (rs.next()) {
	            long compraId = rs.getLong("compra_id");

	            // Cria uma nova instância de Compra apenas quando muda a compra
	            if (compraId != currentCompraId) {
	                Compra compra = new Compra();
	                compra.setId(compraId);
	                compra.setCliente(rs.getLong("cliente_id"));
	                lista.add(compra);
	                currentCompraId = compraId;
	            }

	            // Adiciona um novo item de compra à compra atual
	            ItensCompra itensCompra = new ItensCompra();
	            itensCompra.setId(rs.getLong("item_id"));
	            itensCompra.setIdVenda(compraId);
	            itensCompra.setIdProduto(rs.getLong("produto_id"));
	            itensCompra.setQuantidade(rs.getInt("quantidade"));

	            lista.get(lista.size() - 1).getItens().add(itensCompra);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lista;
	}


}
