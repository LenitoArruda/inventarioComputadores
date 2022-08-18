package br.com.lenito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lenito.controller.MarcaController;
import br.com.lenito.controller.SetorController;
import br.com.lenito.entity.Computador;
import br.com.lenito.entity.Marca;
import br.com.lenito.entity.Setor;

public class ComputadorDAO implements IComputador {

	private static final String sqlInsert = "insert into maquina"
			+ "(nome_maquina, ip_maquina, modelo_maquina, serial_number, id_marca, id_setor, usuario, tipo_maquina)"
			+ "values (?,?,?,?,?,?,?,?)";

	private static final String sqlUpdate = "update maquina"
			+ " set nome_maquina = ?, ip_maquina = ?, modelo_maquina = ?, serial_number = ?, id_marca = ?, id_setor = ?, usuario = ?, tipo_maquina = ?"
			+ " where id_maquina = ?"; 
	
	private static final String sqlRemove = "delete from maquina where id_maquina = ?";

	private static final String sqlFindAll = "SELECT maquina.tipo_maquina, maquina.id_maquina, maquina.nome_maquina, maquina.ip_maquina, maquina.modelo_maquina, "
			+ "maquina.serial_number, maquina.usuario, marca.nome_marca, setor.nome_setor "
			+ "from maquina, marca , setor "
			+ "where maquina.id_marca = marca.id_marca and maquina.id_setor = setor.id_setor";

	
	

	@Override
	public int save(Computador computador) {
		
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(sqlInsert);
			pstm.setString(1, computador.getNome());
			pstm.setString(2, computador.getIp());
			pstm.setString(3, computador.getModelo());
			pstm.setString(4, computador.getSerialNumber());
			pstm.setInt(5, buscaIdMarca(computador.getMarca()));
			pstm.setInt(6, buscaIdSetor(computador.getSetor()));
			pstm.setString(7, computador.getUsuario());
			pstm.setString(8, computador.getTipo());
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
	public int update(Computador computador) {
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(sqlUpdate);
			pstm.setString(1, computador.getNome());
			pstm.setString(2, computador.getIp());
			pstm.setString(3, computador.getModelo());
			pstm.setString(4, computador.getSerialNumber());
			pstm.setInt(5, buscaIdMarca(computador.getMarca()));
			pstm.setInt(6, buscaIdSetor(computador.getSetor()));
			pstm.setString(7, computador.getUsuario());
			pstm.setString(8, computador.getTipo());
			pstm.setInt(9, computador.getId());
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
	public List<Computador> findAll() {
		
		Connection conn = Conexao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Computador> computadores = new ArrayList<Computador>();
		try {
			
			pstm = conn.prepareStatement(sqlFindAll);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				Computador computador = new Computador();
				computador.setId(rs.getInt("maquina.id_maquina"));
				computador.setNome(rs.getString("maquina.nome_maquina"));
				computador.setIp(rs.getString("maquina.ip_maquina"));
				computador.setSetor(rs.getString("setor.nome_setor"));
				computador.setMarca(rs.getString("marca.nome_marca"));
				computador.setModelo(rs.getString("maquina.modelo_maquina"));
				computador.setUsuario(rs.getString("maquina.usuario"));
				computador.setSerialNumber(rs.getString("maquina.serial_number"));
				computador.setTipo(rs.getString("maquina.tipo_maquina"));
				
				computadores.add(computador);
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


	
	public int buscaIdSetor(String nome){
		List<Setor> setorList = new SetorController().findSetores(); 
		for(int i = 0; i< setorList.size(); i++){
			Setor s = setorList.get(i);
			if(s.getNome().equals(nome)){
				return s.getId();
			}
		}
		return 0;
	}
	
	public int buscaIdMarca(String nome){
		List<Marca> marcaList = new MarcaController().findMarcas(); 
		for(int i = 0; i< marcaList.size(); i++){
			Marca m = marcaList.get(i);
			if(m.getNome().equals(nome)){
				return m.getId();
			}
		}
		return 0;
	}


}
