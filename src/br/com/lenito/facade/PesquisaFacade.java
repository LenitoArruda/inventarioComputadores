package br.com.lenito.facade;

import java.util.List;

import br.com.lenito.dao.PesquisaDAO;
import br.com.lenito.entity.Pesquisa;

public class PesquisaFacade {

	private PesquisaDAO dao;

	public PesquisaFacade() {

		this.dao = new PesquisaDAO();
	}

	public List<Pesquisa> findIp(String ip) {

		return dao.findIp(ip);

	}

	public List<Pesquisa> findNome(String nome) {

		return dao.findNome(nome);

	}

	public List<Pesquisa> findUsuario(String usuario) {

		return dao.findUsuario(usuario);

	}
	
	public List<Pesquisa> findAll() {

		return dao.findAll();

	}


}
