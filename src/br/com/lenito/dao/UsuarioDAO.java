package br.com.lenito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lenito.entity.Usuario;

public class UsuarioDAO implements IUsuario {

	private static final String sqlInsert = "insert into usuario(nome_usuario, senha_usuario ) values (?,?)";

	private static final String sqlUpdate = "update usuario set nome_usuario = ?, senha_usuario = ? where id_usuario = ?";

	private static final String sqlRemove = "delete from usuario where id_usuario = ?";

	private static final String sqlFindAll = "select * from usuario order by nome_usuario";

	@Override
	public int save(Usuario usuario) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(sqlInsert);
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getSenha());
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
	public int update(Usuario usuario) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(sqlUpdate);
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getSenha());
			pstm.setInt(3, usuario.getId());
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
	public List<Usuario> findAll() {

		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {

			pstm = conn.prepareStatement(sqlFindAll);
			rs = pstm.executeQuery();

			while (rs.next()) {

				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setSenha(rs.getString("senha_usuario"));
				usuarios.add(usuario);

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
		return usuarios;

	}
}
