package br.com.lenito.facade;

import java.util.List;


import br.com.lenito.dao.SetorDAO;
import br.com.lenito.entity.Setor;

public class SetorFacade {
	
	private SetorDAO dao;

	public SetorFacade() {

		this.dao = new SetorDAO();

	}

	public int save(Setor setor) {

		return dao.save(setor);

	}

	public int update(Setor setor) {

		return dao.update(setor);

	}

	public int remove(int id) {

		return dao.remove(id);

	}

	public List<Setor> findAll() {

		return dao.findAll();

	}


}
