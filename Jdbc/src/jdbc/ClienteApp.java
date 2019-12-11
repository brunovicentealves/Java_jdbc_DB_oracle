package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClienteApp {
	
	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String usuario = "system";
	static String senha = "admin";

	static Connection conexao;

	public static void conectar() throws SQLException {

		conexao = DriverManager.getConnection(url, usuario, senha);
		conexao.setAutoCommit(false);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
