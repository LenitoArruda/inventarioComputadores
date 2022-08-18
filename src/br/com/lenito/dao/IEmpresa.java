package br.com.lenito.dao;

import java.util.List;

import br.com.lenito.entity.Empresa;

public interface IEmpresa {

	int save(Empresa empresa);

	int update(Empresa empresa);

	int remove(int id);

	List<Empresa> findAll();

	
}
