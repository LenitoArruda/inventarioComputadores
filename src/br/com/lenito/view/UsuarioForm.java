package br.com.lenito.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.com.lenito.controller.UsuarioController;
import br.com.lenito.entity.Usuario;
import br.com.lenito.table.UsuarioTableModel;
import net.miginfocom.swing.MigLayout;

public class UsuarioForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lbUsuario, lbSenha, lbConfSenha;
	private JTextField txtUsuario;
	private JPasswordField pswSenha, pswConfSenha;
	private JPanel panelAdd, panelTable, panelButtons;
	private JButton btnNew, btnSave, btnRemove, btnUpdate, btnCancel;
	private JTable table;
	private JScrollPane scrollPane;

	private List<Usuario> usuarioList;
	private int idUsuario;

	private int salvar = 1;
	private int selecionar = 0;

	public UsuarioForm() {

		super("Cadastro de Usu�rios");
		setContentPane(new JPanel());
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/LogoIntercam.jpg")).getImage());

		// Cria��o do painel Add
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Usu�rio"));
		panelAdd.setBounds(5, 0, 470, 100);

		// Cria��o dos labels
		lbUsuario = new JLabel("Usu�rio");
		lbSenha = new JLabel("Senha");
		lbConfSenha = new JLabel("Conf. Senha");

		// Cria��o dos txts e PasswordFied
		txtUsuario = new JTextField(50);
		pswSenha = new JPasswordField(50);
		pswConfSenha = new JPasswordField(50);

		// Adicionando os txts e os labels no painel
		panelAdd.add(lbUsuario);
		panelAdd.add(txtUsuario, "span, growx");
		panelAdd.add(lbSenha);
		panelAdd.add(pswSenha, "span, growx");
		panelAdd.add(lbConfSenha);
		panelAdd.add(pswConfSenha, "span, growx");

		// Cria��o do painel dos bot�es
		panelButtons = new JPanel(new MigLayout());
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 105, 470, 40);

		// Cria��o dos bot�es
		ClassLoader loader = getClass().getClassLoader();
		btnNew = new JButton(new ImageIcon(loader.getResource("img/new.png")));
		btnNew.setToolTipText("Novo");
		btnSave = new JButton(new ImageIcon(loader.getResource("img/save.png")));
		btnSave.setToolTipText("Salvar");
		btnCancel = new JButton(new ImageIcon(loader.getResource("img/cancel.png")));
		btnCancel.setToolTipText("Cancelar");
		btnRemove = new JButton(new ImageIcon(loader.getResource("img/trash.png")));
		btnRemove.setToolTipText("Excluir");
		btnUpdate = new JButton(new ImageIcon(loader.getResource("img/edit.png")));
		btnUpdate.setToolTipText("Alterar");

		// Incluindo os bot�es no painel
		panelButtons.add(btnNew, "gapleft 90");
		panelButtons.add(btnCancel);
		panelButtons.add(btnSave, "gap unrelated");
		panelButtons.add(btnUpdate, "gap unrelated");
		panelButtons.add(btnRemove);

		// Cria��o do painel da tabela
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Usu�rios"));
		panelTable.setBounds(5, 150, 470, 240);

		// Cria��o da tabela
		table = new JTable();

		// Cria��o da barra de rolagem da tabela
		scrollPane = new JScrollPane(table);

		// Adicionando a tabela no painel de tabela
		panelTable.add(scrollPane);

		// Atualizar a lista
		refreshTable();
		// Bloquear txts para edi��o
		enableFields(selecionar);

		// Adicionando os paineis no form
		getContentPane().add(panelAdd);
		getContentPane().add(panelButtons);
		getContentPane().add(panelTable);

		// Setando configura��es do form
		setMinimumSize(new Dimension(495, 435));
		setVisible(true);

		// Configurando o bot�o Save
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onSaveUsuario();

			}
		});

		// Configurando o Cancel
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enableFields(selecionar);
				limparCampos();

			}
		});

		// Configurando o bot�o New
		btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onNovoUsuario();

			}
		});

		// Configurando o bot�o Remove
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onRemoverUsuario();

			}
		});

		// Configurando o bot�o Update
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onAlterarUsuario();

			}
		});

		// Setando os txts para atualizar de acordo com a linha selecionada da
		// tabela
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getRowSelectionAllowed() == true) {
					int rowIndex = table.getSelectedRow();
					Usuario usuario = new UsuarioTableModel(usuarioList).get(rowIndex);
					idUsuario = usuario.getId();

					txtUsuario.setText(usuario.getNome());
				}

			}
		});

	}

	// M�todo do bot�o New
	private void onNovoUsuario() {

		idUsuario = 0;
		enableFields(salvar);
		limparCampos();

	}

	// M�todo do bot�o Remove
	private void onRemoverUsuario() {
		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o usu�rio a ser removido!");
			return;
		}

		Usuario usuario = new UsuarioTableModel(usuarioList).get(rowIndex);
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar exclus�o?", "Excluir usu�rio",
				JOptionPane.YES_NO_OPTION);
		if (confirm != 0) {
			return;
		}

		int result = new UsuarioController().excluirUsuario(usuario.getId());
		if (result == 1) {

			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
			enableFields(selecionar);
			limparCampos();
		} else {

			JOptionPane.showMessageDialog(this, "Tente Novamente!");

		}
	}

	// M�todo do bot�o Update
	private void onAlterarUsuario() {

		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o usu�rio a ser alterado!");
			return;
		}
		Usuario usuario = new UsuarioTableModel(usuarioList).get(rowIndex);
		idUsuario = usuario.getId();

		txtUsuario.setText(usuario.getNome());
		enableFields(salvar);

	}

	// M�todo do bot�o Save
	private void onSaveUsuario() {

		Usuario usuario = new Usuario();
		String senha = String.valueOf(pswSenha.getPassword());
		String confSenha = String.valueOf(pswConfSenha.getPassword());
		
		// Verifica��o de senha
		if (senha.length() < 4) {
			JOptionPane.showMessageDialog(this, "A senha deve conter no m�nimo 4 caracters!");
			return;
		} else if (senha.equals(confSenha) == false) {
			JOptionPane.showMessageDialog(this, "As senhas digitadas n�o s�o iguais!");
			return;
		}
		// Verifica��o se todos os campos foram preenchidos
		if (txtUsuario.getText().length() > 0) {

			usuario.setNome(txtUsuario.getText());
			usuario.setSenha(senha);
		} else {
			JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
			return;
		}

		int result = 0;

		if (idUsuario == 0) {

			result = new UsuarioController().addUsuario(usuario);

		} else {

			usuario.setId(idUsuario);
			result = new UsuarioController().alterarUsuario(usuario);
			idUsuario = 0;
		}

		if (result == 1) {

			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
			enableFields(selecionar);
			refreshTable();
			limparCampos();

		} else {

			JOptionPane.showMessageDialog(this, "Tente Novamente!");

		}

	}

	// M�todo para limpar os campos
	private void limparCampos() {

		txtUsuario.setText("");
		pswConfSenha.setText("");
		pswSenha.setText("");

	}

	// M�todo para desabilitar e habilitar os elementos do form
	private void enableFields(int i) {
		if (i == 0) {

			btnRemove.setEnabled(true);
			btnNew.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnCancel.setEnabled(false);
			btnSave.setEnabled(false);
			txtUsuario.setEnabled(false);
			pswConfSenha.setEnabled(false);
			pswSenha.setEnabled(false);
			table.setRowSelectionAllowed(true);

		} else {

			btnRemove.setEnabled(false);
			btnNew.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnCancel.setEnabled(true);
			btnSave.setEnabled(true);
			txtUsuario.setEnabled(true);
			pswConfSenha.setEnabled(true);
			pswSenha.setEnabled(true);
			table.setRowSelectionAllowed(false);
		}

	}

	// M�todo para atualizar a tabela
	private void refreshTable() {

		usuarioList = new UsuarioController().findUsuarios();

		if (usuarioList != null) {

			table.setModel(new UsuarioTableModel(usuarioList));

		}
	}

}
