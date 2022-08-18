package br.com.lenito.facade;

import java.util.List;

import br.com.lenito.dao.ComputadorDAO;
import br.com.lenito.entity.Computador;

public class ComputadorFacade {

	private ComputadorDAO dao;

	public ComputadorFacade(){
		
		this.dao = new ComputadorDAO();
		
	}

	public int save(Computador computador) {

		return dao.save(computador);

	}

	public int update(Computador computador) {

		return dao.update(computador);

	}

	public int remove(int id) {

		return dao.remove(id);

	}

	public List<Computador> findAll() {
		
		return dao.findAll();

	}

}
