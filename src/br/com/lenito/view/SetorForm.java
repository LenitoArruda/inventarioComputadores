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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import br.com.lenito.controller.SetorController;
import br.com.lenito.entity.Setor;
import br.com.lenito.table.SetorTableModel;
import net.miginfocom.swing.MigLayout;

public class SetorForm extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel lbNome;
	private JTextField txtNome;
	private JPanel panelAdd, panelTable, panelButtons;
	private JButton btnNew, btnSave, btnRemove, btnUpdate, btnCancel;
	private JTable table;
	private JScrollPane scrollPane;

	private List<Setor> setorList;
	private int idSetor;
	
	private int salvar = 1;
	private int selecionar = 0;

	public SetorForm() {

		super("Cadastro de Marcas");
		setContentPane(new JPanel());
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/LogoIntercam.jpg")).getImage());

		// Criação do painel Add
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Setor"));
		panelAdd.setBounds(5, 0, 470, 60);

		// Criação dos labels
		lbNome = new JLabel("Nome");

		// Criação dos txts
		txtNome = new JTextField(50);

		// Adicionando os txts e os labels no painel
		panelAdd.add(lbNome);
		panelAdd.add(txtNome, "span, growx");

		// Criação do painel dos botões
		panelButtons = new JPanel(new MigLayout());
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 65, 470, 40);

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

		// Incluindo os botões no painel
		panelButtons.add(btnNew, "gapleft 90");
		panelButtons.add(btnCancel);
		panelButtons.add(btnSave, "gap unrelated");
		panelButtons.add(btnUpdate, "gap unrelated");
		panelButtons.add(btnRemove);

		// Criação do painel da tabela
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Setores"));
		panelTable.setBounds(5, 110, 470, 240);

		// Criação da tabela
		table = new JTable();

		// Criação da barra de rolagem da tabela
		scrollPane = new JScrollPane(table);

		// Adicionando a tabela no painel de tabela
		panelTable.add(scrollPane);

		// Atualizar a lista
		refreshTable();
		// Bloquear txts para edição
		enableFields(selecionar);

		// Adicionando os paineis no form
		getContentPane().add(panelAdd);
		getContentPane().add(panelButtons);
		getContentPane().add(panelTable);

		// Setando configurações do form
		setMinimumSize(new Dimension(495, 395));
		setVisible(true);

		// Configurando o botão Save
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onSaveSetor();

			}
		});

		// Configurando o Cancel
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enableFields(selecionar);
				txtNome.setText("");

			}
		});

		// Configurando o botão New
		btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onNovoSetor();

			}
		});

		// Configurando o botão Remove
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onRemoverSetor();

			}
		});

		// Configurando o botão Update
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onAlterarSetor();

			}
		});

		// Setando os txts para atualizar de acordo com a linha selecionada da
		// tabela
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getRowSelectionAllowed() == true){
					int rowIndex = table.getSelectedRow();
					Setor setor = new SetorTableModel(setorList).get(rowIndex);
					idSetor = setor.getId();

					txtNome.setText(setor.getNome());
				}

			}
		});

	}

	// Método do botão New
	private void onNovoSetor() {

		idSetor = 0;
		enableFields(salvar);

	}

	// Método do botão Remove
	private void onRemoverSetor() {
		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione a setor a ser removido!");
			return;
		}

		Setor setor = new SetorTableModel(setorList).get(rowIndex);
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?", "Excluir setor",
				JOptionPane.YES_NO_OPTION);
		if (confirm != 0) {
			return;
		}

		int result = new SetorController().excluirSetor(setor.getId());
		if (result == 1) {

			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
			enableFields(selecionar);
			txtNome.setText("");

		} else {

			JOptionPane.showMessageDialog(this, "Tente Novamente!");

		}
	}

	// Método do botão Update
	private void onAlterarSetor() {

		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o setor a ser alterado!");
			return;
		}
		Setor setor = new SetorTableModel(setorList).get(rowIndex);
		idSetor = setor.getId();

		txtNome.setText(setor.getNome());
		enableFields(salvar);
		table.setRowSelectionAllowed(false);
		
	}
	
	// Método do botão Save
	private void onSaveSetor() {

		Setor setor = new Setor();
		// Verifica~ção se todos os campos foram preenchidos
		if (txtNome.getText().length() > 0) {

			setor.setNome(txtNome.getText());

		} else {
			JOptionPane.showMessageDialog(this, "Todos os campos devem serem preenchidos!");
			return;
		}

		int result = 0;

		if (idSetor == 0) {

			result = new SetorController().addSetor(setor);

		} else {

			setor.setId(idSetor);
			result = new SetorController().alterarSetor(setor);
			idSetor = 0;
		}

		if (result == 1) {

			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
			enableFields(selecionar);
			refreshTable();
			txtNome.setText("");
		} else {

			JOptionPane.showMessageDialog(this, "Tente Novamente!");

		}

	}


	//Método para desabilitar e habilitar os elementos do form
	private void enableFields(int i){
		if(i==0){
			
			btnRemove.setEnabled(true);
			btnNew.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnCancel.setEnabled(false);
			btnSave.setEnabled(false);
			txtNome.setEnabled(false);
			table.setRowSelectionAllowed(true);		
			
		}else{
			
			btnRemove.setEnabled(false);
			btnNew.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnCancel.setEnabled(true);
			btnSave.setEnabled(true);
			txtNome.setEnabled(true);
			table.setRowSelectionAllowed(false);
		}

	}
	
	// Método para atualizar a tabela
	private void refreshTable() {

		setorList = new SetorController().findSetores();

		if (setorList != null) {

			table.setModel(new SetorTableModel(setorList));			

		}
	}
	

}
