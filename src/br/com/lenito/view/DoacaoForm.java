package br.com.lenito.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.com.lenito.cb.MarcaCB;
import br.com.lenito.controller.DoacaoController;
import br.com.lenito.controller.MarcaController;
import br.com.lenito.entity.Doacao;
import br.com.lenito.entity.Marca;
import br.com.lenito.table.DoacaoTableModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DoacaoForm extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable tabela;
	private JScrollPane scrollPane;
	private JPanel panellTipo, panelMouseTeclado, panelOutros, panelButtons, panelTable, panelDoar;
	private JLabel lbMarca, lbMarca1, lbModelo, lbSerial, lbQtd, lbSaida, lbTipo, lbTipo1;
	private JTextField txtModelo, txtSerial, txtQtd;
	private JComboBox<Object> cbTipo, cbMouseTeclado, cbOutros, cbSaida;
	private JComboBox<Marca> cbMarca, cbMarca1;
	private JButton btnNew, btnUpdate, btnSave, btnCancel, btnRemove, btnDoar;

	private List<Doacao> doacaoList;
	private List<Marca> marcaList, marcaList1;
	private int idDoacao;
	private int salvar = 1;
	private int selecionar = 0;

	public DoacaoForm() {

		// Configurações do form
		super("Cadastro de Doações");
		setContentPane(new JPanel());
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/LogoIntercam.jpg")).getImage());
		setMinimumSize(new Dimension(563, 600));
		setVisible(true);

		// Criação dos CB
		cbTipo = new JComboBox<Object>();
		cbTipo.setBounds(23, 23, 504, 20);
		cbTipo.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "< Selecione um tipo >", "Mouse / Teclado", "Outros" }));
		cbMouseTeclado = new JComboBox<Object>();
		cbMouseTeclado.setBounds(127, 21, 400, 26);
		cbMouseTeclado.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "< Selecione um tipo >", "Mouse", "Teclado" }));
		cbOutros = new JComboBox<Object>();
		cbOutros.setBounds(127, 21, 400, 26);
		cbOutros.setModel(new DefaultComboBoxModel<Object>(new String[] { "< Selecione um tipo >", "All in One",
				"Desktop", "Monitor", "Notebook", "Roteador", "Switch" }));

		cbSaida = new JComboBox<Object>();
		cbSaida.setBounds(127, 120, 400, 20);
		cbSaida.setModel(new DefaultComboBoxModel<Object>(new String[] { "< Selecione uma saída >", "P2P", "USB" }));

		marcaList = new MarcaController().findMarcas();
		cbMarca = new JComboBox<Marca>();
		cbMarca.setModel(new MarcaCB(marcaList));
		cbMarca.setBounds(127, 89, 400, 20);

		marcaList1 = new MarcaController().findMarcas();
		cbMarca1 = new JComboBox<Marca>();
		cbMarca1.setModel(new MarcaCB(marcaList1));
		cbMarca1.setBounds(127, 58, 400, 20);

		// Criação dos labels
		lbMarca = new JLabel("Marca");
		lbMarca.setBounds(23, 92, 74, 14);

		lbModelo = new JLabel("Modelo");
		lbModelo.setBounds(23, 123, 74, 14);

		lbSaida = new JLabel("Saída");
		lbSaida.setBounds(23, 123, 74, 14);

		lbQtd = new JLabel("Quantidade");
		lbQtd.setBounds(23, 92, 74, 14);

		lbSerial = new JLabel("Número de Série");
		lbSerial.setBounds(23, 61, 102, 14);

		lbTipo = new JLabel("Tipo");
		lbTipo.setBounds(23, 30, 74, 14);

		lbTipo1 = new JLabel("Tipo");
		lbTipo1.setBounds(23, 30, 74, 14);

		lbMarca1 = new JLabel("Marca");
		lbMarca1.setBounds(23, 61, 74, 14);

		// Criação dos Txts
		txtModelo = new JTextField();
		txtModelo.setBounds(127, 120, 400, 20);

		txtSerial = new JTextField();
		txtSerial.setBounds(127, 58, 400, 20);

		txtQtd = new JTextField();
		txtQtd.setBounds(127, 89, 400, 20);

		// Criação do painel MouseTeclado
		panelMouseTeclado = new JPanel();
		panelMouseTeclado.setBorder(BorderFactory.createTitledBorder("Mouse / Teclado"));
		panelMouseTeclado.setBounds(5, 63, 537, 158);
		getContentPane().add(panelMouseTeclado);
		panelMouseTeclado.setLayout(null);
		panelMouseTeclado.setVisible(false);

		panelMouseTeclado.add(lbTipo1);
		panelMouseTeclado.add(cbMouseTeclado);

		panelMouseTeclado.add(lbQtd);
		panelMouseTeclado.add(txtQtd);

		panelMouseTeclado.add(lbSaida);
		panelMouseTeclado.add(cbSaida);

		panelMouseTeclado.add(lbMarca1);
		panelMouseTeclado.add(cbMarca1);

		// Criação do painel Outros
		panelOutros = new JPanel();
		panelOutros.setBorder(BorderFactory.createTitledBorder("Outros"));
		panelOutros.setBounds(5, 63, 537, 158);
		getContentPane().add(panelOutros);
		panelOutros.setVisible(false);
		panelOutros.setLayout(null);

		panelOutros.add(lbTipo);
		panelOutros.add(cbOutros);

		panelOutros.add(lbMarca);
		panelOutros.add(cbMarca);

		panelOutros.add(lbModelo);
		panelOutros.add(txtModelo);

		panelOutros.add(lbSerial);
		panelOutros.add(txtSerial);

		// Criação do painel Tipo
		panellTipo = new JPanel();
		panellTipo.setBorder(BorderFactory.createTitledBorder("Tipo de Doação"));
		panellTipo.setBounds(5, 0, 537, 65);
		panellTipo.setLayout(null);
		panellTipo.add(cbTipo);
		getContentPane().add(panellTipo);

		// Configurando o ComboBox tipo para habilitalar o painel de acordo
		cbTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbTipo.getSelectedIndex() == 1) {

					panelOutros.setVisible(false);
					panelMouseTeclado.setVisible(true);

				} else if (cbTipo.getSelectedIndex() == 2) {

					panelOutros.setVisible(true);
					panelMouseTeclado.setVisible(false);

				} else {

					panelOutros.setVisible(false);
					panelMouseTeclado.setVisible(false);

				}
			}
		});

		// Criação do painel dos botoes
		panelButtons = new JPanel(new MigLayout());
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 226, 537, 43);
		panelButtons.setVisible(true);
		getContentPane().add(panelButtons);

		// Criação dos botões
		ClassLoader loader = getClass()
				.getClassLoader(); /*
									 * Dando acesso para alteração das imagens
									 * do botao
									 */
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
		panelButtons.add(btnNew, "gapleft 120");
		panelButtons.add(btnCancel);
		panelButtons.add(btnSave, "gap unrelated");
		panelButtons.add(btnUpdate, "gap unrelated");
		panelButtons.add(btnRemove);

		// Criação do painel da tabela
		panelTable = new JPanel();
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Doações"));
		panelTable.setBounds(5, 269, 537, 229);
		panelTable.setLayout(null);

		// Criação da tabela
		tabela = new JTable();

		// Criação da barra de rolagem da tabela
		scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(13, 23, 514, 192);

		// Adicionando a tabela no painel de tabela
		panelTable.add(scrollPane);
		getContentPane().add(panelTable);

		// Criação do painel Doação
		panelDoar = new JPanel();
		panelDoar.setBorder(BorderFactory.createTitledBorder("Efetivar Doação"));
		panelDoar.setBounds(5, 497, 537, 60);
		getContentPane().add(panelDoar);
		panelDoar.setLayout(null);

		// Criando o botão de doação
		btnDoar = new JButton("Doar");
		btnDoar.setBounds(229, 22, 96, 27);
		btnDoar.setToolTipText("Doar");
		panelDoar.add(btnDoar);

		refreshTable();
		enableFields(selecionar);

		// Setando os campos para atualizar de acordo com a linha selecionada da
		// tabela
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tabela.getRowSelectionAllowed() == true) {
					int rowIndex = tabela.getSelectedRow();
					Doacao doacao = new DoacaoTableModel(doacaoList).get(rowIndex);
					enableFields(salvar);
					idDoacao = doacao.getId();
					txtModelo.setText(doacao.getModelo());
					txtQtd.setText(Integer.toString(doacao.getQuantidade()));
					txtSerial.setText(doacao.getSerialNumber());
					cbMouseTeclado.setSelectedIndex(setaCB(doacao.getTipo(), cbMouseTeclado));
					cbOutros.setSelectedIndex(setaCB(doacao.getTipo(), cbOutros));
					cbSaida.setSelectedIndex(setaCB(doacao.getAdicional(), cbSaida));
					enableFields(selecionar);

					// Verificando com painel ativar de acordo com o tipo
					if (doacao.getTipo().equals("Mouse") || doacao.getTipo().equals("Teclado")) {
						panelMouseTeclado.setVisible(true);
						panelOutros.setVisible(false);
						cbTipo.setSelectedIndex(1);
						cbMarca1.setEnabled(true);
						cbMarca1.setModel(new MarcaCB(marcaList, doacao.getMarca()));
						cbMarca1.setEnabled(false);

					} else {
						panelMouseTeclado.setVisible(false);
						panelOutros.setVisible(true);
						cbTipo.setSelectedIndex(2);
						cbMarca.setEnabled(true);
						cbMarca.setModel(new MarcaCB(marcaList, doacao.getMarca()));
						cbMarca.setEnabled(false);
					}

				}
			}
		});

		// Método para impossibilitar do usuário digitar letrar no txt
		// quantidade
		txtQtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});

		// Botão Doar
		btnDoar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DoarElementos();
			}
		});

		// Botão salvar
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onSaveDoacao();
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

		// Botão Alterar
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onAlterarDoacao();
				cbTipo.setEnabled(false);

			}
		});

		// Botão excluir
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onRemoverDoacao();
			}
		});

		// Botão Novo
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onNovaDoacao();
			}
		});

	}

	// Método do botão New
	private void onNovaDoacao() {

		idDoacao = 0;
		enableFields(salvar);
		limparCampos();

	}

	// Método do botão Remove
	private void onRemoverDoacao() {
		int rowIndex = tabela.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione a doação a ser removida!");
			return;
		}

		Doacao doacao = new DoacaoTableModel(doacaoList).get(rowIndex);
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?", "Excluir computador",
				JOptionPane.YES_NO_OPTION);
		if (confirm != 0) {
			return;
		}

		int result = new DoacaoController().excluirDoacao(doacao.getId());
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
	private void onAlterarDoacao() {

		int rowIndex = tabela.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione a doação a ser alterada!");
			return;
		}

		enableFields(salvar);

	}

	// Método do botão Save
	private void onSaveDoacao() {

		Doacao doacao = new Doacao();
		Marca marcaSelecionado1 = (Marca) cbMarca1.getSelectedItem();
		Marca marcaSelecionado = (Marca) cbMarca.getSelectedItem();

		// Verificação se todos os campos foram preenchidos
		if (cbTipo.getSelectedIndex() == 1) {
			if (txtQtd.getText().length() > 0 && cbMouseTeclado.getSelectedIndex() > 0 && marcaSelecionado1.getId() > 0
					&& cbSaida.getSelectedIndex() > 0) {

				doacao.setAdicional(String.valueOf(cbSaida.getSelectedItem()));
				doacao.setMarca(String.valueOf(cbMarca1.getSelectedItem()));
				doacao.setModelo("-");
				int qtd = verificaNumero(txtQtd.getText());
				if (qtd > 0) {
					doacao.setQuantidade(Integer.parseInt(txtQtd.getText()));
				} else {
					JOptionPane.showMessageDialog(this, "Atenção! No campo 'Quantidade' digitar apenas números.");
					return;
				}
				doacao.setSerialNumber("-");
				doacao.setTipo(String.valueOf(cbMouseTeclado.getSelectedItem()));

			} else {

				JOptionPane.showMessageDialog(this, "Todos os campos devem serem preenchidos!");
				return;

			}

		} else if (cbTipo.getSelectedIndex() == 2) {
			if (txtModelo.getText().length() > 0 && txtSerial.getText().length() > 0 && marcaSelecionado.getId() > 0
					&& cbOutros.getSelectedIndex() > 0) {

				doacao.setAdicional("-");
				doacao.setMarca(String.valueOf(cbMarca.getSelectedItem()));
				doacao.setModelo(txtModelo.getText());
				doacao.setQuantidade(1);
				doacao.setSerialNumber(txtSerial.getText());
				doacao.setTipo(String.valueOf(cbOutros.getSelectedItem()));

			} else {
				JOptionPane.showMessageDialog(this, "Todos os campos devem serem preenchidos!");
				return;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Selecione o tipo de doação");
			return;

		}

		int result = 0;

		if (idDoacao == 0) {

			result = new DoacaoController().addDoacao(doacao);

		} else {

			doacao.setId(idDoacao);
			result = new DoacaoController().alterarDoacao(doacao);
			idDoacao = 0;
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

	// Método para desabilitar e habilitar os elementos do form
	private void enableFields(int i) {
		if (i == 0) {

			btnRemove.setEnabled(true);
			btnNew.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnCancel.setEnabled(false);
			btnSave.setEnabled(false);
			cbTipo.setEnabled(false);
			cbMarca.setEnabled(false);
			cbMarca1.setEnabled(false);
			cbMouseTeclado.setEnabled(false);
			cbOutros.setEnabled(false);
			cbSaida.setEnabled(false);
			txtModelo.setEnabled(false);
			txtQtd.setEnabled(false);
			txtSerial.setEnabled(false);
			tabela.setRowSelectionAllowed(true);

		} else {

			btnRemove.setEnabled(false);
			btnNew.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnCancel.setEnabled(true);
			btnSave.setEnabled(true);
			cbTipo.setEnabled(true);
			cbMarca.setEnabled(true);
			cbMarca1.setEnabled(true);
			cbMouseTeclado.setEnabled(true);
			cbOutros.setEnabled(true);
			cbSaida.setEnabled(true);
			txtModelo.setEnabled(true);
			txtQtd.setEnabled(true);
			txtSerial.setEnabled(true);
			tabela.setRowSelectionAllowed(false);
		}

	}

	// Método para limpar os campos
	private void limparCampos() {

		txtModelo.setText("");
		txtQtd.setText("");
		txtSerial.setText("");
		cbMarca.setSelectedIndex(0);
		cbMarca1.setSelectedIndex(0);
		cbMouseTeclado.setSelectedIndex(0);
		cbOutros.setSelectedIndex(0);
		cbSaida.setSelectedIndex(0);
		cbTipo.setSelectedIndex(0);
		panelMouseTeclado.setVisible(false);
		panelOutros.setVisible(false);

	}

	// Método para atualizar a tabela
	private void refreshTable() {

		doacaoList = new DoacaoController().findDoacoes();

		if (doacaoList != null) {

			tabela.setModel(new DoacaoTableModel(doacaoList));

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

	private int verificaNumero(String palavra) {
		int verifica = 0;
		for (int i = 0; i < palavra.length(); i++) {
			if (Character.isDigit(palavra.charAt(i)) == false) {

				verifica++;

			}

		}
		if (verifica > 0) {

			return 0;

		} else {

			return Integer.parseInt(palavra);

		}
	}

}
