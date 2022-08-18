package br.com.lenito.view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import br.com.lenito.entity.Empresa;
import br.com.lenito.workbook.ComputadorWB;
import br.com.lenito.workbook.DoacaoWB;
import br.com.lenito.workbook.PedidoDeCompraWB;
import br.com.lenito.workbook.PedidoDeContratoWB;
import br.com.lenito.workbook.PreventivaWB;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelecionarDiretorioForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtSeleciona;
	private JButton btnSeleciona, btnSalvar;
	private JLabel lblSeleciona;
	private JFileChooser jfc;
	private String caminho = "";
	private String dataVenc, pedido, obs1, obs2, mesInicio, preco, desc;
	private String[][] items;
	private Empresa empresa;
	private int qtdMeses;

	public SelecionarDiretorioForm(String nome) {

		super(nome);
		JPanel panel = new JPanel();
		setContentPane(panel);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setLayout(null);

		lblSeleciona = new JLabel("Selecione o local do arquivo:");
		lblSeleciona.setBounds(24, 5, 241, 20);
		panel.add(lblSeleciona);
		txtSeleciona = new JTextField();
		txtSeleciona.setBounds(24, 25, 241, 20);
		panel.add(txtSeleciona);
		txtSeleciona.setColumns(10);
		txtSeleciona.setEnabled(false);
		ClassLoader loader = getClass().getClassLoader();
		btnSeleciona = new JButton(new ImageIcon(loader.getResource("img/arquivo.png")));
		btnSeleciona.setToolTipText("Buscar");
		btnSeleciona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Selecione o diretório:");
				jfc.setMultiSelectionEnabled(true);
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isDirectory()) {

						caminho = jfc.getSelectedFile().getAbsolutePath();
						txtSeleciona.setText(caminho);

					}
				}

			}
		});
		btnSeleciona.setBounds(279, 11, 35, 34);
		panel.add(btnSeleciona);
		btnSalvar = new JButton("Salvar");
		btnSalvar.setToolTipText("Salvar");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtSeleciona.getText().length() > 0) {

					if (nome.equals("Relatório de computadores")) {

						new ComputadorWB(caminho);
						dispose();

					} else if (nome.equals("Relatório de doações")) {

						new DoacaoWB(caminho);
						dispose();

					} else if (nome.equals("Manutenção Preventiva Anual")) {

						new PreventivaWB(caminho);
						dispose();

					} else if (nome.equals("Pedido de compra - Contrato")) {

						new PedidoDeContratoWB(caminho, empresa, pedido, dataVenc, obs1, obs2, desc, qtdMeses,
								mesInicio, preco);

						dispose();

					} else if (nome.equals("Pedido de compra - Compra")) {

						new PedidoDeCompraWB(caminho, empresa, nome, dataVenc, pedido, obs1, obs2, items);

						dispose();

					}

				} else {

					JOptionPane.showMessageDialog(btnSalvar, "Selecione o destino primeiro!");
				}
			}
		});
		btnSalvar.setBounds(120, 59, 70, 21);
		panel.add(btnSalvar);

		setVisible(true);
		setMinimumSize(new Dimension(355, 130));
		this.setLocationRelativeTo(null);
	}

	public void setandoVariaveisCompra(Empresa empresa, String dataVenc, String pedido, String obs1, String obs2, String[][] items) {

		this.empresa = empresa;
		this.dataVenc = dataVenc;
		this.pedido = pedido;
		this.obs1 = obs1;
		this.obs2 = obs2;
		this.items = items;

	}

	public void setandoVariaveisContrato(Empresa empresa, String dataVenc, String pedido, String obs1, String obs2,
			int qtdMes, String mesInicio, String desc, String preco) {

		this.empresa = empresa;
		this.dataVenc = dataVenc;
		this.pedido = pedido;
		this.obs1 = obs1;
		this.obs2 = obs2;
		this.qtdMeses = qtdMes;
		this.mesInicio = mesInicio;
		this.desc = desc;
		this.preco = preco;

	}
}
