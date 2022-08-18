package br.com.lenito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lenito.entity.Setor;

public class SetorDAO implements ISetor {
	
	private static final String sqlInsert = "insert into setor(nome_setor) values (?)";

	private static final String sqlUpdate = "update setor set nome_setor = ? where id_setor = ?";

	private static final String sqlRemove = "delete from setor where id_setor = ?";

	private static final String sqlFindAll = "select * from setor order by nome_setor";

	@Override
	public int save(Setor setor) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(sqlInsert);
			pstm.setString(1, setor.getNome());
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
	public int update(Setor setor) {

		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(sqlUpdate);
			pstm.setString(1, setor.getNome());
			pstm.setInt(2, setor.getId());
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
	public List<Setor> findAll() {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Setor> setores = new ArrayList<Setor>();
		try {

			pstm = conn.prepareStatement(sqlFindAll);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Setor setor = new Setor();
				setor.setId(rs.getInt("id_setor"));
				setor.setNome(rs.getString("nome_setor"));

				setores.add(setor);
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
		return setores;

	}

}
