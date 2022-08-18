package br.com.lenito.controller;

import java.util.List;

import br.com.lenito.entity.Doacao;
import br.com.lenito.facade.DoacaoFacade;

public class DoacaoController {

	private DoacaoFacade facade;

	public DoacaoController() {

		this.facade = new DoacaoFacade();

	}

	public int addDoacao(Doacao doacao) {

		return facade.save(doacao);

	}

	public int alterarDoacao(Doacao doacao) {

		return facade.update(doacao);

	}

	public int excluirDoacao(int id) {

		return facade.remove(id);

	}

	public List<Doacao> findDoacoes() {

		return facade.findAll();

	}

}
