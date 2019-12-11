package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ClienteApp {

	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String usuario = "system";
	static String senha = "admin";

	static Connection conexao;

	public static void conectar() throws SQLException {

		conexao = DriverManager.getConnection(url, usuario, senha);
		conexao.setAutoCommit(false);
	}

	public static void desconectar() throws SQLException {

		conexao.close();
	}

	public static void inserir(int id_pessoa , String nome,String cpf) throws SQLException {
		
		String sql = "insert into PESSOA values("+id_pessoa+",'"+nome+",'"+cpf+"')";
		Statement statement = conexao.createStatement();
		statement.execute(sql);
		conexao.commit();
		
	}

	public static void consultar(int id_pessoa) throws SQLException {
		String sql = "select * from PESSOA where id_pessoa ="+id_pessoa+"";
		Statement statement = conexao.createStatement();
		
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {

			JOptionPane.showMessageDialog(null,
					"cpf :" + rs.getInt(1) + "Nome :" + rs.getString(2) + "Cpf :" + rs.getString(3));
		}

	}

	public static void consultarTodosRegistros() throws SQLException {
		
		String sql = "select * from PESSOA ";
		Statement statement = conexao.createStatement();
		
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {

			JOptionPane.showMessageDialog(null,
					"cpf :" + rs.getInt(1) + "Nome :" + rs.getString(2) + "Cpf :" + rs.getString(3));
		}


	}

	public static void alterar() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
