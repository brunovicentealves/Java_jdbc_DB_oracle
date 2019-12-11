package jdbc;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {

	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String usuario = "system";
	static String senha = "admin";

	static Connection conexao;

	public static void conectar() throws SQLException {

		conexao = DriverManager.getConnection(url, usuario, senha);
		conexao.setAutoCommit(false);
	}

	public static void consultarCliente() throws SQLException {

		String Sql = "select * from PESSOA";

		java.sql.Statement statement = conexao.createStatement();

		ResultSet rs = statement.executeQuery(Sql);

		while (rs.next()) {

			JOptionPane.showMessageDialog(null,
					"cpf :" + rs.getInt(1) + "Nome :" + rs.getString(2) + "Cpf :" + rs.getString(3));
		}
	}

	public static void mostrarMetaInfoDB() throws SQLException {

		DatabaseMetaData meta = conexao.getMetaData();
		String fabrincanteDB = meta.getDatabaseProductName();
		String versaoDB = meta.getDatabaseProductVersion();

		JOptionPane.showMessageDialog(null, "FabricanteDB :" + fabrincanteDB + "-- VersaoDB" + versaoDB);

	}

	public static void main(String[] args) {
		try {
			conectar();
			mostrarMetaInfoDB();
			consultarCliente();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
