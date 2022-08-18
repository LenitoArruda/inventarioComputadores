package br.com.lenito.view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.com.lenito.cb.EmpresaCB;
import br.com.lenito.controller.EmpresaController;
import br.com.lenito.entity.Empresa;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import javax.swing.ScrollPaneConstants;

public class CompraForm extends JFrame {

	private JPanel panel, panelContrato, panelBtn, panelItens;;
	private JLabel lbEmpresa, lbPedido, lbDataVenc, lbExemplo, lbIncluir, lbObs1, lbObs2, lbItem1, lbItem2, lbItem3,
			lbItem5, lbItem6, lbItem4, lbQtd, lbValor1, lbValor2, lbValor3, lbValor4, lbValor5, lbValor6, lbQtd1,
			lbQtd2, lbQtd3, lbQtd4, lbQtd5, lbQtd6;
	private JComboBox<Object> cbQtd;
	private JComboBox<Empresa> cbEmpresa;
	private JTextField txtPedido, txtDataVenc, txtObs1, txtObs2, txtItem1, txtItem2, txtItem3, txtItem4, txtItem5,
			txtItem6, txtQtd1, txtQtd2, txtQtd3, txtQtd4, txtQtd5, txtQtd6, txtValor1, txtValor2, txtValor3, txtValor4,
			txtValor5, txtValor6;
	private JCheckBox chbObs;
	private JButton btnGerar;
	private JScrollPane scrollPane;

	private List<Empresa> empresaList;

	private static final long serialVersionUID = 1L;

