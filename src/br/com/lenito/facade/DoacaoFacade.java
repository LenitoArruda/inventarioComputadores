package br.com.lenito.facade;

import java.util.List;

import br.com.lenito.dao.DoacaoDAO;
import br.com.lenito.entity.Doacao;

public class DoacaoFacade {

	private DoacaoDAO dao;
	
	public DoacaoFacade(){
		
		this.dao = new DoacaoDAO();
		
	}
	
	public int save(Doacao doacao) {

		return dao.save(doacao);

	}

	public int update(Doacao doacao) {

		return dao.update(doacao);

	}

	public int remove(int id) {

		return dao.remove(id);

	}

	public List<Doacao> findAll() {
		
		return dao.findAll();

	}
	
}
