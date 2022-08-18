package br.com.lenito.dao;

import java.sql.*;


public class Conexao {


	private static final String URL_MYSQL = "jdbc:mysql://localhost/inventario_computadores?useTimezone=true&serverTimezone=UTC";
	private static final String DRIVER_CLASS_MYSQL = "com.mysql.cj.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASS = "lenito478811901";
	
	public static Connection getConnection() {
		System.out.println("Conectando ao Banco de Dados");
		try {
			Class.forName(DRIVER_CLASS_MYSQL);
			return DriverManager.getConnection(URL_MYSQL, USER, PASS);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

		return null;

	}

	public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}

			if (stmt != null) {
				stmt.close();
			}

			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Fechando conexão");
	}

	public static void createDataBase(Connection conn, PreparedStatement stmt) {
		try {

			stmt = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS inventario_intercam");
			stmt.execute();
			System.out.println("Banco OK!");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void createTable() {
		Connection connection = getConnection();
		PreparedStatement stmt = null;
		createDataBase(connection, stmt);
		String sqlSetor = "CREATE TABLE IF NOT EXISTS setor (" + "  id_setor int NOT NULL AUTO_INCREMENT,"
				+ "  nome_setor VARCHAR(25) NOT NULL," + "  CONSTRAINT PK_SETOR PRIMARY KEY (id_setor)" + ");";

		String sqlMarca = "CREATE TABLE IF NOT EXISTS marca (" + "  id_marca int NOT NULL AUTO_INCREMENT,"
				+ "  nome_marca VARCHAR(30) NOT NULL," + "  CONSTRAINT PK_MARCA PRIMARY KEY (id_marca)" + ");";

		String sqlMaquina = "CREATE TABLE IF NOT EXISTS maquina (" + "  id_maquina int NOT NULL AUTO_INCREMENT,"
				+ "  nome_maquina VARCHAR(15) NOT NULL," + "  ip_maquina VARCHAR(15) NOT NULL,"
				+ "  modelo_maquina VARCHAR(50) NOT NULL," + "  serial_number VARCHAR(50) NOT NULL,"
				+ "  id_marca int NOT NULL," + "  id_setor int NOT NULL," + "  usuario VARCHAR(20) NOT NULL,"
				+ "  tipo_maquina VARCHAR(20) NOT NULL," + "  CONSTRAINT PK_MAQUINA PRIMARY KEY (id_maquina),"
				+ "  CONSTRAINT FK_SETOR FOREIGN KEY (id_setor) REFERENCES setor (id_setor),"
				+ "  CONSTRAINT FK_MARCA FOREIGN KEY (id_marca) REFERENCES marca (id_marca)" + ");";

		String sqlDoacao = "CREATE TABLE IF NOT EXISTS doacao (" + "  id_doacao int NOT NULL AUTO_INCREMENT,"
				+ "  tipo_doacao VARCHAR(30) NOT NULL," + "  marca_doacao VARCHAR(30) NOT NULL,"
				+ "  modelo_doacao VARCHAR(50)," + "  serial_number VARCHAR(50)," + "  qtd_doacao int NOT NULL,"
				+ "  adicional VARCHAR(20)," + "  CONSTRAINT PK_DOACAO PRIMARY KEY (id_doacao)" + ");";

		String sqlEmpresa = "CREATE TABLE IF NOT EXISTS empresa (" + "  id_empresa int NOT NULL AUTO_INCREMENT,"
				+ "  cnpj_empresa VARCHAR(20) NOT NULL," + "  nome_empresa VARCHAR(50) NOT NULL,"
				+ " endereco_empresa VARCHAR(100) NOT NULL," + " cep_empresa VARCHAR(20) NOT NULL,"
				+ " contato_empresa VARCHAR(30) NOT NULL," + " cidade_empresa VARCHAR(30) NOT NULL,"
				+ " uf_empresa VARCHAR (10) NOT NULL," + " telefone_empresa VARCHAR(20) NOT NULL,"
				+ "  CONSTRAINT PK_MAQUINA PRIMARY KEY (id_empresa)" + ");";

		String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuario (" + "  id_usuario int NOT NULL AUTO_INCREMENT,"
				+ "  nome_usuario VARCHAR(20) NOT NULL," + "  senha_usuario VARCHAR(20) NOT NULL,"
				+ "  CONSTRAINT PK_MAQUINA PRIMARY KEY (id_usuario)" + ");";
		
	//	String sqlCriaUsuario = "INSERT INTO usuario(nome_usuario, senha_usuario) VALUES('admin','123')";
 	

		try {
			// Criação da tabela setor
			stmt = connection.prepareStatement(sqlSetor);
			stmt.execute();
			// Criação da tabela marca
			stmt = connection.prepareStatement(sqlMarca);
			stmt.execute();
			// Criação da tabela maquina
			stmt = connection.prepareStatement(sqlMaquina);
			stmt.execute();
			// Criação da tabela doacao
			stmt = connection.prepareStatement(sqlDoacao);
			stmt.execute();
			// Criação da tabela empresa
			stmt = connection.prepareStatement(sqlEmpresa);
			stmt.execute();
			// Criação da tabela usuario
			stmt = connection.prepareStatement(sqlUsuario);
			stmt.execute();
			// Criação da tabela usuario
			//stmt = connection.prepareStatement(sqlCriaUsuario);
			//stmt.execute();
			System.out.println("Tabelas OK!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, stmt, null);
		}
	}
}