package br.com.lenito.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import br.com.lenito.controller.MarcaController;
import br.com.lenito.controller.PesquisaController;
import br.com.lenito.controller.SetorController;
import br.com.lenito.controller.UsuarioController;
import br.com.lenito.entity.Marca;
import br.com.lenito.entity.Pesquisa;
import br.com.lenito.entity.Setor;
import br.com.lenito.entity.Usuario;
import br.com.lenito.table.PesquisaTableModel;
import br.com.lenito.utilitarios.Data;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class PrincipalForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar barraMenu;
	private JMenu cadastroMenu, relatMenu, opMenu, editarMenu, pedidoMenu;
	private JMenuItem setorMenu, marcaMenu, computadorMenu, empresaMenu, computadorRelat, limparDados, sair,
			calculadora, copiar, colar, recortar, atualizar, doacaoMenu, doacaoRelat, preventivaRelat, usuarioMenu,
			contratoMenu, compraMenu;
	private JSeparator separador, separador1, separador2;
	private ButtonGroup bgPesquisar;
	private JRadioButton rbNome, rbIp, rbUsuario;
	private JPanel panelPesquisar, panelData, panelTipo, panelTable;
	private JLabel lbData, lbNome, lbIp, lbUsuario, lbImagem, lbUsuarioLogado;
	private JTextField txtPesquisar;
	private JButton btnPesquisar;
	private Data d;
	private String data;
	private JTable tabela;
	private JScrollPane scrollPane;
	private List<Pesquisa> pesquisaList;

	public PrincipalForm(String UsuarioLogado) {

		// Setando o form
		super("Inventário de Computadores");
		JPanel panel = new JPanel();
		setContentPane(panel);
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/LogoIntercam.jpg")));

		// Criação do Menu
		barraMenu = new JMenuBar();
		getContentPane().add(barraMenu);
		barraMenu.setBounds(0, 0, 558, 20);

		// Menu Opções
		opMenu = new JMenu("Opções");
		barraMenu.add(opMenu);
		atualizar = new JMenuItem("Atualizar tabela");
		opMenu.add(atualizar);
		calculadora = new JMenuItem("Calculadora");
		opMenu.add(calculadora);
		limparDados = new JMenuItem("Limpar dados de pesquisa");
		opMenu.add(limparDados);
		separador = new JSeparator();
		opMenu.add(separador);
		sair = new JMenuItem("Sair");
		opMenu.add(sair);

		// Menu Editar
		editarMenu = new JMenu("Editar");
		barraMenu.add(editarMenu);
		copiar = new JMenuItem("Copiar");
		colar = new JMenuItem("Colar");
		recortar = new JMenuItem("Recortar");
		editarMenu.add(copiar);
		editarMenu.add(colar);
		editarMenu.add(recortar);

		// Menu Cadastro
		cadastroMenu = new JMenu("Cadastro");
		barraMenu.add(cadastroMenu);
		setorMenu = new JMenuItem("Setor");
		marcaMenu = new JMenuItem("Marca");
		empresaMenu = new JMenuItem("Empresa");
		computadorMenu = new JMenuItem("Computador");
		doacaoMenu = new JMenuItem("Itens para doação");
		usuarioMenu = new JMenuItem("Usuário");
		separador1 = new JSeparator();
		cadastroMenu.add(computadorMenu);
		cadastroMenu.add(empresaMenu);
		cadastroMenu.add(marcaMenu);
		cadastroMenu.add(setorMenu);
		cadastroMenu.add(usuarioMenu);
		cadastroMenu.add(separador1);
		cadastroMenu.add(doacaoMenu);
		cadastroMenu.setEnabled(true);

		// Menu Pedido de compras
		pedidoMenu = new JMenu("Compras");
		barraMenu.add(pedidoMenu);
		compraMenu = new JMenuItem("Pagamento de Compra");
		contratoMenu = new JMenuItem("Pagamento de Contrato");
		pedidoMenu.add(compraMenu);
		pedidoMenu.add(contratoMenu);

		// Menu Relatórios
		relatMenu = new JMenu("Relatórios");
		separador2 = new JSeparator();
		barraMenu.add(relatMenu);
		computadorRelat = new JMenuItem("Computadores");
		doacaoRelat = new JMenuItem("Doações");
		preventivaRelat = new JMenuItem("Manutenção Preventiva");
		relatMenu.add(computadorRelat);
		relatMenu.add(doacaoRelat);
		relatMenu.add(separador2);
		relatMenu.add(preventivaRelat);
		relatMenu.setEnabled(true);

		// Atribuindo cada form conforme o menu

		// Opções -> Atualizar Tabela
		atualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				pesquisaList = new PesquisaController().findAll();
				tabela.setModel(new PesquisaTableModel(pesquisaList));
			}
		});

		// Opções -> Calculadora
		calculadora.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					Runtime.getRuntime().exec("cmd.exe /C start calc.exe");

				} catch (IOException e1) {

					e1.printStackTrace();

				}
			}
		});

		// Opções -> Limpar dados de pesquisa
		limparDados.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				limparCampos();
			}
		});

		// Opções -> Sair
		sair.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (op == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					return;
				}
			}
		});

		// Editar -> Copiar
		copiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				txtPesquisar.setFocusable(true);
				txtPesquisar.copy();
			}
		});

		// Editar -> Colar
		colar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				txtPesquisar.setFocusable(true);
				txtPesquisar.paste();
			}
		});

		// Editar -> Recortar
		recortar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				txtPesquisar.setFocusable(true);
				txtPesquisar.cut();
			}
		});

		// Cadastro -> Computadores
		computadorMenu.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				new ComputadorForm("", "", "", "", "", 0, 0, 0, "");
			}
		});

		// Cadastro -> Empresa
		empresaMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new EmpresaForm();
			}
		});

		// Cadastro -> Marca
		marcaMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new MarcaForm();
			}
		});

		// Cadastro -> Setor
		setorMenu.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				new SetorForm();
			}
		});

		// Cadastro -> Usuário
		usuarioMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new UsuarioForm();
			}
		});

		// Cadastro -> Itens para Doação
		doacaoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new DoacaoForm();
			}
		});

		// Pedido de compra -> Compra
		compraMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				new CompraForm();

			}

		});

		// Pedido de compra -> Contrato
		contratoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ContratoForm();
			}

		});

		// Relatórios -> Computadores
		computadorRelat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				// new SelecionarDiretorioForm("Relatório de computadores", null, "", "", "",
				// "", "", 0, "", "");
				new SelecionarDiretorioForm("Relatório de computadores");
			}
		});

		// Relatórios -> Doações
		doacaoRelat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				new SelecionarDiretorioForm("Relatório de doações");
			}
		});

		// Relatórios -> Manutenção preventiva
		preventivaRelat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				new SelecionarDiretorioForm("Manutenção Preventiva Anual");
			}
		});

		// Criação do Painel da pesquisa
		panelPesquisar = new JPanel();
		panelPesquisar.setBorder(BorderFactory.createTitledBorder("Pesquisar:"));
		panelPesquisar.setBounds(5, 25, 533, 112);
		panelPesquisar.setLayout(null);
		getContentPane().add(panelPesquisar);

		// Criação dos componentes para pesquisa
		ClassLoader loader = getClass().getClassLoader();
		lbNome = new JLabel("Nome");
		lbNome.setBounds(10, 20, 48, 14);

		lbIp = new JLabel("    IP");
		lbIp.setBounds(146, 20, 37, 14);

		lbUsuario = new JLabel(" Usuário");
		lbUsuario.setBounds(267, 20, 48, 14);

		lbImagem = new JLabel(new ImageIcon(loader.getResource("img/LogoIntercam.jpg")));
		lbImagem.setBounds(396, 11, 127, 92);

		rbIp = new JRadioButton();
		rbIp.setBounds(177, 16, 21, 21);

		rbNome = new JRadioButton();
		rbNome.setBounds(51, 16, 21, 21);

		rbUsuario = new JRadioButton();
		rbUsuario.setBounds(321, 16, 21, 21);

		txtPesquisar = new JTextField();
		txtPesquisar.setBounds(13, 23, 312, 20);

		btnPesquisar = new JButton(new ImageIcon(loader.getResource("img/pesquisar.png")));
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setBounds(335, 23, 39, 20);

		bgPesquisar = new ButtonGroup();
		bgPesquisar.add(rbNome);
		bgPesquisar.add(rbIp);
		bgPesquisar.add(rbUsuario);

		// Adicionando os componentes no painel pesquisar
		panelPesquisar.add(txtPesquisar);
		panelPesquisar.add(btnPesquisar);
		panelPesquisar.add(lbImagem);

		// Criando o painel tipo
		panelTipo = new JPanel();
		panelTipo.setBounds(13, 56, 361, 47);
		panelTipo.setBorder(BorderFactory.createTitledBorder("Pesquisar por: "));

		// Adicionando componentes no painel tipo
		panelTipo.setLayout(null);
		panelTipo.add(lbNome);
		panelTipo.add(rbNome);
		panelTipo.add(lbIp);
		panelTipo.add(rbIp);
		panelTipo.add(lbUsuario);
		panelTipo.add(rbUsuario);
		panelPesquisar.add(panelTipo);

		// Configurando botão pesquisar
		btnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtPesquisar.getText().length() > 0) {
					if (rbNome.isSelected() == false && rbIp.isSelected() == false && rbUsuario.isSelected() == false) {
						JOptionPane.showMessageDialog(btnPesquisar, "Selecione o tipo de pesquisa!");
						;
						return;
					} else {
						refreshTable();
					}

				} else {
					JOptionPane.showMessageDialog(btnPesquisar, "Digite algo para pesquisar!");
					;
					return;
				}
			}
		});

		// Criação da data
		d = new Data();
		d.lerData();
		data = "São Paulo, " + d.getDia() + " de " + d.getMes() + " de " + d.getAno() + "    -    " + d.getDia_semana();
		lbData = new JLabel(data);
		lbData.setBounds(9, 9, 342, 14);

		// Criação do painel de data
		panelData = new JPanel();
		panelData.setBorder(BorderFactory.createEtchedBorder());
		panelData.setBounds(0, 425, 543, 40);
		panelData.setLayout(null);
		panelData.add(lbData);
		getContentPane().add(panelData);

		// Criação o label usuário
		lbUsuarioLogado = new JLabel("Usuário: " + UsuarioLogado);
		lbUsuarioLogado.setBounds(417, 9, 116, 14);
		panelData.add(lbUsuarioLogado);

		// Painel da tabela de pesquisa
		panelTable = new JPanel();
		panelTable.setBorder(BorderFactory.createTitledBorder("Resutado:"));
		panelTable.setBounds(5, 139, 533, 281);
		getContentPane().add(panelTable);
		panelTable.setLayout(null);

		// Criação da tabela
		tabela = new JTable();

		// Método para quando o usuario dar dois clicks na tabela abrir o form
		// Computador com os dados selecionado
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (tabela.getRowSelectionAllowed() == true) {
						int rowIndex = tabela.getSelectedRow();
						Pesquisa selecionado = new PesquisaTableModel(pesquisaList).get(rowIndex);

						new ComputadorForm(selecionado.getIp(), selecionado.getModelo(), selecionado.getNome(),
								selecionado.getSerialNumber(), selecionado.getUsuario(),
								getIdMarca(selecionado.getMarca()), getIdSetor(selecionado.getSetor()),
								selecionado.getId(), selecionado.getTipo());
					}
				}
			}
		});

		// Criação da barra de rolagem da tabela
		scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 21, 513, 249);

		// Adicionando a tabela no painel de tabela
		panelTable.add(scrollPane);

		// Configurações do form
		setMinimumSize(new Dimension(558, 495));
		setVisible(true);

		// Atualizar tabela
		refreshTable();

		// Habilitando campos conforme o usuario
		List<Usuario> usuarioList = new UsuarioController().findUsuarios();

		for (int i = 0; i < usuarioList.size(); i++) {
			Usuario u = usuarioList.get(i);
			if (u.getNome().equals(UsuarioLogado) == true) {

		
				
			}

		}

	}

	// Implementação do metodo para atualizar a tabela
	private void refreshTable() {

		if (rbNome.isSelected() == false && rbIp.isSelected() == false && rbUsuario.isSelected() == false) {

			pesquisaList = new PesquisaController().findAll();

			if (pesquisaList != null) {

				tabela.setModel(new PesquisaTableModel(pesquisaList));

			}
		} else if (rbNome.isSelected() == true) {

			pesquisaList = new PesquisaController().findNome(txtPesquisar.getText());

			if (pesquisaList != null) {

				if (pesquisaList.size() == 0) {

					JOptionPane.showMessageDialog(this, "Não foi encontrado nenhum nome com esses dados");
					return;

				} else {

					tabela.setModel(new PesquisaTableModel(pesquisaList));

				}
			}

		} else if (rbUsuario.isSelected() == true) {

			pesquisaList = new PesquisaController().findUsuario(txtPesquisar.getText());

			if (pesquisaList != null) {

				if (pesquisaList.size() == 0) {

					JOptionPane.showMessageDialog(this, "Não foi encontrado nenhum usuário com esses dados");
					return;

				} else {

					tabela.setModel(new PesquisaTableModel(pesquisaList));
				}
			}

		} else if (rbIp.isSelected() == true) {

			pesquisaList = new PesquisaController().findIp(txtPesquisar.getText());

			if (pesquisaList != null) {

				if (pesquisaList.size() == 0) {

					JOptionPane.showMessageDialog(this, "Não foi encontrado nenhum IP com esses dados");
					return;

				} else {

					tabela.setModel(new PesquisaTableModel(pesquisaList));
				}
			}
		}
	}

	// Implementando metodo para limpar os campos
	private void limparCampos() {
		bgPesquisar.clearSelection();
		txtPesquisar.setText("");
		refreshTable();

	}

	// Método para retornar o ID da Marca do parametro
	private int getIdMarca(String marca) {
		Marca b;
		int idMarca = 0;
		List<Marca> marcaList = new MarcaController().findMarcas();
		for (int i = 0; i < marcaList.size(); i++) {
			b = marcaList.get(i);
			if (marca.equals(b.getNome())) {
				idMarca = b.getId();
			}
		}
		return idMarca;
	}

	// Método para retornar o ID do Setor do parametro
	private int getIdSetor(String setor) {

		Setor b;
		int idSetor = 0;
		List<Setor> setorList = new SetorController().findSetores();
		for (int i = 0; i < setorList.size(); i++) {
			b = setorList.get(i);
			if (setor.equals(b.getNome())) {
				idSetor = b.getId();
			}
		}
		return idSetor;
	}

}
