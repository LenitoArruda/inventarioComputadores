package br.com.lenito.controller;

import java.util.List;

import br.com.lenito.entity.Marca;
import br.com.lenito.facade.MarcaFacade;

public class MarcaController {

	private MarcaFacade facade;

	public MarcaController() {

		this.facade = new MarcaFacade();

	}

	public int addMarca(Marca marca) {

		return facade.save(marca);

	}

	public int alterarMarca(Marca marca) {

		return facade.update(marca);

	}

	public int excluirMarca(int id) {

		return facade.remove(id);

	}

	public List<Marca> findMarcas() {

		return facade.findAll();

	}

}
