package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Categoria;
import br.com.senai.core.domain.DiaDaSemana;
import br.com.senai.core.domain.Endereco;
import br.com.senai.core.domain.Horario;
import br.com.senai.core.domain.Restaurante;
import br.com.senai.core.service.CategoriaService;
import br.com.senai.core.service.HorarioService;
import br.com.senai.core.service.RestauranteService;

public class ViewConfHorario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtAbertura;
	private JTextField edtFechamento;
	private JTable tableHorario;
	private JComboBox<Restaurante> cbRestaurante;
	
	private HorarioService horarioService = new HorarioService();
	
	private Restaurante restaurante;
	private Horario horario;

	
	private RestauranteService restService = new RestauranteService();
	
	public void carregarComboRestaurante() {
		List<Restaurante> restaurante = restService.listarTodos();
		for (Restaurante rest : restaurante) {
			cbRestaurante.addItem(rest);
		}
	}

	public ViewConfHorario() {
		setTitle("Gerenciar Horários - Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRestaurante = new JLabel("Restaurante");
		lblRestaurante.setBounds(25, 36, 70, 16);
		contentPane.add(lblRestaurante);
		
		cbRestaurante = new JComboBox<Restaurante>();
		
		
		cbRestaurante.setBounds(100, 32, 538, 25);
		contentPane.add(cbRestaurante);
		
		JLabel lblDiaSemana = new JLabel("Dia da Semana");
		lblDiaSemana.setBounds(12, 73, 85, 16);
		contentPane.add(lblDiaSemana);
		
		JComboBox cbDiaSemana = new JComboBox();
		cbDiaSemana.setBounds(100, 69, 117, 25);
		contentPane.add(cbDiaSemana);
		
		JLabel lblAbertura = new JLabel("Abertura");
		lblAbertura.setBounds(221, 73, 56, 16);
		contentPane.add(lblAbertura);
		
		edtAbertura = new JTextField();
		edtAbertura.setBounds(272, 71, 96, 20);
		contentPane.add(edtAbertura);
		edtAbertura.setColumns(10);
		
		JLabel lblFechamento = new JLabel("Fechamento");
		lblFechamento.setBounds(368, 73, 70, 16);
		contentPane.add(lblFechamento);
		
		edtFechamento = new JTextField();
		edtFechamento.setColumns(10);
		edtFechamento.setBounds(439, 71, 96, 20);
		contentPane.add(edtFechamento);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					DiaDaSemana diaSemana = (DiaDaSemana)cbDiaSemana.getSelectedItem();
					String abertura = edtAbertura.getText();
					String fechamento = edtFechamento.getText();
					//Restaurante restaurante = cbRestaurante.getSelectedItem();
					
					if (restaurante == null || diaSemana == null || abertura == null || fechamento == null) {
							
						JOptionPane.showMessageDialog(contentPane, "Todos os campos devem ser preenchidos");
						
					} else {
						
						horario.setDiaSemana(diaSemana);;
						horario.setRestaurante(restaurante);
						//horario.setHoraAbertura(abertura);
						//horario.setHoraFechamento(fechamento);
					
						//horarioService.salvar(restaurante);
						JOptionPane.showMessageDialog(contentPane, "Horário adicionado com sucesso ao restaurante " + restaurante.getNome() + "!!");
						restaurante = null;
						horario = null;
						
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
					boolean isRestauranteOK = restaurante.getId() <= 0;
					if (isRestauranteOK) {
						restaurante = null;
					}
				}
				
			}
		});
		btnAdicionar.setBounds(542, 69, 96, 26);
		contentPane.add(btnAdicionar);
		
		JLabel lblHorarios = new JLabel("Horários");
		lblHorarios.setBounds(12, 123, 56, 16);
		contentPane.add(lblHorarios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 151, 398, 145);
		contentPane.add(scrollPane);
		
		tableHorario = new JTable();
		scrollPane.setViewportView(tableHorario);
		
		JLabel lblAcoes = new JLabel("Ações");
		lblAcoes.setBounds(428, 151, 36, 16);
		contentPane.add(lblAcoes);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(439, 179, 199, 26);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(439, 225, 199, 26);
		contentPane.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja realmente cancelar a operação?", "Apagar Campos!", JOptionPane.YES_NO_OPTION);
				if (opcao == 0) {
					edtAbertura.setText("");
					edtFechamento.setText("");
					cbRestaurante.setSelectedIndex(0);
					
				}
				
			}
		});
		btnCancelar.setBounds(542, 337, 96, 26);
		contentPane.add(btnCancelar);
	
		setLocationRelativeTo(null);
		this.carregarComboRestaurante();
	}
}
