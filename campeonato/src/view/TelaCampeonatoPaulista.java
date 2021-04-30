package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
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

import controller.Controller4Finais;
import controller.ControllerClassificacao;
import controller.ControllerGrupo;
import controller.ControllerInserirResultados;
import controller.ControllerRodada;
import model.Jogos;
import model.ModelResultados;
import model.ModeloTabelaResultados;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JSeparator;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JDesktopPane;

public class TelaCampeonatoPaulista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ControllerGrupo cG = new ControllerGrupo();
	private ControllerRodada cR = new ControllerRodada();
	private ControllerClassificacao cC = new ControllerClassificacao();
	private ControllerInserirResultados cIR = new ControllerInserirResultados();
	private Controller4Finais c4F = new Controller4Finais();
	private JTable tableBusca;
	private JTable tableInsereResultado;
	private JTextField tFGolA;
	private JTextField tFGolB;
	private JTable tableGrupoA;
	private JTable tableGrupoB;
	private JTable tableGrupoC;
	private JTable tableGrupoD;
	private JTable tableclassificacaoGeral;
	private JTextField tfJ3A;
	private JTextField tfJ3B;
	private JTextField tfJ2A;
	private JTextField tfJ2B;
	private JTextField tfJ4A;
	private JTextField tfJ4B;
	private JTextField tfJ1A;
	private JTextField tfJ1B;

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
		setBounds(100, 100, 768, 616);
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

		btnGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cG.geraGrupos(taGrupos);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Grupo ja existe",
							JOptionPane.ERROR_MESSAGE);
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

					JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), "Erro Jogos",
							JOptionPane.ERROR_MESSAGE);
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

		// Tela que mostra os grupos formados
		JPanel pGrupos = new JPanel();
		tabbedPane.addTab("Grupos", null, pGrupos, null);
		pGrupos.setLayout(null);

		// Acao do bot�o gerar grupos

		JButton btnBuscaGrupo = new JButton("MOSTRAR");
		btnBuscaGrupo.setBounds(647, 515, 92, 23);
		btnBuscaGrupo.setForeground(Color.BLUE);
		btnBuscaGrupo.setFont(new Font("Georgia", Font.PLAIN, 12));
		pGrupos.add(btnBuscaGrupo);

		JScrollPane scrollPaneA = new JScrollPane();
		scrollPaneA.setBounds(32, 39, 694, 87);
		pGrupos.add(scrollPaneA);

		tableGrupoA = new JTable();
		scrollPaneA.setViewportView(tableGrupoA);

		JLabel lblGrupoA = new JLabel("Grupo A");
		lblGrupoA.setBounds(324, 22, 70, 15);
		pGrupos.add(lblGrupoA);

		JLabel lblGrupoA_1 = new JLabel("Grupo B");
		lblGrupoA_1.setBounds(324, 138, 70, 15);
		pGrupos.add(lblGrupoA_1);

		JScrollPane scrollPaneA_1 = new JScrollPane();
		scrollPaneA_1.setBounds(32, 155, 694, 87);
		pGrupos.add(scrollPaneA_1);

		tableGrupoB = new JTable();
		scrollPaneA_1.setViewportView(tableGrupoB);

		JScrollPane scrollPaneA_1_1 = new JScrollPane();
		scrollPaneA_1_1.setBounds(32, 281, 694, 87);
		pGrupos.add(scrollPaneA_1_1);

		tableGrupoC = new JTable();
		scrollPaneA_1_1.setViewportView(tableGrupoC);

		JLabel lblGrupoA_1_1 = new JLabel("Grupo C");
		lblGrupoA_1_1.setBounds(324, 264, 70, 15);
		pGrupos.add(lblGrupoA_1_1);

		JButton btnBuscaGrupo_1 = new JButton("MOSTRAR");
		btnBuscaGrupo_1.setForeground(Color.BLUE);
		btnBuscaGrupo_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnBuscaGrupo_1.setBounds(647, 641, 92, 23);
		pGrupos.add(btnBuscaGrupo_1);

		JLabel lblGrupoA_1_1_1 = new JLabel("Grupo D");
		lblGrupoA_1_1_1.setBounds(324, 392, 70, 15);
		pGrupos.add(lblGrupoA_1_1_1);

		JScrollPane scrollPaneA_1_1_1 = new JScrollPane();
		scrollPaneA_1_1_1.setBounds(32, 407, 694, 87);
		pGrupos.add(scrollPaneA_1_1_1);

		tableGrupoD = new JTable();
		scrollPaneA_1_1_1.setViewportView(tableGrupoD);

		btnBuscaGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					cC.buscaClassificacaoGrupos(tableGrupoA, "A");
					cC.buscaClassificacaoGrupos(tableGrupoB, "B");
					cC.buscaClassificacaoGrupos(tableGrupoC, "C");
					cC.buscaClassificacaoGrupos(tableGrupoD, "D");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

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
		lblInsereResultado.setBounds(206, 12, 259, 43);
		pInserirResultado.add(lblInsereResultado);

		JLabel lblDigite_1 = new JLabel("Digite uma data");
		lblDigite_1.setForeground(Color.DARK_GRAY);
		lblDigite_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDigite_1.setBounds(497, 44, 118, 23);
		pInserirResultado.add(lblDigite_1);

		final JFormattedTextField tfData_1 = new JFormattedTextField(mascaraData);
		tfData_1.setBounds(614, 44, 102, 23);
		pInserirResultado.add(tfData_1);

		JButton btnBuscaJogo_1 = new JButton("BUSCAR");
		btnBuscaJogo_1.setForeground(Color.BLUE);
		btnBuscaJogo_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnBuscaJogo_1.setBounds(624, 79, 86, 23);
		pInserirResultado.add(btnBuscaJogo_1);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(12, 145, 705, 302);
		pInserirResultado.add(scrollPane_1_1);

		tableInsereResultado = new JTable();
		tableInsereResultado.setToolTipText("");
		tableInsereResultado.setForeground(Color.BLACK);
		scrollPane_1_1.setViewportView(tableInsereResultado);

		final JCheckBox cbTodasDatas_1 = new JCheckBox("Todas as datas");
		cbTodasDatas_1.setBounds(565, 110, 151, 23);
		pInserirResultado.add(cbTodasDatas_1);

		JLabel lblMandante = new JLabel("Mandante");
		lblMandante.setBounds(42, 92, 86, 19);
		pInserirResultado.add(lblMandante);

		tFGolA = new JTextField();
		tFGolA.setBounds(123, 87, 30, 30);
		pInserirResultado.add(tFGolA);
		tFGolA.setColumns(10);

		tFGolB = new JTextField();
		tFGolB.setColumns(10);
		tFGolB.setBounds(172, 87, 30, 30);
		pInserirResultado.add(tFGolB);

		JLabel lblMandante_1 = new JLabel("Vistante");
		lblMandante_1.setBounds(215, 92, 86, 19);
		pInserirResultado.add(lblMandante_1);

		JButton btnInserir = new JButton("INSERIR");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int golA, golB, linha;

				linha = tableInsereResultado.getSelectedRow();

				if (linha != -1) {

					try {

						if (!tFGolA.getText().equals("") && !tFGolB.getText().equals("")) {
							golA = Integer.parseInt(tFGolA.getText());
							golB = Integer.parseInt(tFGolB.getText());

							if (cbTodasDatas_1.isSelected()) {

								cIR.inseriGols(linha, golA, golB, "-1", tableInsereResultado);
								limpaTextf(tFGolA, tFGolB);

							} else {

								cIR.inseriGols(linha, golA, golB, tfData_1.getText(), tableInsereResultado);
								limpaTextf(tFGolA, tFGolB);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Digite os Gols", "Erro", JOptionPane.ERROR_MESSAGE);
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Digite somente numeros inteiros", "Erro",
								JOptionPane.ERROR_MESSAGE);

					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um jogo", "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnInserir.setForeground(Color.BLUE);
		btnInserir.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnInserir.setBounds(290, 90, 86, 23);
		pInserirResultado.add(btnInserir);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Classificação Geral", null, panel, null);
		panel.setLayout(null);

		JLabel lblInsereResultado_1 = new JLabel("Classificação");
		lblInsereResultado_1.setForeground(Color.BLUE);
		lblInsereResultado_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblInsereResultado_1.setBounds(276, 21, 198, 43);
		panel.add(lblInsereResultado_1);

		JButton btnClassificacaoGeral = new JButton("MOSTRAR");
		btnClassificacaoGeral.setForeground(Color.BLUE);
		btnClassificacaoGeral.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnClassificacaoGeral.setBounds(319, 76, 101, 23);
		panel.add(btnClassificacaoGeral);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 152, 727, 282);
		panel.add(scrollPane_2);

		tableclassificacaoGeral = new JTable();
		scrollPane_2.setViewportView(tableclassificacaoGeral);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("4final", null, panel_1, null);
		panel_1.setLayout(null);
		
		tfJ3A = new JTextField();
		tfJ3A.setColumns(10);
		tfJ3A.setBounds(39, 297, 142, 24);
		panel_1.add(tfJ3A);
		
		JLabel lblX_1 = new JLabel("X");
		lblX_1.setFont(new Font("Dialog", Font.BOLD, 30));
		lblX_1.setBounds(188, 297, 23, 24);
		panel_1.add(lblX_1);
		
		tfJ3B = new JTextField();
		tfJ3B.setColumns(10);
		tfJ3B.setBounds(218, 297, 142, 24);
		panel_1.add(tfJ3B);
		
		tfJ2A = new JTextField();
		tfJ2A.setColumns(10);
		tfJ2A.setBounds(400, 164, 142, 24);
		panel_1.add(tfJ2A);
		
		JLabel lblX_2 = new JLabel("X");
		lblX_2.setFont(new Font("Dialog", Font.BOLD, 30));
		lblX_2.setBounds(549, 164, 23, 24);
		panel_1.add(lblX_2);
		
		tfJ2B = new JTextField();
		tfJ2B.setColumns(10);
		tfJ2B.setBounds(579, 164, 142, 24);
		panel_1.add(tfJ2B);
		
		tfJ4A = new JTextField();
		tfJ4A.setColumns(10);
		tfJ4A.setBounds(400, 297, 142, 24);
		panel_1.add(tfJ4A);
		
		JLabel lblX_3 = new JLabel("X");
		lblX_3.setFont(new Font("Dialog", Font.BOLD, 30));
		lblX_3.setBounds(549, 297, 23, 24);
		panel_1.add(lblX_3);
		
		tfJ4B = new JTextField();
		tfJ4B.setColumns(10);
		tfJ4B.setBounds(579, 297, 142, 24);
		panel_1.add(tfJ4B);
		
		tfJ1A = new JTextField();
		tfJ1A.setColumns(10);
		tfJ1A.setBounds(39, 164, 142, 24);
		panel_1.add(tfJ1A);
		
		JLabel lblX_1_1 = new JLabel("X");
		lblX_1_1.setFont(new Font("Dialog", Font.BOLD, 30));
		lblX_1_1.setBounds(188, 164, 23, 24);
		panel_1.add(lblX_1_1);
		
		tfJ1B = new JTextField();
		tfJ1B.setColumns(10);
		tfJ1B.setBounds(218, 164, 142, 24);
		panel_1.add(tfJ1B);
		
		JButton btn4Finais = new JButton("MOSTRAR");
		btn4Finais.setForeground(Color.BLUE);
		btn4Finais.setFont(new Font("Dialog", Font.PLAIN, 12));
		btn4Finais.setBounds(602, 36, 101, 23);
		panel_1.add(btn4Finais);
		
		JLabel lblJogo = new JLabel("Jogo 1");
		lblJogo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblJogo.setBounds(174, 112, 70, 15);
		panel_1.add(lblJogo);
		
		JLabel lblJogo_1 = new JLabel("Jogo 2");
		lblJogo_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblJogo_1.setBounds(525, 112, 70, 15);
		panel_1.add(lblJogo_1);
		
		JLabel lblJogo_2 = new JLabel("Jogo 3");
		lblJogo_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblJogo_2.setBounds(174, 258, 70, 15);
		panel_1.add(lblJogo_2);
		
		JLabel lblJogo_3 = new JLabel("Jogo 4");
		lblJogo_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblJogo_3.setBounds(525, 258, 70, 15);
		panel_1.add(lblJogo_3);
		
		JLabel lblPaulistao_1 = new JLabel("Quartas de finais");
		lblPaulistao_1.setForeground(Color.BLUE);
		lblPaulistao_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblPaulistao_1.setBounds(233, 29, 248, 28);
		panel_1.add(lblPaulistao_1);

		btnClassificacaoGeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					cC.buscaClassificacaoGrupos(tableclassificacaoGeral, "");

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		btn4Finais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					c4F.busca4Finais(tfJ1A, tfJ1B, "A");
					c4F.busca4Finais(tfJ2A, tfJ2B, "B");
					c4F.busca4Finais(tfJ3A, tfJ3B, "C");
					c4F.busca4Finais(tfJ4A, tfJ4B, "D");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

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

	private static void limpaTextf(JTextField tFGolA2, JTextField tFGolB2) {

		tFGolA2.setText("");
		tFGolB2.setText("");

	}
}
