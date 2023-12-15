
package ifsp.edu.source.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 * @author
 */
public class DataBaseCom {

    private static Connection connection = null;
    private static Statement statement = null;
    
    public DataBaseCom() {
    	conectar();
    	criarTabelas();
    }

    public static Connection getConnection() {
          conectar();
          return connection;
    }
    
    public static Statement getStatement(){
    	conectar();
        return statement;
    }

    public static Connection conectar() {
        try {
            if(connection==null) {
            	connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            	statement = connection.createStatement();
            	statement.setQueryTimeout(30);  // set timeout to 30 sec.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean criarTabelas() {
    	try {
    	    // Criação da tabela Pessoa
    	    statement.executeUpdate("CREATE TABLE IF NOT EXISTS pessoa (" +
    	                            "id INTEGER PRIMARY KEY," +
    	                            "nome TEXT)");

    	    // Criação da tabela Produto
    	    statement.executeUpdate("CREATE TABLE IF NOT EXISTS produto (" +
    	                            "id INTEGER PRIMARY KEY," +
    	                            "nome TEXT," +
    	                            "quantidade INTEGER," +
    	                            "preco REAL)");

    	    // Criação da tabela Compra
    	    statement.executeUpdate("CREATE TABLE IF NOT EXISTS compra (" +
    	                            "id INTEGER PRIMARY KEY," +
    	                            "cliente_id INTEGER," +
    	                            "data TEXT," +
    	                            "FOREIGN KEY (cliente_id) REFERENCES pessoa(id))");

    	    // Criação da tabela ItensCompra
    	    statement.executeUpdate("CREATE TABLE IF NOT EXISTS itens_compra (" +
    	                            "compra_id INTEGER," +
    	                            "produto_id INTEGER," +
    	                            "quantidade INTEGER," +
    	                            "PRIMARY KEY (compra_id, produto_id)," +
    	                            "FOREIGN KEY (compra_id) REFERENCES compra(id)," +
    	                            "FOREIGN KEY (produto_id) REFERENCES produto(id))");
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
        return true;
    }
    
    public static void desconectar(){
        try {
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseCom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
