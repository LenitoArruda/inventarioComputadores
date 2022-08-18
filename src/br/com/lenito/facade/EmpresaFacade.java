package br.com.lenito.facade;

import java.util.List;

import br.com.lenito.dao.EmpresaDAO;
import br.com.lenito.entity.Empresa;

public class EmpresaFacade {

	private EmpresaDAO dao;

	public EmpresaFacade() {

		this.dao = new EmpresaDAO();

	}

	public int save(Empresa empresa) {

		return dao.save(empresa);
	}

	public int update(Empresa empresa) {

		return dao.update(empresa);
	}
	
	public int remove(int id){
		
		return dao.remove(id);
	}
	
	public List<Empresa> findAll(){
		
		return dao.findAll();
	}
}
