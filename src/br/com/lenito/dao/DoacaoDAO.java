package br.com.lenito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import br.com.lenito.entity.Doacao;

public class DoacaoDAO implements IDoacao {

	private static final String sqlInsert = "insert into doacao"
			+ "(tipo_doacao, marca_doacao, modelo_doacao, serial_number, adicional, qtd_doacao)"
			+ "values (?,?,?,?,?,?)";

	private static final String sqlUpdate = "update doacao"
			+ " set tipo_doacao = ?, marca_doacao = ?, modelo_doacao = ?, serial_number = ?, adicional = ?, qtd_doacao = ?"
			+ " where id_doacao = ?";

	private static final String sqlRemove = "delete from doacao where id_doacao = ?";

	private static final String sqlFindAll = "SELECT * from doacao ORDER BY tipo_doacao";

	@Override
	public int save(Doacao doacao) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(sqlInsert);
			pstm.setString(1, doacao.getTipo());
			pstm.setString(2, doacao.getMarca());
			pstm.setString(3, doacao.getModelo());
			pstm.setString(4, doacao.getSerialNumber());
			pstm.setString(5, doacao.getAdicional());
			pstm.setInt(6, doacao.getQuantidade());
			result = pstm.executeUpdate();

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
			Conexao.close(conn, pstm, null);
		}
		return result;
	}

	@Override
	public int update(Doacao doacao) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(sqlUpdate);
			pstm.setString(1, doacao.getTipo());
			pstm.setString(2, doacao.getMarca());
			pstm.setString(3, doacao.getModelo());
			pstm.setString(4, doacao.getSerialNumber());
			pstm.setString(5, doacao.getAdicional());
			pstm.setInt(6, doacao.getQuantidade());
			pstm.setInt(7, doacao.getId());
			result = pstm.executeUpdate();

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
			Conexao.close(conn, pstm, null);
		}
		return result;
	}

	@Override
	public int remove(int id) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {

			pstm = conn.prepareStatement(sqlRemove);
			pstm.setInt(1, id);
			result = pstm.executeUpdate();

		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			Conexao.close(conn, pstm, null);
		}
		return result;
	}

	@Override
	public List<Doacao> findAll() {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Doacao> doacoes = new ArrayList<Doacao>();
		try {

			pstm = conn.prepareStatement(sqlFindAll);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Doacao doacao = new Doacao();
				doacao.setAdicional(rs.getString("adicional"));
				doacao.setId(rs.getInt("id_doacao"));
				doacao.setMarca(rs.getString("marca_doacao"));
				doacao.setModelo(rs.getString("modelo_doacao"));
				doacao.setQuantidade(rs.getInt("qtd_doacao"));
				doacao.setSerialNumber(rs.getString("serial_number"));
				doacao.setTipo(rs.getString("tipo_doacao"));
				doacoes.add(doacao);
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
		return doacoes;
	}

}
