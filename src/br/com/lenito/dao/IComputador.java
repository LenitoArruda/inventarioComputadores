package br.com.lenito.dao;

import java.util.List;

import br.com.lenito.entity.Computador;

public interface IComputador {
	
	int save(Computador computador);
	
	
	int update(Computador computador);
	
	
	int remove(int id);
	
	
	List<Computador> findAll();

}
