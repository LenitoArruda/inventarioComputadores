package br.com.lenito.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.com.lenito.cb.UfCB;
import br.com.lenito.controller.EmpresaController;
import br.com.lenito.entity.Empresa;
import br.com.lenito.entity.Uf;
import br.com.lenito.table.EmpresaTableModel;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmpresaForm extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable tabela;
	private JPanel panelAdd, panelButtons, panelTable;
	private JScrollPane scrollPane;
	private JLabel lbNome, lbCnpj, lbCidade, lbCep, lbContato, lbTelefone, lbEndereco, lbUf;
	private JTextField txtNome, txtCnpj, txtCidade, txtCep, txtContato, txtTelefone, txtEndereco;
	private JButton btnNew, btnUpdate, btnSave, btnCancel, btnRemove;
	private int idEmpresa;
	private final int salvar = 1;
	private final int selecionar = 0;
	private List<Empresa> empresaList;

	private JComboBox<Uf> ufCb;

	public EmpresaForm() {

		// Configurações do form
		super("Cadastro de Empresas");
		setContentPane(new JPanel());
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/LogoIntercam.jpg")).getImage());
		setMinimumSize(new Dimension(465, 585));
		setResizable(false);
		setVisible(true);

		// Criação dos labels
		lbNome = new JLabel("Nome");
		lbCnpj = new JLabel("CNPJ");
		lbCep = new JLabel("CEP");
		lbCidade = new JLabel("Cidade");
		lbContato = new JLabel("Contato");
		lbEndereco = new JLabel("Endereço");
		lbTelefone = new JLabel("Telefone");
		lbUf = new JLabel("UF");

		// Criação dos texts Fields
		txtCnpj = new JTextField(20);
		txtNome = new JTextField(50);
		txtCep = new JTextField(10);
		txtCidade = new JTextField(50);
		txtContato = new JTextField(50);
		txtEndereco = new JTextField(150);
		txtTelefone = new JTextField(20);

		// Criação do Combo Box UF
		ufCb = new JComboBox<Uf>();
		ufCb.setModel(new UfCB());
		
		// Criação do painel Add
		panelAdd = new JPanel();
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Empresa"));
		getContentPane().add(panelAdd);
		panelAdd.setLayout(null);
		panelAdd.setBounds(5, 0, 442, 218);

		// Adicionando componentes do painel
		panelAdd.add(lbNome);
		panelAdd.add(txtNome);

		panelAdd.add(lbCnpj);
		panelAdd.add(txtCnpj);

		panelAdd.add(lbCep);
		panelAdd.add(txtCep);

		panelAdd.add(lbCidade);
		panelAdd.add(txtCidade);

		panelAdd.add(lbContato);
		panelAdd.add(txtContato);

		panelAdd.add(lbEndereco);
		panelAdd.add(txtEndereco);

		panelAdd.add(lbTelefone);
		panelAdd.add(txtTelefone);

		panelAdd.add(lbUf);
		panelAdd.add(ufCb);

		// Posicionamento dos componentes
		lbNome.setBounds(13, 23, 53, 20);
		txtNome.setBounds(76, 23, 353, 20);

		lbCnpj.setBounds(13, 57, 53, 14);
		txtCnpj.setBounds(76, 54, 353, 20);

		lbCep.setBounds(13, 85, 53, 20);
		txtCep.setBounds(76, 85, 353, 20);

		lbCidade.setBounds(13, 147, 53, 20);
		txtCidade.setBounds(76, 147, 216, 20);

		lbContato.setBounds(13, 178, 53, 20);
		txtContato.setBounds(76, 178, 150, 20);

		lbEndereco.setBounds(13, 116, 63, 20);
		txtEndereco.setBounds(76, 116, 353, 20);

		lbTelefone.setBounds(239, 178, 53, 20);
		txtTelefone.setBounds(292, 178, 137, 20);

		lbUf.setBounds(301, 147, 30, 20);
		ufCb.setBounds(331, 147, 98, 20);

		// Criação dos botões
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

		// Criação do painel dos botoes
		panelButtons = new JPanel(new MigLayout());
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		getContentPane().add(panelButtons);
		panelButtons.setBounds(5, 229, 442, 40);

		// Adicionando os botoes no painel
		panelButtons.add(btnNew, "gapleft 75");
		panelButtons.add(btnCancel);
		panelButtons.add(btnSave, "gap unrelated");
		panelButtons.add(btnUpdate, "gap unrelated");
		panelButtons.add(btnRemove);

		// Criação do painel da tabela
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Empresas"));
		getContentPane().add(panelTable);
		panelTable.setBounds(5, 280, 442, 266);

		// Criação da tabela
		tabela = new JTable();

		// Criação da barra de rolagem da tabela
		scrollPane = new JScrollPane(tabela);

		// Adicionando a tabela no painel de tabela
		panelTable.add(scrollPane, "cell 0 0");

		// Bloquear txts para edição
		enableFields(selecionar);

		// Botão new
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparCampos();
				onNovaEmpresa();

			}
		});

		// Botão Update
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onAlterarEmpresa();
			}
		});

		// Botão remove
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onRemoverEmpresa();
			}
		});

		// Botão save
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onSaveEmpresa();
			}
		});

		// Botão cancelar
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enableFields(selecionar);
				limparCampos();
			}
		});

		// Setando os txts para atualizar de acordo com a linha selecionada da
		// tabela
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tabela.getRowSelectionAllowed() == true) {
					int rowIndex = tabela.getSelectedRow();
					Empresa empresa = new EmpresaTableModel(empresaList).get(rowIndex);
					idEmpresa = empresa.getId();

					txtNome.setText(empresa.getNome());
					txtCnpj.setText(empresa.getCnpj());
					txtTelefone.setText(empresa.getTelefone());
					txtEndereco.setText(empresa.getEndereco());
					txtCep.setText(empresa.getCep());
					txtCidade.setText(empresa.getCidade());
					txtContato.setText(empresa.getContato());
					ufCb.setEnabled(true);
					ufCb.setModel(new UfCB(empresa.getUf()));
					ufCb.setEnabled(false);
				}

			}
		});

		// Atualizar a lista
		refreshTable();

	}

	// Método para atualizar tabela
	private void refreshTable() {

		empresaList = new EmpresaController().findEmpresas();

		if (empresaList != null) {

			tabela.setModel(new EmpresaTableModel(empresaList));

		}
	}

	// Método para desabilitar e habilitar os elementos do form
	private void enableFields(int i) {
		if (i == 0) {

			btnRemove.setEnabled(true);
			btnNew.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnCancel.setEnabled(false);
			btnSave.setEnabled(false);
			txtNome.setEnabled(false);
			txtCnpj.setEnabled(false);
			tabela.setRowSelectionAllowed(true);
			txtCep.setEnabled(false);
			txtCidade.setEnabled(false);
			txtContato.setEnabled(false);
			txtEndereco.setEnabled(false);
			txtTelefone.setEnabled(false);
			ufCb.setEnabled(false);

		} else {

			btnRemove.setEnabled(false);
			btnNew.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnCancel.setEnabled(true);
			btnSave.setEnabled(true);
			txtNome.setEnabled(true);
			txtCnpj.setEnabled(true);
			tabela.setRowSelectionAllowed(false);
			txtCep.setEnabled(true);
			txtCidade.setEnabled(true);
			txtContato.setEnabled(true);
			txtEndereco.setEnabled(true);
			txtTelefone.setEnabled(true);
			ufCb.setEnabled(true);
		}

	}

	// Método para limpar os campos
	private void limparCampos() {

		txtNome.setText("");
		txtCnpj.setText("");
		txtCep.setText("");
		txtCidade.setText("");
		txtContato.setText("");
		txtEndereco.setText("");
		txtTelefone.setText("");
		ufCb.setSelectedIndex(0);

	}

	// Método do botão New
	private void onNovaEmpresa() {

		idEmpresa = 0;
		enableFields(salvar);

	}

	// Método do botão Remove
	private void onRemoverEmpresa() {
		int rowIndex = tabela.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione a setor a ser removido!");
			return;
		}

		Empresa empresa = new EmpresaTableModel(empresaList).get(rowIndex);
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?", "Excluir empresa",
				JOptionPane.YES_NO_OPTION);
		if (confirm != 0) {
			return;
		}

		int result = new EmpresaController().excluirEmpresa(empresa.getId());
		if (result == 1) {

			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
			enableFields(selecionar);
			limparCampos();

		} else {

			JOptionPane.showMessageDialog(this, "Tente Novamente!");

		}
	}

	// Método do botão Update
	private void onAlterarEmpresa() {

		int rowIndex = tabela.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione a empresa a ser alterada!");
			return;
		}

		Empresa empresa = new EmpresaTableModel(empresaList).get(rowIndex);
		idEmpresa = empresa.getId();
		txtNome.setText(empresa.getNome());
		txtCnpj.setText(empresa.getCnpj());
		txtCep.setText(empresa.getCep());
		txtCidade.setText(empresa.getCidade());
		txtContato.setText(empresa.getContato());
		txtEndereco.setText(empresa.getEndereco());
		txtTelefone.setText(empresa.getTelefone());
		ufCb.setEnabled(true);
		ufCb.setModel(new UfCB(empresa.getUf()));
		ufCb.setEnabled(false);
		enableFields(salvar);

	}

	// Método do botão Save
	private void onSaveEmpresa() {
		Uf ufSelecionado = (Uf) ufCb.getSelectedItem();
		Empresa empresa = new Empresa();
		// Verificação se todos os campos foram preenchidos
		if (txtNome.getText().length() > 0 && txtCnpj.getText().length() > 0 && txtCep.getText().length() > 0
				&& txtCidade.getText().length() > 0 && txtContato.getText().length() > 0
				&& txtEndereco.getText().length() > 0 && txtTelefone.getText().length() > 0
				&& ufCb.getSelectedIndex() != 0) {

			empresa.setNome(txtNome.getText());
			empresa.setCnpj(txtCnpj.getText());
			empresa.setCep(txtCep.getText());
			empresa.setCidade(txtCidade.getText());
			empresa.setContato(txtContato.getText());
			empresa.setEndereco(txtEndereco.getText());
			empresa.setTelefone(txtTelefone.getText());
			empresa.setUf(ufSelecionado.getNome());

		} else {
			JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
			return;
		}

		int result = 0;

		if (idEmpresa == 0) {

			result = new EmpresaController().addEmpresa(empresa);

		} else {

			empresa.setId(idEmpresa);
			result = new EmpresaController().alterarEmpresa(empresa);
			idEmpresa = 0;
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

}
