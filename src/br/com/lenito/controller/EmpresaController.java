package br.com.lenito.controller;

import java.util.List;

import br.com.lenito.entity.Empresa;
import br.com.lenito.facade.EmpresaFacade;

public class EmpresaController {
	
	private EmpresaFacade facade;
	
	public EmpresaController(){
		
		this.facade = new EmpresaFacade();
	}
	
	public int addEmpresa(Empresa empresa){
		
		return facade.save(empresa);
	}
	
	public int alterarEmpresa(Empresa empresa){
		
		return facade.update(empresa);
	}
	
	public int excluirEmpresa(int id){
		
		return facade.remove(id);
	}
	
	public List<Empresa> findEmpresas(){
		
		return facade.findAll();
	}

}
