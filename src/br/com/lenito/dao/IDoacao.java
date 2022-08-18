package br.com.lenito.dao;

import java.util.List;

import br.com.lenito.entity.Doacao;

public interface IDoacao {

	int save(Doacao doacao);

	int update(Doacao doacao);

	int remove(int id);

	List<Doacao> findAll();

}
