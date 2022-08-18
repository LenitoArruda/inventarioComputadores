package br.com.lenito.facade;

import java.util.List;

import br.com.lenito.dao.MarcaDAO;
import br.com.lenito.entity.Marca;

public class MarcaFacade {

	private MarcaDAO dao;

	public MarcaFacade() {

		this.dao = new MarcaDAO();

	}

	public int save(Marca marca) {

		return dao.save(marca);

	}

	public int update(Marca marca) {

		return dao.update(marca);

	}

	public int remove(int id) {

		return dao.remove(id);

	}

	public List<Marca> findAll() {

		return dao.findAll();

	}

}
