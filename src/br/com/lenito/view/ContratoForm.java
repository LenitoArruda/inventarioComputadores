package br.com.lenito.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.lenito.cb.EmpresaCB;
import br.com.lenito.cb.MesCB;
import br.com.lenito.controller.EmpresaController;
import br.com.lenito.entity.Empresa;
import br.com.lenito.entity.Mes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ContratoForm extends JFrame {

	private JPanel panel, panelMeses, panelBtn;
	private JLabel lbQtdMeses, lbMesInicio, lbServico, lbEmpresa, lbPedido, lbDataVenc, lbExemplo, lbIncluir, lbObs1,
			lbObs2, lbPreco;
	private JComboBox<Mes> cbQtdMeses, cbMesInicio;
	private JComboBox<Empresa> cbEmpresa;
	private JTextField txtServico, txtPedido, txtDataVenc, txtPreco, txtObs1, txtObs2;
	private JCheckBox chbObs;
	private JButton btnGerar;

	private List<Empresa> empresaList;

	private static final long serialVersionUID = 1L;

	public ContratoForm() {

		// Configurando o form
		super("Pedido de Contrato");
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/LogoIntercam.jpg")).getImage());
		setMinimumSize(new Dimension(655, 410));
		setResizable(false);
		setVisible(true);

		// Criação dos componentes
		lbQtdMeses = new JLabel("Quantidade de meses");
		lbMesInicio = new JLabel("Mês de início");
		lbServico = new JLabel("Serviço contratado");
		lbEmpresa = new JLabel("Empresa");
		lbPedido = new JLabel("N° do pedido");
		lbDataVenc = new JLabel("Vencimento");
		lbExemplo = new JLabel("( Ex. dd/mm/aaaa )");
		lbIncluir = new JLabel("Incluir Obervação");
		lbObs1 = new JLabel("Observação 1");
		lbObs2 = new JLabel("Observação 2");
		lbPreco = new JLabel("Preço");

		txtServico = new JTextField(200);
		txtPedido = new JTextField(50);
		txtDataVenc = new JTextField(10);
		txtObs1 = new JTextField(100);
		txtObs2 = new JTextField(100);
		txtPreco = new JTextField(15);

		chbObs = new JCheckBox();

		cbQtdMeses = new JComboBox<Mes>();
		cbQtdMeses.setModel(new MesCB(2));

		cbMesInicio = new JComboBox<Mes>();
		cbMesInicio.setModel(new MesCB(1));

		cbEmpresa = new JComboBox<Empresa>();
		empresaList = new EmpresaController().findEmpresas();
		cbEmpresa.setModel(new EmpresaCB(empresaList));

		btnGerar = new JButton("Gerar Pedido de Contrato");

		// Criação do painel Meses
		panelMeses = new JPanel();
		panelMeses.setBorder(BorderFactory.createTitledBorder("Formulário de Contrato"));
		panelMeses.setLayout(null);
		panelMeses.setVisible(true);
		panel.add(panelMeses);
		panelMeses.setBounds(10, 11, 615, 292);

		// Adicionando componentes no painel Meses
		panelMeses.add(lbQtdMeses);
		panelMeses.add(cbQtdMeses);

		panelMeses.add(lbMesInicio);
		panelMeses.add(cbMesInicio);

		panelMeses.add(lbServico);
		panelMeses.add(txtServico);

		panelMeses.add(lbEmpresa);
		panelMeses.add(cbEmpresa);

		panelMeses.add(lbPedido);
		panelMeses.add(txtPedido);

		panelMeses.add(lbDataVenc);
		panelMeses.add(txtDataVenc);
		panelMeses.add(lbExemplo);

		panelMeses.add(lbIncluir);
		panelMeses.add(chbObs);

		panelMeses.add(lbPreco);
		panelMeses.add(txtPreco);

		panelMeses.add(lbObs1);
		panelMeses.add(txtObs1);

		panelMeses.add(lbObs2);
		panelMeses.add(txtObs2);

		// Posicionando componentes no painel Meses
		lbQtdMeses.setBounds(25, 25, 126, 20);
		cbQtdMeses.setBounds(161, 25, 190, 20);

		lbMesInicio.setBounds(361, 25, 81, 20);
		cbMesInicio.setBounds(452, 25, 141, 20);

		lbServico.setBounds(25, 85, 126, 20);
		txtServico.setBounds(161, 85, 432, 20);

		lbEmpresa.setBounds(25, 56, 94, 20);
		cbEmpresa.setBounds(161, 54, 432, 20);

		lbPedido.setBounds(25, 116, 101, 20);
		txtPedido.setBounds(161, 116, 175, 20);

		lbDataVenc.setBounds(346, 116, 72, 20);
		txtDataVenc.setBounds(428, 116, 165, 20);
		lbExemplo.setBounds(428, 136, 135, 20);

		lbPreco.setBounds(25, 147, 116, 20);
		txtPreco.setBounds(161, 147, 175, 20);

		lbIncluir.setBounds(25, 182, 116, 20);
		chbObs.setBounds(158, 182, 21, 20);

		lbObs1.setBounds(25, 213, 86, 20);
		txtObs1.setBounds(161, 213, 432, 20);

		lbObs2.setBounds(25, 244, 86, 20);
		txtObs2.setBounds(161, 244, 432, 20);

		// Criação do painel de botão
		panelBtn = new JPanel();
		panelBtn.setBounds(10, 314, 615, 46);
		panel.add(panelBtn);
		panelBtn.setBorder(BorderFactory.createEtchedBorder());
		panelBtn.setLayout(null);
		panelBtn.setVisible(true);

		// Chamada do metodo limparCampos()
		limparCampos();

		// Adicionando componentes no painel do botão
		panelBtn.add(btnGerar);
		btnGerar.setBounds(196, 11, 210, 24);

		// Configurando o botão
		btnGerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (cbMesInicio.getSelectedIndex() != 0 && cbQtdMeses.getSelectedIndex() != 0
						&& cbEmpresa.getSelectedIndex() != 0 && txtPedido.getText().length() > 0
						&& txtDataVenc.getText().length() > 0 && txtServico.getText().length() > 0
						&& txtPreco.getText().length() > 0) {
					String nome = "Pedido de compra - Contrato";
					Empresa empresa = (Empresa) cbEmpresa.getSelectedItem();
					String pedido = txtPedido.getText();
					String dataVenc = txtDataVenc.getText();
					String obs1 = txtObs1.getText();
					String obs2 = txtObs2.getText();
					String desc = txtServico.getText();
					String preco = txtPreco.getText();
					Mes mes = (Mes) cbQtdMeses.getSelectedItem();
					int qtdMeses = mes.getNumero();
					mes = (Mes) cbMesInicio.getSelectedItem();
					String mesInicio = mes.getNome();

					new SelecionarDiretorioForm(nome).setandoVariaveisContrato(empresa, dataVenc, pedido, obs1, obs2,
							qtdMeses, mesInicio, desc, preco);
					
					limparCampos();


				} else {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
					return;
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

		// Evitando virgula nos txtPreco
		txtPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getSource() == txtPreco) {
					if (e.getKeyChar() == ',') {
						txtPreco.setText(txtPreco.getText().replace(',', '.'));
						txtPreco.setCaretPosition(txtPreco.getText().length());
					}
				}
			}
		});
		
		// Evitando letra em txtPreco
		txtPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();

				if ((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});
		

	}

	public void limparCampos() {
		
		cbEmpresa.setEnabled(true);
		cbMesInicio.setEnabled(true);
		cbQtdMeses.setEnabled(true);
		cbEmpresa.setSelectedIndex(0);
		cbMesInicio.setSelectedIndex(0);
		cbQtdMeses.setSelectedIndex(0);
		txtDataVenc.setText("");
		txtObs1.setText("");
		txtObs1.setEnabled(false);
		txtObs2.setText("");
		txtObs2.setEnabled(false);
		txtPedido.setText("");
		txtServico.setText("");
		txtPreco.setText("");
		chbObs.setSelected(false);

	}
}
