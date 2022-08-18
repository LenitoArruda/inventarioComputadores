package br.com.lenito.facade;

import java.util.List;

import br.com.lenito.dao.UsuarioDAO;
import br.com.lenito.entity.Usuario;

public class UsuarioFacade {
	
	private UsuarioDAO dao;
	
	public UsuarioFacade(){
		this.dao = new UsuarioDAO();
	}
	
	public int save (Usuario usuario){
		
		return dao.save(usuario);
	}
	
	public int remove (int id){
		
		return dao.remove(id);
	}
	
	public int update (Usuario usuario){
		
		return dao.update(usuario);
	}
	
	public List<Usuario> findAll(){
		return dao.findAll();
	}
}
