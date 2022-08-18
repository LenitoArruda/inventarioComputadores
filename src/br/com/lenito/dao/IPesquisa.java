package br.com.lenito.dao;

import java.util.List;

import br.com.lenito.entity.Pesquisa;

public interface IPesquisa {
	
	List<Pesquisa> findIp(String ip);
	
	List<Pesquisa> findNome(String nome);
	
	List<Pesquisa> findUsuario(String usuario);
	
	List<Pesquisa> findAll();

}
