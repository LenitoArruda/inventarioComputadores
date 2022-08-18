package br.com.lenito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lenito.entity.Empresa;

public class EmpresaDAO implements IEmpresa {

	private static final String sqlInsert = "insert into empresa(nome_empresa, cnpj_empresa, endereco_empresa, cep_empresa, "
			+ "contato_empresa, cidade_empresa, uf_empresa, telefone_empresa) values (?,?,?,?,?,?,?,?)";

	private static final String sqlUpdate = "update empresa set nome_empresa = ?, cnpj_empresa = ?, endereco_empresa = ?, "
			+ "cep_empresa = ?, contato_empresa = ?, cidade_empresa = ?, uf_empresa = ?, telefone_empresa = ?  "
			+ "where id_empresa = ?";

	private static final String sqlRemove = "delete from empresa where id_empresa = ?";

	private static final String sqlFindAll = "select * from empresa order by nome_empresa";

	@Override
	public int save(Empresa empresa) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {

			pstm = conn.prepareStatement(sqlInsert);
			pstm.setString(1, empresa.getNome());
			pstm.setString(2, empresa.getCnpj());
			pstm.setString(3, empresa.getEndereco());
			pstm.setString(4, empresa.getCep());
			pstm.setString(5, empresa.getContato());
			pstm.setString(6, empresa.getCidade());
			pstm.setString(7, empresa.getUf());
			pstm.setString(8, empresa.getTelefone());
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
	public int update(Empresa empresa) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;

		try {

			pstm = conn.prepareStatement(sqlUpdate);
			pstm.setString(1, empresa.getNome());
			pstm.setString(2, empresa.getCnpj());
			pstm.setString(3, empresa.getEndereco());
			pstm.setString(4, empresa.getCep());
			pstm.setString(5, empresa.getContato());
			pstm.setString(6, empresa.getCidade());
			pstm.setString(7, empresa.getUf());
			pstm.setString(8, empresa.getTelefone());
			pstm.setInt(9, empresa.getId());
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
	public List<Empresa> findAll() {

		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Empresa> empresas = new ArrayList<Empresa>();

		try {

			pstm = conn.prepareStatement(sqlFindAll);
			rs = pstm.executeQuery();

			while (rs.next()) {

				Empresa empresa = new Empresa();
				empresa.setId(rs.getInt("id_empresa"));
				empresa.setNome(rs.getString("nome_empresa"));
				empresa.setCnpj(rs.getString("cnpj_empresa"));
				empresa.setEndereco(rs.getString("endereco_empresa"));
				empresa.setCep(rs.getString("cep_empresa"));
				empresa.setContato(rs.getString("contato_empresa"));
				empresa.setCidade(rs.getString("cidade_empresa"));
				empresa.setUf(rs.getString("uf_empresa"));
				empresa.setTelefone(rs.getString("telefone_empresa"));
				empresas.add(empresa);
			}

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

			Conexao.close(conn, pstm, rs);
		}
		return empresas;

	}

}
