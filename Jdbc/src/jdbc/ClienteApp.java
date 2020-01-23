package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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

	public static void inserir(int id_pessoa, String nome, String cpf) throws SQLException {

		String sql = "insert into PESSOA (id_pessoa,nome,cpf) values('" + id_pessoa + "','" + nome + "','" + cpf + "')";
		Statement statement = conexao.createStatement();
		statement.execute(sql);
		conexao.commit();

	}

	public static void consultar(int id_pessoa) throws SQLException {
		String sql = "select * from PESSOA where id_pessoa =" + id_pessoa + "";
		Statement statement = conexao.createStatement();

		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {

			System.out.println("cpf :" + rs.getInt(1) + "Nome :" + rs.getString(2) + "Cpf :" + rs.getString(3));
		}

	}

	public static void consultarTodosRegistros() throws SQLException {

		String sql = "select * from PESSOA ";
		Statement statement = conexao.createStatement();

		ResultSet rs = statement.executeQuery(sql);
		int  contadorRegistros = 0;

		while (rs.next()) {

			System.out.println("ID :" + rs.getInt(1) + "Nome :" + rs.getString(2) + "Cpf :" + rs.getString(3));
			System.out.println();
			System.out.println("===============================================================================");
			contadorRegistros++;
		}
		System.out.println("Quantidade de Registros :"+contadorRegistros);
	}

	public static void alterar(int id_pessoa , String nome , String cpf) throws SQLException {
		
		String sql ="update PESSOA set nome='"+nome+"'where cpf="+cpf;
		
		Statement statement = conexao.createStatement();
		statement.execute(sql);
		conexao.commit();

	}
	
	public static void  excluir(int id_pessoa) throws SQLException {
		String sql = "delete from PESSOA where id_pessoa="+id_pessoa;
		Statement statement  = conexao.createStatement();
		statement.execute(sql);
		conexao.commit();
		
		
	}

	public static void main(String[] args) throws SQLException, IOException {
		
		conectar();
		
		Scanner sc = new Scanner(System.in);
		
		int opcao = 0;
		
		int id_pessoa;
		String nome,cpf;
		
		do{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("<=============== Sistema de Gerenciamento de Clientes ==============>");
			System.out.println("Digite [1] para consultar todos os clientes =========================");
			System.out.println("Digite [2] para consultar cliente especifico=========================");
			System.out.println("Digite [3] para cadastrar novo cliente ==============================");
			System.out.println("Digite [4] para alterar um cliente ==================================");
			System.out.println("Digite [5] para excluir um cliente ==================================");
			System.out.println("Digite [6] sair do sistema ==========================================");
			System.out.println("<===================================================================>");
			opcao = sc.nextInt();
			
			
			switch (opcao) {
			
			case 1: //consultar todos
				System.out.println();
				System.out.println();
				System.out.println();
				
				System.out.println("[1]=============== CONSULTAR TODOS OS REGISTROS ===============================");
				System.out.println("===============================================================================");
				System.out.println();
				consultarTodosRegistros();
				
				break;
			case 2: // cliente especifico
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("[2] Consultar Cliente");
				System.out.println("informar o ID do colaborador >>>");
				id_pessoa = sc.nextInt();
				
				if(id_pessoa!=0) {
					
					consultar(id_pessoa);
				}
				
				break;
			case 3 : // novo cliente
				System.out.println();
				System.out.println();
				System.out.println();
				
				System.out.println("[3] Cadastrar Cliente");
				System.out.println("informe o nome : ");
				nome = sc.next();
				sc.nextLine();
				System.out.println("informe cpf");
				cpf = sc.next();
				System.out.println("informe id:");
				id_pessoa = sc.nextInt();
				inserir(id_pessoa, nome, cpf);
					
				break;
			case 4: //alterar cliente
				System.out.println("[4]alterar cliente");
				System.out.println("digite o id do cliente que você deseja alterar os dados");
				id_pessoa= sc.nextInt();
				consultar(id_pessoa);
				
				System.out.println("digite o nome Novamente:");
				nome=sc.next();
				System.out.println("digite o Cpf novamente:");
				cpf=sc.next();
				alterar(id_pessoa, nome, cpf);
				
				
				break;
				
			case 5: //excluir cliente
				System.out.println("[5]excluir cliente");
				System.out.println("informar o id do cliente que você deseja Excluir :");
				id_pessoa = sc.nextInt();
				excluir(id_pessoa);
				break;
				
				
			case 6 : // sair do sistema
				System.out.println("encerrando sistema........");
				break;
			}
		}while(opcao!=6);
		
		
		

	}

}
