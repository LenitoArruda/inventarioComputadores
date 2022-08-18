package br.com.lenito.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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

import br.com.lenito.cb.MarcaCB;
import br.com.lenito.cb.SetorCB;
import br.com.lenito.controller.ComputadorController;
import br.com.lenito.controller.DoacaoController;
import br.com.lenito.controller.MarcaController;
import br.com.lenito.controller.SetorController;
import br.com.lenito.entity.Computador;
import br.com.lenito.entity.Doacao;
import br.com.lenito.entity.Marca;
import br.com.lenito.entity.Setor;
import br.com.lenito.table.ComputadorTableModel;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComputadorForm extends JFrame {

	// Criação das variaveis
	private static final long serialVersionUID = 1L;
	private JLabel lbIp, lbTipo, lbUsuario, lbMarca, lbSetor, lbModelo, lbSerial, lbNome, lbDoacao;
	private JTextField txtIp, txtUsuario, txtModelo, txtSerial, txtNome;
	private JPanel panelAdd, panelTable, panelButtons, panelDoacao;
	private JButton btnNew, btnSave, btnRemove, btnUpdate, btnCancel, btnDoar;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox<Setor> cbSetor;
	private JComboBox<Marca> cbMarca;
	private JComboBox<Object> cbTipo;

	private List<Computador> computadorList;
	private List<Marca> marcaList;
	private List<Setor> setorList;

	private int idComputador = 0;

	private final int  salvar = 1;
	private final int selecionar = 0;

	// Criação do construtor
	public ComputadorForm(String ip, String modelo, String nome, String serial, String usuario, int marca, int setor,
			int id, String tipo) {

		super("Cadastro de Computadores");
		setContentPane(new JPanel());
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/LogoIntercam.jpg")).getImage());
		setMinimumSize(new Dimension(545, 645));
		setVisible(true);

		// Criação do painel Add
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Computador"));
		panelAdd.setBounds(5, 0, 520, 243);

		// Criação dos labels
		lbTipo = new JLabel("Tipo");
		lbIp = new JLabel("IP");
		lbMarca = new JLabel("Marca");
		lbModelo = new JLabel("Modelo");
		lbNome = new JLabel("Nome");
		lbSerial = new JLabel("Serial N.");
		lbSetor = new JLabel("Setor");
		lbUsuario = new JLabel("Usuário");
		lbDoacao = new JLabel("Incluir este computador na lista de doações:");

		// Criação dos txts
		txtIp = new JTextField(14);
		txtModelo = new JTextField(30);
		txtNome = new JTextField(50);
		txtSerial = new JTextField(50);
		txtUsuario = new JTextField(2);

		// Criação dos Combo box
		setorList = new SetorController().findSetores();
		cbSetor = new JComboBox<Setor>();
		cbSetor.setModel(new SetorCB(setorList));

		marcaList = new MarcaController().findMarcas();
		cbMarca = new JComboBox<Marca>();
		cbMarca.setModel(new MarcaCB(marcaList));

		cbTipo = new JComboBox<Object>();
		cbTipo.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "< Selecione um tipo >", "All in One", "Desktop", "Notebook" }));

		// Adicionando os txts e os labels no painel
		panelAdd.add(lbTipo);
		panelAdd.add(cbTipo, "span, growx");

		panelAdd.add(lbIp);
		panelAdd.add(txtIp, "span, growx");

		panelAdd.add(lbMarca);
		panelAdd.add(cbMarca, "span, growx");

		panelAdd.add(lbModelo);
		panelAdd.add(txtModelo, "span, growx");

		panelAdd.add(lbNome);
		panelAdd.add(txtNome, "span, growx");

		panelAdd.add(lbSerial);
		panelAdd.add(txtSerial, "span, growx");

		panelAdd.add(lbSetor);
		panelAdd.add(cbSetor, "span, growx");

		panelAdd.add(lbUsuario);
		panelAdd.add(txtUsuario, "span, growx");

		// Criação do painel dos botões
		panelButtons = new JPanel(new MigLayout());
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 257, 520, 40);

		

		// Dando acesso para alteração das imagens do botao
		ClassLoader loader = getClass().getClassLoader();
		
		// Criação dos botões
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

		// Incluindo os botões no painel
		panelButtons.add(btnNew, "gapleft 115");
		panelButtons.add(btnCancel);
		panelButtons.add(btnSave, "gap unrelated");
		panelButtons.add(btnUpdate, "gap unrelated");
		panelButtons.add(btnRemove);

		// Criação do painel da tabela
		panelTable = new JPanel();
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Computadores"));
		panelTable.setBounds(5, 301, 520, 229);
		panelTable.setLayout(null);

		// Criação da tabela
		table = new JTable();

		// Criação da barra de rolagem da tabela
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(13, 23, 497, 192);

		// Adicionando a tabela no painel de tabela
		panelTable.add(scrollPane);

		// Criando o botão de doação
		btnDoar = new JButton("Doar este computador");
		btnDoar.setToolTipText("Doar");

		// CriaÃ§Ã£o do painel DoaÃ§Ã£o
		panelDoacao = new JPanel(new MigLayout());
		panelDoacao.setBorder(BorderFactory.createTitledBorder("Doação"));
		panelDoacao.setBounds(5, 529, 520, 66);

		panelDoacao.add(lbDoacao);
		panelDoacao.add(btnDoar);

		// Atualizar a lista
		refreshTable();

		// Habilitando elementos necessarios;
		enableFields(selecionar);

		// Adicionando os paineis no form
		getContentPane().add(panelAdd);
		getContentPane().add(panelButtons);
		getContentPane().add(panelTable);
		getContentPane().add(panelDoacao);

		// Setando os campos de acordo com o construtor
		txtIp.setText(ip);
		txtModelo.setText(modelo);
		txtNome.setText(nome);
		txtSerial.setText(serial);
		txtUsuario.setText(usuario);
		cbMarca.setModel(new MarcaCB(marcaList, marca));
		cbSetor.setModel(new SetorCB(setorList, setor));
		setLinhaSelecionada(id);
		cbTipo.setSelectedIndex(setaCB(tipo, cbTipo));
		idComputador = id;

		// Configurando o botão Save
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onSaveComputador();

			}
		});

		// Configurando o Cancel
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onCancelar();

			}
		});

		// Configurando o botão Doar
		btnDoar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onDoarComputador();

			}
		});

		// Configurando o botão New
		btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onNovoComputador();

			}
		});

		// Configurando o botão Remove
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onRemoverComputador();

			}
		});

		// Configurando o botão Update
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onAlterarComputador();

			}
		});

		// Setando os txts para atualizar de acordo com a linha selecionada da
		// tabela
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (table.getRowSelectionAllowed() == true) {
					int rowIndex = table.getSelectedRow();
					Computador computador = new ComputadorTableModel(computadorList).get(rowIndex);
					idComputador = computador.getId();
					txtIp.setText(computador.getIp());
					cbMarca.setEnabled(true);
					cbMarca.setModel(new MarcaCB(marcaList, computador.getMarca()));
					cbMarca.setEnabled(false);
					cbSetor.setEnabled(true);
					cbSetor.setModel(new SetorCB(setorList, computador.getSetor()));
					cbSetor.setEnabled(false);
					txtUsuario.setText(computador.getUsuario());
					txtModelo.setText(computador.getModelo());
					txtNome.setText(computador.getNome());
					txtSerial.setText(computador.getSerialNumber());
					cbTipo.setSelectedIndex(setaCB(computador.getTipo(), cbTipo));
				}
			}
		});

	}

	// Método para selecionar a linha da tabela de acordo com o computador
	// passado pelo parametro
	private void setLinhaSelecionada(int id) {
		computadorList = new ComputadorController().findComputadores();
		for (int i = 0; i < computadorList.size(); i++) {
			Computador c = computadorList.get(i);
			if (c.getId() == id) {
				table.setRowSelectionInterval(i, i);
			}
		}

	}

	// Método botão Doar
	private void onDoarComputador() {
		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o computador a ser doado!");
			return;
		}

		Doacao doacao = new Doacao();
		Computador computador = new ComputadorTableModel(computadorList).get(rowIndex);
		int confirm = JOptionPane.showConfirmDialog(this,
				"Este computador será incluido na lista de doação e será removido do seu inventário. Você tem certeza disso?",
				"Doar computador", JOptionPane.YES_NO_OPTION);

		if (confirm != 0) {
			return;
		}

		// Excluindo computador do inventario
		int result = new ComputadorController().excluirComputador(computador.getId());

		// Adicionando o computador em doações
		doacao.setAdicional("-");
		doacao.setMarca(String.valueOf(cbMarca.getSelectedItem()));
		doacao.setModelo(txtModelo.getText());
		doacao.setQuantidade(1);
		doacao.setSerialNumber(txtSerial.getText());
		doacao.setTipo(String.valueOf(cbTipo.getSelectedItem()));

		result += new DoacaoController().addDoacao(doacao);

		if (result == 2) {

			JOptionPane.showMessageDialog(this, "Computador inserido na lista de doação com sucesso!");
			refreshTable();
			onCancelar();

		} else {

			JOptionPane.showMessageDialog(this, "Tente Novamente!");

		}

	}

	// Método do botão New
	private void onNovoComputador() {

		limparCampos();
		enableFields(salvar);
		idComputador = 0;

	}

	// Método do botão Remove
	private void onRemoverComputador() {
		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o computador a ser removido!");
			return;
		}

		Computador computador = new ComputadorTableModel(computadorList).get(rowIndex);
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?", "Excluir computador",
				JOptionPane.YES_NO_OPTION);
		if (confirm != 0) {
			return;
		}

		int result = new ComputadorController().excluirComputador(computador.getId());
		if (result == 1) {

			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
			onCancelar();

		} else {

			JOptionPane.showMessageDialog(this, "Tente Novamente!");

		}
	}

	// Método do botão Update
	private void onAlterarComputador() {

		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o computador a ser alterado!");
			return;
		}

		enableFields(salvar);

	}

	// Método do botão Save
	private void onSaveComputador() {

		Computador computador = new Computador();
		Setor setorSelecionado = (Setor) cbSetor.getSelectedItem();
		Marca marcaSelecionado = (Marca) cbMarca.getSelectedItem();
		// Verificação se todos os campos foram preenchidos
		if (txtIp.getText().length() > 0 && txtModelo.getText().length() > 0 && txtNome.getText().length() > 0
				&& marcaSelecionado.getId() > 0 && txtSerial.getText().length() > 0 && setorSelecionado.getId() > 0
				&& txtUsuario.getText().length() > 0) {

			computador.setMarca(marcaSelecionado.getNome());
			computador.setSetor(setorSelecionado.getNome());
			computador.setIp(txtIp.getText());
			computador.setModelo(txtModelo.getText());
			computador.setNome(txtNome.getText());
			computador.setUsuario(txtUsuario.getText());
			computador.setSerialNumber(txtSerial.getText());
			computador.setTipo(String.valueOf(cbTipo.getSelectedItem()));

		} else {
			JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
			return;
		}

		int result = 0;

		if (idComputador == 0) {

			result = new ComputadorController().addComputador(computador);

		} else {

			computador.setId(idComputador);
			result = new ComputadorController().alterarComputador(computador);
			idComputador = 0;
		}

		if (result == 1) {

			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
			refreshTable();
			enableFields(selecionar);
			limparCampos();

		} else {

			JOptionPane.showMessageDialog(this, "Tente Novamente!");

		}

	}

	// Método do botão Cancel
	private void onCancelar() {

		limparCampos();
		enableFields(selecionar);

	}

	// Limpar os campos
	private void limparCampos() {
		txtIp.setText("");
		txtModelo.setText("");
		txtNome.setText("");
		txtSerial.setText("");
		txtUsuario.setText("");
		cbMarca.setSelectedItem(marcaList.get(0));
		cbSetor.setSelectedItem(setorList.get(0));
		cbTipo.setSelectedIndex(0);
	}

	// Método para desabilitar e habilitar os elementos do form
	private void enableFields(int i) {
		if (i == 0) {

			btnRemove.setEnabled(true);
			btnNew.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnCancel.setEnabled(false);
			btnSave.setEnabled(false);
			btnDoar.setEnabled(true);
			txtIp.setEnabled(false);
			cbMarca.setEnabled(false);
			txtModelo.setEnabled(false);
			txtNome.setEnabled(false);
			txtSerial.setEnabled(false);
			cbSetor.setEnabled(false);
			cbTipo.setEnabled(false);
			txtUsuario.setEnabled(false);
			table.setRowSelectionAllowed(true);

		} else {

			btnRemove.setEnabled(false);
			btnNew.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnCancel.setEnabled(true);
			btnSave.setEnabled(true);
			btnDoar.setEnabled(false);
			txtIp.setEnabled(true);
			cbMarca.setEnabled(true);
			txtModelo.setEnabled(true);
			txtNome.setEnabled(true);
			txtSerial.setEnabled(true);
			cbSetor.setEnabled(true);
			cbTipo.setEnabled(true);
			txtUsuario.setEnabled(true);
			table.setRowSelectionAllowed(false);
		}
	}

	// Método para atualizar a tabela
	private void refreshTable() {

		computadorList = new ComputadorController().findComputadores();

		if (computadorList != null) {

			table.setModel(new ComputadorTableModel(computadorList));

		}
	}

	// Método para setar o comboBox de acordo com o parametro
	private int setaCB(String tipo, JComboBox<Object> combo) {
		int total = combo.getItemCount();
		for (int i = 0; i < total; i++) {
			combo.setSelectedIndex(i);
			String conteudo = String.valueOf(combo.getSelectedItem());
			if (tipo.equals(conteudo)) {
				return i;
			}

		}
		return 0;

	}

}
