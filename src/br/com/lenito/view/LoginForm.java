package br.com.lenito.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.lenito.controller.UsuarioController;
import br.com.lenito.entity.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

	static final long serialVersionUID = 1L;
	private JLabel lbSenha, lbUsuario;
	private JTextField txtUsuario;
	private JPasswordField pswSenha;
	private JButton btnLogar;
	private JPanel panelLogin, panel;

	public LoginForm() {

		// Setando configurações do form
		super("Login");
		setVisible(true);
		panel = new JPanel();
		setContentPane(panel);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/LogoIntercam.jpg")).getImage());
		this.setLocationRelativeTo(null);
		setMinimumSize(new Dimension(308, 205));
		panel.setVisible(true);
		panel.setLayout(null);
	
		
		//Criando painel
		panelLogin = new JPanel();
		panel.add(panelLogin);
		panelLogin.setBounds(3, 1, 284, 161);
		panelLogin.setBorder(BorderFactory.createTitledBorder("Seja Bem Vindo!"));
		panelLogin.setLayout(null);
		
		
		// Criando componentes
		
		txtUsuario = new JTextField(50);
		panelLogin.add(txtUsuario);
		txtUsuario.setBounds(91, 48, 172, 20);
		
		lbUsuario = new JLabel("Usuário");
		panelLogin.add(lbUsuario);
		lbUsuario.setBounds(39, 48, 46, 20);
		
		lbSenha = new JLabel("Senha");
		panelLogin.add(lbSenha);
		lbSenha.setBounds(39, 79, 46, 20);
		
		pswSenha = new JPasswordField(50);
		panelLogin.add(pswSenha);
		pswSenha.setBounds(91, 79, 172, 20);
			
		btnLogar = new JButton("Login");
		panelLogin.add(btnLogar);
		btnLogar.setBounds(91, 110, 89, 20);
		
		

		// COnfigurando botao logar
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String senha = String.valueOf(pswSenha.getPassword());
				verificaLogin(txtUsuario.getText(), senha);
			}
		});

	}

	public void verificaLogin(String nome, String senha) {

		List<Usuario> usuarioList = new UsuarioController().findUsuarios();
		int count = 0;
		for (int i = 0; i < usuarioList.size(); i++) {
			Usuario u = usuarioList.get(i);
			if (u.getNome().equals(nome) == true && u.getSenha().equals(senha) == true) {

				new PrincipalForm(nome);
				this.dispose();
				count++;

			}

		}

		if (count != 1) {
			JOptionPane.showMessageDialog(this, "Usuário ou senha inválido!");
		}
	}

}
