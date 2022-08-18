package br.com.lenito.app;
import br.com.lenito.dao.Conexao;
import br.com.lenito.view.LoginForm;




public class Main {

	public static void main(String[] args) {
		
		Conexao.createTable();
		new LoginForm();
		

	}

}
