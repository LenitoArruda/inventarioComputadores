package br.com.lenito.controller;

import java.util.List;

import br.com.lenito.entity.Computador;
import br.com.lenito.facade.ComputadorFacade;

public class ComputadorController {

	private ComputadorFacade facade;

	public ComputadorController() {
		
		this.facade = new ComputadorFacade();
		
	}

	public int addComputador(Computador computador) {
		

		return facade.save(computador);
		
	}

	public int alterarComputador(Computador computador) {

		return facade.update(computador);

	}

	public int excluirComputador(int id) {

		return facade.remove(id);

	}

	public List<Computador> findComputadores() {

		return facade.findAll();
		
	}
}
