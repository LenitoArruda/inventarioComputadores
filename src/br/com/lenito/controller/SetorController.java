package br.com.lenito.controller;

import java.util.List;


import br.com.lenito.entity.Setor;
import br.com.lenito.facade.SetorFacade;

public class SetorController {
	
	private SetorFacade facade;

	public SetorController() {

		this.facade = new SetorFacade();

	}

	public int addSetor(Setor setor) {

		return facade.save(setor);

	}

	public int alterarSetor(Setor setor) {

		return facade.update(setor);

	}

	public int excluirSetor(int id) {

		return facade.remove(id);

	}

	public List<Setor> findSetores() {

		return facade.findAll();

	}

}
