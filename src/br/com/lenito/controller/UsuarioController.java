package br.com.lenito.controller;

import java.util.List;

import br.com.lenito.entity.Usuario;
import br.com.lenito.facade.UsuarioFacade;

public class UsuarioController {
	
	private UsuarioFacade facade;

	public UsuarioController() {

		this.facade = new UsuarioFacade();

	}

	public int addUsuario(Usuario usuario) {

		return facade.save(usuario);

	}

	public int alterarUsuario(Usuario usuario) {

		return facade.update(usuario);

	}

	public int excluirUsuario(int id) {

		return facade.remove(id);

	}

	public List<Usuario> findUsuarios() {

		return facade.findAll();

	}

}
