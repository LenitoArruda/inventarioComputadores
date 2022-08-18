package br.com.lenito.dao;

import java.util.List;

import br.com.lenito.entity.Marca;

public interface IMarca {

	int save(Marca marca);

	int update(Marca marca);

	int remove(int id);

	List<Marca> findAll();

}
