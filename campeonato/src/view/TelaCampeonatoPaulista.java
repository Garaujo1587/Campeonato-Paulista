package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ControllerGrupo;
import controller.ControllerInserirResultados;
import controller.ControllerRodada;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class TelaCampeonatoPaulista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ControllerGrupo cG = new ControllerGrupo();
	private ControllerRodada cR = new ControllerRodada();
	private ControllerInserirResultados cIR = new ControllerInserirResultados();
	private JTable tableBusca;
	private JTable tableInsereResultado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					TelaCampeonatoPaulista frame = new TelaCampeonatoPaulista();
					frame.setVisible(true);
					frame.setTitle("Campeonato Paulista");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCampeonatoPaulista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		// Tela Inicial que gera os grupos do campeonato paulista
		JPanel pInicial = new JPanel();
		tabbedPane.addTab("Inicial", null, pInicial, null);
		pInicial.setLayout(null);

		JLabel lblPaulistao = new JLabel("Campeonato Paulista");
		lblPaulistao.setBounds(231, 11, 310, 28);
		lblPaulistao.setForeground(Color.BLUE);
		lblPaulistao.setFont(new Font("Georgia", Font.BOLD, 24));
		pInicial.add(lblPaulistao);

		JLabel lblClicar = new JLabel("Clique no bot\u00E3o para dividir os times em 4 grupos");
		lblClicar.setBounds(193, 77, 391, 19);
		lblClicar.setForeground(Color.DARK_GRAY);
		lblClicar.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		pInicial.add(lblClicar);

		JButton btnGrupos = new JButton("GERAR GRUPOS");
		btnGrupos.setBounds(280, 126, 170, 23);
		btnGrupos.setForeground(Color.BLUE);
		btnGrupos.setFont(new Font("Georgia", Font.PLAIN, 12));
		pInicial.add(btnGrupos);

		JScrollPane spInicial = new JScrollPane();
		spInicial.setBounds(115, 167, 504, 268);
		spInicial.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pInicial.add(spInicial);

		final JTextArea taGrupos = new JTextArea();
		spInicial.setViewportView(taGrupos);
		taGrupos.setForeground(Color.black);

		// Tela que mostra os grupos formados
		JPanel pGrupos = new JPanel();
		tabbedPane.addTab("Grupos", null, pGrupos, null);
		pGrupos.setLayout(null);

		JLabel lblGrupos = new JLabel("GRUPOS");
		lblGrupos.setBounds(271, 24, 127, 28);
		lblGrupos.setForeground(Color.BLUE);
		lblGrupos.setFont(new Font("Georgia", Font.BOLD, 24));
		pGrupos.add(lblGrupos);

		JScrollPane spTabGrupos = new JScrollPane();
		spTabGrupos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spTabGrupos.setBounds(58, 134, 611, 301);
		pGrupos.add(spTabGrupos);

		JScrollPane spGrupos = new JScrollPane();
		spTabGrupos.setViewportView(spGrupos);

		final JTextArea tabGrupos = new JTextArea();
		spGrupos.setViewportView(tabGrupos);
		tabGrupos.setForeground(Color.black);

		// Acao do bot�o gerar grupos

		JButton btnBuscaGrupo = new JButton("MOSTRAR GRUPOS");
		btnBuscaGrupo.setBounds(234, 69, 192, 23);
		btnBuscaGrupo.setForeground(Color.BLUE);
		btnBuscaGrupo.setFont(new Font("Georgia", Font.PLAIN, 12));
		pGrupos.add(btnBuscaGrupo);

		btnBuscaGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cG.mostraGrupos(tabGrupos);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cG.geraGrupos(taGrupos);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Tela gerar rodadas e jogos
		JPanel pRodadas = new JPanel();
		tabbedPane.addTab("Rodadas", null, pRodadas, null);
		pRodadas.setLayout(null);

		JLabel lblRodadas = new JLabel("Rodadas");
		lblRodadas.setBounds(311, 11, 150, 28);
		lblRodadas.setForeground(Color.BLUE);
		lblRodadas.setFont(new Font("Georgia", Font.BOLD, 24));
		pRodadas.add(lblRodadas);

		JLabel lblClicarRodadas = new JLabel("Clique no bot\u00E3o para gerar as rodadas dos jogos");
		lblClicarRodadas.setBounds(204, 62, 353, 17);
		lblClicarRodadas.setForeground(Color.DARK_GRAY);
		lblClicarRodadas.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		pRodadas.add(lblClicarRodadas);

		JButton btnRodadas = new JButton("GERAR RODADAS");
		btnRodadas.setBounds(269, 113, 192, 23);
		btnRodadas.setForeground(Color.BLUE);
		btnRodadas.setFont(new Font("Georgia", Font.PLAIN, 12));
		pRodadas.add(btnRodadas);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 158, 690, 275);
		pRodadas.add(scrollPane);

		table = new JTable();
		table.setToolTipText("");
		table.setForeground(Color.BLACK);
		scrollPane.setViewportView(table);

		btnRodadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cR.geraRodadas(table);
				} catch (ClassNotFoundException | SQLException e1) {
				
					JOptionPane.showMessageDialog(null, "Crie os Grupos", "Grupo não existe", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Tela que busca as rodadas pela data que o usu�rio digitou
		JPanel pBuscaRodadas = new JPanel();
		tabbedPane.addTab("Buscar Rodadas", null, pBuscaRodadas, null);
		pBuscaRodadas.setLayout(null);

		JLabel lblBuscar = new JLabel("BUSCAR  RODADA");
		lblBuscar.setBounds(256, 11, 214, 43);
		lblBuscar.setForeground(Color.BLUE);
		lblBuscar.setFont(new Font("Georgia", Font.BOLD, 20));
		pBuscaRodadas.add(lblBuscar);

		JLabel lblDigite = new JLabel("Digite uma data");
		lblDigite.setBounds(87, 94, 127, 23);
		lblDigite.setForeground(Color.DARK_GRAY);
		lblDigite.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		pBuscaRodadas.add(lblDigite);

		// Define as m�scaras
		MaskFormatter mascaraData = null;

		try {
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println("Erro na formata��o: " + excp.getMessage());
			System.exit(-1);
		}

		final JFormattedTextField tfData = new JFormattedTextField(mascaraData);
		tfData.setBounds(232, 94, 102, 23);
		pBuscaRodadas.add(tfData);

		JButton btnBuscaJogo = new JButton("BUSCAR");
		btnBuscaJogo.setBounds(399, 94, 86, 23);
		btnBuscaJogo.setForeground(Color.BLUE);
		btnBuscaJogo.setFont(new Font("Georgia", Font.PLAIN, 12));
		pBuscaRodadas.add(btnBuscaJogo);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 128, 705, 302);
		pBuscaRodadas.add(scrollPane_1);

		tableBusca = new JTable();
		tableBusca.setToolTipText("");
		tableBusca.setForeground(Color.BLACK);
		scrollPane_1.setViewportView(tableBusca);

		final JCheckBox cbTodasDatas = new JCheckBox("Todas as datas");
		cbTodasDatas.setBounds(396, 52, 151, 23);
		pBuscaRodadas.add(cbTodasDatas);
		
		JPanel pInserirResultado = new JPanel();
		tabbedPane.addTab("Inserir Resultados", null, pInserirResultado, null);
		pInserirResultado.setLayout(null);
		
		btnBuscaJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cbTodasDatas.isSelected()) {
						cR.buscaRodadas(tableBusca);

					} else {
						cR.buscaRodadas(tableBusca, tfData.getText());
					}
				} catch (ClassNotFoundException | SQLException e1) {

					JOptionPane.showMessageDialog(null, "Não é uma Data valida", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JLabel lblInsereResultado = new JLabel("INSERIR RESULTADOS");
		lblInsereResultado.setForeground(Color.BLUE);
		lblInsereResultado.setFont(new Font("Dialog", Font.BOLD, 20));
		lblInsereResultado.setBounds(232, 18, 259, 43);
		pInserirResultado.add(lblInsereResultado);
		
		JLabel lblDigite_1 = new JLabel("Digite uma data");
		lblDigite_1.setForeground(Color.DARK_GRAY);
		lblDigite_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDigite_1.setBounds(87, 111, 127, 23);
		pInserirResultado.add(lblDigite_1);
		
		final JFormattedTextField tfData_1 = new JFormattedTextField(mascaraData);
		tfData_1.setBounds(232, 111, 102, 23);
		pInserirResultado.add(tfData_1);
		
		JButton btnBuscaJogo_1 = new JButton("BUSCAR");
		btnBuscaJogo_1.setForeground(Color.BLUE);
		btnBuscaJogo_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnBuscaJogo_1.setBounds(399, 111, 86, 23);
		pInserirResultado.add(btnBuscaJogo_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(12, 145, 705, 302);
		pInserirResultado.add(scrollPane_1_1);
		
		tableInsereResultado = new JTable();
		tableInsereResultado.setToolTipText("");
		tableInsereResultado.setForeground(Color.BLACK);
		scrollPane_1_1.setViewportView(tableInsereResultado);
		
		final JCheckBox cbTodasDatas_1 = new JCheckBox("Todas as datas");
		cbTodasDatas_1.setBounds(396, 69, 151, 23);
		pInserirResultado.add(cbTodasDatas_1);

		btnBuscaJogo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cbTodasDatas_1.isSelected()) {
						cIR.buscaRodadas(tableInsereResultado);

					} else {
						cIR.buscaRodadas(tableInsereResultado, tfData_1.getText());
					}
				} catch (ClassNotFoundException | SQLException e1) {

					JOptionPane.showMessageDialog(null, "Não é uma Data valida", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}
}
