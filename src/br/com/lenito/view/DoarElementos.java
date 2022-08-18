package br.com.lenito.view;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.com.lenito.cb.EmpresaCB;
import br.com.lenito.controller.DoacaoController;
import br.com.lenito.controller.EmpresaController;
import br.com.lenito.entity.Doacao;
import br.com.lenito.entity.Empresa;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class DoarElementos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JList<Doacao> listaDoacoes, listaDoar;
	private JButton btnMoverUm, btnMoverTodos, btnRemoverUm, btnRemoverTodos, btnDoar;
	private JScrollPane scrollPaneDoacoes, scrollPaneDoar;
	private JPanel panelDoar, panelEmpresa, panelLista;
	private JComboBox<Empresa> cbEmpresa;
	private DefaultListModel<Doacao> dlmDoacoes, dlmDoar;
	private JLabel lbDoar, lbDoacao;

	private List<Doacao> doacoes;
	private List<Empresa> empresas;

	public DoarElementos() {

		// Conigurações do form
		super("Efetivar Doação");
		setContentPane(new JPanel());
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/LogoIntercam.jpg")).getImage());
		setMinimumSize(new Dimension(580, 360));
		setVisible(true);

		// Criação do painel das listas
		panelLista = new JPanel();
		panelLista.setBorder(BorderFactory.createTitledBorder("Selecione os produtos para doação"));
		panelLista.setBounds(5, 11, 554, 192);
		getContentPane().add(panelLista);
		panelLista.setLayout(null);

		// Criação do label
		lbDoacao = new JLabel("Lista de produtos disponíveis");
		lbDoacao.setBounds(16, 26, 228, 14);
		panelLista.add(lbDoacao);

		lbDoar = new JLabel("Lista de produtos a ser doado");
		lbDoar.setBounds(309, 26, 235, 14);
		panelLista.add(lbDoar);

		// Criando as listas
		listaDoacoes = new JList<Doacao>();
		listaDoacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDoacoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPaneDoacoes = new JScrollPane(listaDoacoes);
		scrollPaneDoacoes.setBounds(16, 45, 228, 130);
		panelLista.add(scrollPaneDoacoes);

		listaDoar = new JList<Doacao>();
		listaDoar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDoar.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPaneDoar = new JScrollPane(listaDoar);
		scrollPaneDoar.setBounds(309, 45, 228, 130);
		panelLista.add(scrollPaneDoar);

		// Criando os List Model
		dlmDoacoes = new DefaultListModel<Doacao>();
		dlmDoar = new DefaultListModel<Doacao>();
		doacoes = new DoacaoController().findDoacoes();

		// Populando o List Model Doacoes
		for (int i = 0; i < doacoes.size(); i++) {
			Doacao doacao = new Doacao();
			doacao = doacoes.get(i);
			dlmDoacoes.addElement(doacao);
		}

		// Atribuindo o List model à lista
		listaDoacoes.setModel(dlmDoacoes);
		listaDoar.setModel(dlmDoar);

		// Criação dos botões
		ClassLoader loader = getClass().getClassLoader();
		btnMoverUm = new JButton(new ImageIcon(loader.getResource("img/MoveUmDireita.png")));
		btnMoverUm.setBounds(252, 45, 49, 25);
		btnMoverUm.setToolTipText("Mover selecionado para Doar");
		panelLista.add(btnMoverUm);

		btnMoverTodos = new JButton(new ImageIcon(loader.getResource("img/MoveTodosDireita.png")));
		btnMoverTodos.setBounds(252, 80, 49, 25);
		btnMoverTodos.setToolTipText("Mover todos para Doar");
		panelLista.add(btnMoverTodos);

		btnRemoverTodos = new JButton(new ImageIcon(loader.getResource("img/MoveTodosEsquerda.png")));
		btnRemoverTodos.setBounds(252, 115, 49, 25);
		btnRemoverTodos.setToolTipText("Remover todos de Doar");
		panelLista.add(btnRemoverTodos);

		btnRemoverUm = new JButton(new ImageIcon(loader.getResource("img/MoveUmEsquerda.png")));
		btnRemoverUm.setBounds(252, 150, 49, 25);
		btnRemoverUm.setToolTipText("Remover selecionado de Doar");
		panelLista.add(btnRemoverUm);

		// Criação do painel da Empresa
		panelEmpresa = new JPanel();
		panelEmpresa.setBorder(BorderFactory.createTitledBorder("Escolha uma empresa para doação"));
		panelEmpresa.setBounds(5, 207, 554, 62);
		getContentPane().add(panelEmpresa);

		// Criação do CB empresa
		empresas = new EmpresaController().findEmpresas();
		panelEmpresa.setLayout(null);
		cbEmpresa = new JComboBox<Empresa>();
		cbEmpresa.setBounds(13, 23, 531, 20);
		cbEmpresa.setModel(new EmpresaCB(empresas));
		panelEmpresa.add(cbEmpresa);

		// Criando o painel do botao doar
		panelDoar = new JPanel();
		panelDoar.setBorder(BorderFactory.createEtchedBorder());
		panelDoar.setBounds(5, 273, 554, 43);
		getContentPane().add(panelDoar);
		panelDoar.setLayout(null);

		// Criando o botão de doação
		btnDoar = new JButton("Doar");
		btnDoar.setBounds(226, 11, 107, 23);
		btnDoar.setToolTipText("Doar");
		panelDoar.add(btnDoar);

		// Eventos dos botões

		// Botão remover um
		btnRemoverUm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dlmDoacoes.addElement(listaDoar.getSelectedValue());
				dlmDoar.removeElement(listaDoar.getSelectedValue());

			}
		});

		// Botão remover todos
		btnRemoverTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < dlmDoar.size(); i++) {

					dlmDoacoes.addElement(dlmDoar.get(i));

				}

				dlmDoar.removeAllElements();

			}
		});

		// Botão mover todos
		btnMoverTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < dlmDoacoes.size(); i++) {

					dlmDoar.addElement(dlmDoacoes.get(i));

				}

				dlmDoacoes.removeAllElements();

			}
		});

		// Botão mover um
		btnMoverUm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dlmDoar.addElement(listaDoacoes.getSelectedValue());
				dlmDoacoes.removeElement(listaDoacoes.getSelectedValue());

			}
		});

		// Botão Doar
		btnDoar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onDoar();
			}
		});

	}

	public void onDoar() {
		if (dlmDoar.size() <= 0) {
			JOptionPane.showMessageDialog(this, "Selecione ao menos 1 equipamento a ser doado!");
			return;
		}
		if (cbEmpresa.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione a empresa desejada!");
			return;
		}
		int confirm = JOptionPane.showConfirmDialog(this,
				"Você tem certeza que deseja doar este(s) equipamento(s) para a empresa: "
						+ cbEmpresa.getSelectedItem(),
				"Doar equipamentos", JOptionPane.YES_NO_OPTION);
		if (confirm != 0) {
			return;
		}
		for (int i = 0; i < dlmDoar.size(); i++) {
			Doacao doacao = dlmDoar.get(i);
			new DoacaoController().excluirDoacao(doacao.getId());
		}
		dlmDoar.removeAllElements();
		JOptionPane.showMessageDialog(this, "Equipamentos doado com sucesso!");

	}
}