	public CompraForm() {

		// Configurando o form
		super("Pedido de Compra");
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/LogoIntercam.jpg")).getImage());
		setMinimumSize(new Dimension(655, 680));
		setResizable(false);
		setVisible(true);

		// Criação do painel contrato
		panelContrato = new JPanel();
		panelContrato.setBorder(BorderFactory.createTitledBorder("Formulário de Contrato"));
		panelContrato.setLayout(null);
		panelContrato.setVisible(true);
		panel.add(panelContrato);
		panelContrato.setBounds(10, 11, 615, 234);

		// Criando componentes do painel contrato
		lbEmpresa = new JLabel("Empresa");
		lbPedido = new JLabel("N° do pedido");
		lbDataVenc = new JLabel("Vencimento");
		lbExemplo = new JLabel("( Ex. dd/mm/aaaa )");
		lbIncluir = new JLabel("Incluir Obervação");
		lbObs1 = new JLabel("Observação 1");
		lbObs2 = new JLabel("Observação 2");
		lbQtd = new JLabel("Quantidade de Itens");

		txtPedido = new JTextField(50);
		txtDataVenc = new JTextField(10);
		txtObs1 = new JTextField(100);
		txtObs2 = new JTextField(100);

		chbObs = new JCheckBox();

		cbEmpresa = new JComboBox<Empresa>();
		empresaList = new EmpresaController().findEmpresas();
		cbEmpresa.setModel(new EmpresaCB(empresaList));

		cbQtd = new JComboBox<Object>();
		cbQtd.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "< Quantidade >", "1", "2", "3", "4", "5", "6" }));

		btnGerar = new JButton("Gerar Pedido de Compra");

		// Adicionando componentes no painel contrato
		panelContrato.add(lbEmpresa);
		panelContrato.add(cbEmpresa);

		panelContrato.add(lbPedido);
		panelContrato.add(txtPedido);

		panelContrato.add(lbDataVenc);
		panelContrato.add(txtDataVenc);
		panelContrato.add(lbExemplo);

		panelContrato.add(lbIncluir);
		panelContrato.add(chbObs);

		panelContrato.add(lbObs1);
		panelContrato.add(txtObs1);

		panelContrato.add(lbObs2);
		panelContrato.add(txtObs2);

		panelContrato.add(lbQtd);
		panelContrato.add(cbQtd);

		lbEmpresa.setBounds(25, 23, 94, 20);
		cbEmpresa.setBounds(161, 23, 432, 20);

		lbPedido.setBounds(25, 54, 101, 20);
		txtPedido.setBounds(161, 54, 175, 20);

		lbDataVenc.setBounds(346, 54, 72, 20);
		txtDataVenc.setBounds(428, 54, 165, 20);
		lbExemplo.setBounds(428, 74, 135, 20);

		lbIncluir.setBounds(25, 89, 116, 20);
		chbObs.setBounds(158, 89, 21, 20);

		lbObs1.setBounds(25, 120, 86, 20);
		txtObs1.setBounds(161, 120, 432, 20);

		lbObs2.setBounds(25, 151, 86, 20);
		txtObs2.setBounds(161, 151, 432, 20);

		lbQtd.setBounds(25, 187, 127, 20);
		cbQtd.setBounds(161, 187, 116, 20);

		// Criação dos componentes do painel itens
		lbItem1 = new JLabel("Item 1");
		lbItem2 = new JLabel("Item 2");
		lbItem3 = new JLabel("Item 3");
		lbItem4 = new JLabel("Item 4");
		lbItem5 = new JLabel("Item 5");
		lbItem6 = new JLabel("Item 6");
		lbQtd1 = new JLabel("Quantidade Item 1");
		lbQtd2 = new JLabel("Quantidade Item 2");
		lbQtd3 = new JLabel("Quantidade Item 3");
		lbQtd4 = new JLabel("Quantidade Item 4");
		lbQtd5 = new JLabel("Quantidade Item 5");
		lbQtd6 = new JLabel("Quantidade Item 6");
		lbValor1 = new JLabel("Valor Item 1");
		lbValor2 = new JLabel("Valor Item 2");
		lbValor3 = new JLabel("Valor Item 3");
		lbValor4 = new JLabel("Valor Item 4");
		lbValor5 = new JLabel("Valor Item 5");
		lbValor6 = new JLabel("Valor Item 6");

		txtItem1 = new JTextField(200);
		txtItem2 = new JTextField(200);
		txtItem3 = new JTextField(200);
		txtItem4 = new JTextField(200);
		txtItem5 = new JTextField(200);
		txtItem6 = new JTextField(200);
		txtQtd1 = new JTextField(5);
		txtQtd2 = new JTextField(5);
		txtQtd3 = new JTextField(5);
		txtQtd4 = new JTextField(5);
		txtQtd5 = new JTextField(5);
		txtQtd6 = new JTextField(5);
		txtValor1 = new JTextField(15);
		txtValor2 = new JTextField(15);
		txtValor3 = new JTextField(15);
		txtValor4 = new JTextField(15);
		txtValor5 = new JTextField(15);
		txtValor6 = new JTextField(15);

		// Criação do painel Itens
		panelItens = new JPanel();
		panelItens.setBorder(BorderFactory.createEmptyBorder());
		panelItens.setPreferredSize(new Dimension(308,450));
		scrollPane = new JScrollPane(panelItens);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Formulário de Compra"));
		scrollPane.setBounds(10, 256, 615, 318);
		panel.add(scrollPane);
		panelItens.setLayout(null);

		// Adicionando componentes no painel itens
		panelItens.add(lbItem1);
		panelItens.add(txtItem1);
		panelItens.add(lbQtd1);
		panelItens.add(txtQtd1);
		panelItens.add(lbValor1);
		panelItens.add(txtValor1);

		panelItens.add(lbItem2);
		panelItens.add(txtItem2);
		panelItens.add(lbQtd2);
		panelItens.add(txtQtd2);
		panelItens.add(lbValor2);
		panelItens.add(txtValor2);

		panelItens.add(lbItem3);
		panelItens.add(txtItem3);
		panelItens.add(lbQtd3);
		panelItens.add(txtQtd3);
		panelItens.add(lbValor3);
		panelItens.add(txtValor3);

		panelItens.add(lbItem4);
		panelItens.add(txtItem4);
		panelItens.add(lbQtd4);
		panelItens.add(txtQtd4);
		panelItens.add(lbValor4);
		panelItens.add(txtValor4);

		panelItens.add(lbItem5);
		panelItens.add(txtItem5);
		panelItens.add(lbQtd5);
		panelItens.add(txtQtd5);
		panelItens.add(lbValor5);
		panelItens.add(txtValor5);

		panelItens.add(lbItem6);
		panelItens.add(txtItem6);
		panelItens.add(lbQtd6);
		panelItens.add(txtQtd6);
		panelItens.add(lbValor6);
		panelItens.add(txtValor6);

		lbItem1.setBounds(25, 28, 95, 14);
		txtItem1.setBounds(161, 25, 415, 20);
		lbQtd1.setBounds(25, 56, 142, 20);
		txtQtd1.setBounds(161, 56, 117, 20);
		lbValor1.setBounds(301, 56, 71, 20);
		txtValor1.setBounds(396, 56, 180, 20);

		lbItem2.setBounds(25, 103, 95, 14);
		txtItem2.setBounds(161, 100, 415, 20);
		lbQtd2.setBounds(25, 131, 142, 20);
		txtQtd2.setBounds(161, 131, 117, 20);
		lbValor2.setBounds(301, 131, 71, 20);
		txtValor2.setBounds(396, 131, 180, 20);

		lbItem3.setBounds(25, 172, 95, 14);
		txtItem3.setBounds(161, 172, 415, 20);
		lbQtd3.setBounds(25, 200, 142, 20);
		txtQtd3.setBounds(161, 200, 117, 20);
		lbValor3.setBounds(301, 200, 71, 20);
		txtValor3.setBounds(396, 200, 180, 20);

		lbItem4.setBounds(25, 241, 95, 14);
		txtItem4.setBounds(161, 241, 415, 20);
		lbQtd4.setBounds(25, 269, 142, 20);
		txtQtd4.setBounds(161, 269, 117, 20);
		lbValor4.setBounds(301, 269, 95, 20);
		txtValor4.setBounds(396, 269, 180, 20);
		

		lbItem5.setBounds(25, 310, 95, 14);
		txtItem5.setBounds(161, 310, 415, 20);
		lbQtd5.setBounds(25, 338, 142, 20);
		txtQtd5.setBounds(161, 338, 117, 20);
		lbValor5.setBounds(301, 338, 95, 20);
		txtValor5.setBounds(396, 338, 180, 20);
		

		lbItem6.setBounds(25, 379, 95, 14);
		txtItem6.setBounds(161, 379, 415, 20);
		lbQtd6.setBounds(25, 407, 142, 20);
		txtQtd6.setBounds(161, 407, 117, 20);
		lbValor6.setBounds(301, 407, 95, 20);
		txtValor6.setBounds(396, 407, 180, 20);
		

		// Criação do painel de botão
		panelBtn = new JPanel();
		panelBtn.setBounds(10, 585, 615, 46);
		panel.add(panelBtn);
		panelBtn.setBorder(BorderFactory.createEtchedBorder());
		panelBtn.setLayout(null);
		panelBtn.setVisible(true);

		// Chamada do metodo limparCampos()
		limparCampos();
		habilitaItens1(false);
		habilitaItens2(false);
		habilitaItens3(false);
		habilitaItens4(false);
		habilitaItens5(false);
		habilitaItens6(false);

		// Adicionando componentes no painel do botão
		panelBtn.add(btnGerar);
		btnGerar.setBounds(196, 11, 210, 24);

		// Configurando o botão
		btnGerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (cbEmpresa.getSelectedIndex() != 0 && txtPedido.getText().length() > 0
						&& txtDataVenc.getText().length() > 0 && cbQtd.getSelectedIndex() > 0) {

					String nome = "Pedido de compra - Compra";
					Empresa empresa = (Empresa) cbEmpresa.getSelectedItem();
					String pedido = txtPedido.getText();
					String dataVenc = txtDataVenc.getText();
					String obs1 = txtObs1.getText();
					String obs2 = txtObs2.getText();
					String[][] items = new String[6][3];
					items[0][0] = txtItem1.getText();
					items[1][0] = txtItem2.getText();
					items[2][0] = txtItem3.getText();
					items[3][0] = txtItem4.getText();
					items[4][0] = txtItem5.getText();
					items[5][0] = txtItem6.getText();
					items[0][1] = txtValor1.getText();
					items[1][1] = txtValor2.getText();
					items[2][1] = txtValor3.getText();
					items[3][1] = txtValor4.getText();
					items[4][1] = txtValor5.getText();
					items[5][1] = txtValor6.getText();
					items[0][2] = txtQtd1.getText();
					items[1][2] = txtQtd2.getText();
					items[2][2] = txtQtd3.getText();
					items[3][2] = txtQtd4.getText();
					items[4][2] = txtQtd5.getText();
					items[5][2] = txtQtd6.getText();

					new SelecionarDiretorioForm(nome).setandoVariaveisCompra(empresa, dataVenc, pedido, obs1, obs2,
							items);

					limparCampos();

				} else {

					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

				}
			}
		});

		// Habilitando itens conforme combo box
		cbQtd.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (cbQtd.getSelectedIndex() == 0) {

					habilitaItens1(false);
					habilitaItens2(false);
					habilitaItens3(false);
					habilitaItens4(false);
					habilitaItens5(false);
					habilitaItens6(false);

				} else if (cbQtd.getSelectedIndex() == 1) {

					habilitaItens1(true);
					habilitaItens2(false);
					habilitaItens3(false);
					habilitaItens4(false);
					habilitaItens5(false);
					habilitaItens6(false);

				} else if (cbQtd.getSelectedIndex() == 2) {

					habilitaItens1(true);
					habilitaItens2(true);
					habilitaItens3(false);
					habilitaItens4(false);
					habilitaItens5(false);
					habilitaItens6(false);

				} else if (cbQtd.getSelectedIndex() == 3) {

					habilitaItens1(true);
					habilitaItens2(true);
					habilitaItens3(true);
					habilitaItens4(false);
					habilitaItens5(false);
					habilitaItens6(false);

				} else if (cbQtd.getSelectedIndex() == 4) {

					habilitaItens1(true);
					habilitaItens2(true);
					habilitaItens3(true);
					habilitaItens4(true);
					habilitaItens5(false);
					habilitaItens6(false);

				} else if (cbQtd.getSelectedIndex() == 5) {

					habilitaItens1(true);
					habilitaItens2(true);
					habilitaItens3(true);
					habilitaItens4(true);
					habilitaItens5(true);
					habilitaItens6(false);

				} else if (cbQtd.getSelectedIndex() == 6) {

					habilitaItens1(true);
					habilitaItens2(true);
					habilitaItens3(true);
					habilitaItens4(true);
					habilitaItens5(true);
					habilitaItens6(true);
				}

			}
		});

		// Configurando Check box
		chbObs.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (chbObs.isSelected() == true) {
					txtObs1.setEnabled(false);
					txtObs2.setEnabled(false);
				} else {
					txtObs1.setEnabled(true);
					txtObs2.setEnabled(true);
				}

			}
		});

		// Configurando os txts para substituir a , por .
		txtValor1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getSource() == txtValor1) {
					if (e.getKeyChar() == ',') {
						txtValor1.setText(txtValor1.getText().replace(',', '.'));
						txtValor1.setCaretPosition(txtValor1.getText().length());
					}
				}
			}
		});

		txtValor2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getSource() == txtValor2) {
					if (e.getKeyChar() == ',') {
						txtValor2.setText(txtValor2.getText().replace(',', '.'));
						txtValor2.setCaretPosition(txtValor2.getText().length());
					}
				}
			}
		});

		txtValor3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getSource() == txtValor3) {
					if (e.getKeyChar() == ',') {
						txtValor3.setText(txtValor3.getText().replace(',', '.'));
						txtValor3.setCaretPosition(txtValor3.getText().length());
					}
				}
			}
		});

		txtValor4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getSource() == txtValor4) {
					if (e.getKeyChar() == ',') {
						txtValor4.setText(txtValor4.getText().replace(',', '.'));
						txtValor4.setCaretPosition(txtValor4.getText().length());
					}
				}
			}
		});

		// Evita letra nos txts numéricos
		txtValor1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});

		txtValor2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});

		txtValor3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});

		txtValor4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});

		txtQtd1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});

		txtQtd2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});

		txtQtd3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});

		txtQtd4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});

	}

	// Zera os componentes do form
	public void limparCampos() {

		cbEmpresa.setEnabled(true);
		cbEmpresa.setSelectedIndex(0);
		txtDataVenc.setText("");
		txtObs1.setText("");
		txtObs1.setEnabled(false);
		txtObs2.setText("");
		txtObs2.setEnabled(false);
		txtPedido.setText("");
		chbObs.setSelected(false);
		cbQtd.setSelectedIndex(0);

		txtValor1.setText("");
		txtItem1.setText("");
		txtQtd1.setText("");

		txtValor2.setText("");
		txtItem2.setText("");
		txtQtd2.setText("");

		txtValor3.setText("");
		txtItem3.setText("");
		txtQtd3.setText("");

		txtValor4.setText("");
		txtItem4.setText("");
		txtQtd4.setText("");

		txtValor5.setText("");
		txtItem5.setText("");
		txtQtd5.setText("");

		txtValor6.setText("");
		txtItem6.setText("");
		txtQtd6.setText("");

	}

	public void habilitaItens1(Boolean b) {

		txtValor1.setEnabled(b);
		txtItem1.setEnabled(b);
		txtQtd1.setEnabled(b);
	}

	public void habilitaItens2(Boolean b) {

		txtValor2.setEnabled(b);
		txtItem2.setEnabled(b);
		txtQtd2.setEnabled(b);
	}

	public void habilitaItens3(Boolean b) {

		txtValor3.setEnabled(b);
		txtItem3.setEnabled(b);
		txtQtd3.setEnabled(b);
	}

	public void habilitaItens4(Boolean b) {

		txtValor4.setEnabled(b);
		txtItem4.setEnabled(b);
		txtQtd4.setEnabled(b);
	}

	public void habilitaItens5(Boolean b) {

		txtValor5.setEnabled(b);
		txtItem5.setEnabled(b);
		txtQtd5.setEnabled(b);
	}

	public void habilitaItens6(Boolean b) {

		txtValor6.setEnabled(b);
		txtItem6.setEnabled(b);
		txtQtd6.setEnabled(b);
	}

}
