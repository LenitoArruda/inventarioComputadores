package br.com.lenito.controller;

import java.util.List;

import br.com.lenito.entity.Pesquisa;
import br.com.lenito.facade.PesquisaFacade;

public class PesquisaController {

	private PesquisaFacade facade;

	public PesquisaController() {

		this.facade = new PesquisaFacade();

	}

	public List<Pesquisa> findIp(String ip) {

		return facade.findIp(ip);

	}

	public List<Pesquisa> findNome(String nome) {

		return facade.findNome(nome);

	}

	public List<Pesquisa> findUsuario(String usuario) {

		return facade.findUsuario(usuario);

	}
	
	public List<Pesquisa> findAll() {

		return facade.findAll();

	}
}
