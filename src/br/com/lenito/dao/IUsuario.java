package br.com.lenito.dao;

import java.util.List;

import br.com.lenito.entity.Usuario;

public interface IUsuario {
	
	int save(Usuario usuario);
	
	int update(Usuario usuario);
	
	int remove(int id);
	
	List<Usuario> findAll();

}
