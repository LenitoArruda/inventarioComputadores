package br.com.lenito.dao;

import java.util.List;

import br.com.lenito.entity.Setor;

public interface ISetor {

	int save(Setor setor);

	int update(Setor setor);

	int remove(int id);

	List<Setor> findAll();

}
