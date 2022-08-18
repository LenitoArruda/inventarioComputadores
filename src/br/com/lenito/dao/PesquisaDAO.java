package br.com.lenito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lenito.entity.Pesquisa;

public class PesquisaDAO implements IPesquisa {

	private static final String sqlFindAll = "SELECT maquina.tipo_maquina, maquina.id_maquina, maquina.nome_maquina, maquina.ip_maquina, maquina.modelo_maquina, "
			+ "maquina.serial_number, maquina.usuario, marca.nome_marca, setor.nome_setor "
			+ "from maquina, marca , setor "
			+ "where maquina.id_marca = marca.id_marca and maquina.id_setor = setor.id_setor";

	@Override
	public List<Pesquisa> findIp(String ip) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Pesquisa> computadores = new ArrayList<Pesquisa>();
		try {

			pstm = conn.prepareStatement("SELECT maquina.tipo_maquina, maquina.id_maquina, maquina.nome_maquina, maquina.ip_maquina, maquina.modelo_maquina, "
					+ "maquina.serial_number, maquina.usuario, marca.nome_marca, setor.nome_setor "
					+ "from maquina, marca , setor "
					+ "where maquina.id_marca = marca.id_marca and maquina.id_setor = setor.id_setor and maquina.ip_maquina like '%"
					+ ip + "%'");
			rs = pstm.executeQuery();

			while (rs.next()) {
				Pesquisa pesquisa = new Pesquisa();
				pesquisa.setId(rs.getInt("maquina.id_maquina"));
				pesquisa.setNome(rs.getString("maquina.nome_maquina"));
				pesquisa.setIp(rs.getString("maquina.ip_maquina"));
				pesquisa.setSetor(rs.getString("setor.nome_setor"));
				pesquisa.setMarca(rs.getString("marca.nome_marca"));
				pesquisa.setModelo(rs.getString("maquina.modelo_maquina"));
				pesquisa.setUsuario(rs.getString("maquina.usuario"));
				pesquisa.setSerialNumber(rs.getString("maquina.serial_number"));
				pesquisa.setTipo(rs.getString("maquina.tipo_maquina"));

				computadores.add(pesquisa);
			}

		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			Conexao.close(conn, pstm, rs);
		}
		return computadores;

	}

	@Override
	public List<Pesquisa> findNome(String nome) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Pesquisa> computadores = new ArrayList<Pesquisa>();
		try {

			pstm = conn.prepareStatement("SELECT maquina.tipo_maquina, maquina.id_maquina, maquina.nome_maquina, maquina.ip_maquina, maquina.modelo_maquina, "
					+ "maquina.serial_number, maquina.usuario, marca.nome_marca, setor.nome_setor "
					+ "from maquina, marca , setor "
					+ "where maquina.id_marca = marca.id_marca and maquina.id_setor = setor.id_setor and maquina.nome_maquina like '%"
					+ nome + "%'");
			rs = pstm.executeQuery();

			while (rs.next()) {
				Pesquisa pesquisa = new Pesquisa();
				pesquisa.setId(rs.getInt("maquina.id_maquina"));
				pesquisa.setNome(rs.getString("maquina.nome_maquina"));
				pesquisa.setIp(rs.getString("maquina.ip_maquina"));
				pesquisa.setSetor(rs.getString("setor.nome_setor"));
				pesquisa.setMarca(rs.getString("marca.nome_marca"));
				pesquisa.setModelo(rs.getString("maquina.modelo_maquina"));
				pesquisa.setUsuario(rs.getString("maquina.usuario"));
				pesquisa.setSerialNumber(rs.getString("maquina.serial_number"));
				pesquisa.setTipo(rs.getString("maquina.tipo_maquina"));

				computadores.add(pesquisa);
			}

		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			Conexao.close(conn, pstm, rs);
		}
		return computadores;
	}

	@Override
	public List<Pesquisa> findUsuario(String usuario) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Pesquisa> computadores = new ArrayList<Pesquisa>();
		try {

			pstm = conn.prepareStatement("SELECT maquina.tipo_maquina, maquina.id_maquina, maquina.nome_maquina, maquina.ip_maquina, maquina.modelo_maquina, "
					+ "maquina.serial_number, maquina.usuario, marca.nome_marca, setor.nome_setor "
					+ "from maquina, marca , setor "
					+ "where maquina.id_marca = marca.id_marca and maquina.id_setor = setor.id_setor and maquina.usuario like '%"
					+ usuario + "%'");
			rs = pstm.executeQuery();

			while (rs.next()) {
				Pesquisa pesquisa = new Pesquisa();
				pesquisa.setId(rs.getInt("maquina.id_maquina"));
				pesquisa.setNome(rs.getString("maquina.nome_maquina"));
				pesquisa.setIp(rs.getString("maquina.ip_maquina"));
				pesquisa.setSetor(rs.getString("setor.nome_setor"));
				pesquisa.setMarca(rs.getString("marca.nome_marca"));
				pesquisa.setModelo(rs.getString("maquina.modelo_maquina"));
				pesquisa.setUsuario(rs.getString("maquina.usuario"));
				pesquisa.setSerialNumber(rs.getString("maquina.serial_number"));
				pesquisa.setTipo(rs.getString("maquina.tipo_maquina"));

				computadores.add(pesquisa);
			}

		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			Conexao.close(conn, pstm, rs);
		}
		return computadores;
	}

	@Override
	public List<Pesquisa> findAll() {

		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Pesquisa> computadores = new ArrayList<Pesquisa>();

		try {

			pstm = conn.prepareStatement(sqlFindAll);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Pesquisa pesquisa = new Pesquisa();
				pesquisa.setId(rs.getInt("maquina.id_maquina"));
				pesquisa.setNome(rs.getString("maquina.nome_maquina"));
				pesquisa.setIp(rs.getString("maquina.ip_maquina"));
				pesquisa.setSetor(rs.getString("setor.nome_setor"));
				pesquisa.setMarca(rs.getString("marca.nome_marca"));
				pesquisa.setModelo(rs.getString("maquina.modelo_maquina"));
				pesquisa.setUsuario(rs.getString("maquina.usuario"));
				pesquisa.setSerialNumber(rs.getString("maquina.serial_number"));
				pesquisa.setTipo(rs.getString("maquina.tipo_maquina"));

				computadores.add(pesquisa);
			}

		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			Conexao.close(conn, pstm, rs);
		}
		return computadores;

	}
}
