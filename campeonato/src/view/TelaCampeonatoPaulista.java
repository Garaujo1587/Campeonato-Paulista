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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ControllerGrupo;
import controller.ControllerRodada;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Button;

public class TelaCampeonatoPaulista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ControllerGrupo cG = new ControllerGrupo();
	private ControllerRodada cR = new ControllerRodada();
	private JTable tableBusca;

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
		lblPaulistao.setBounds(231, 11, 262, 28);
		lblPaulistao.setForeground(Color.BLUE);
		lblPaulistao.setFont(new Font("Georgia", Font.BOLD, 24));
		pInicial.add(lblPaulistao);

		JLabel lblClicar = new JLabel("Clique no bot\u00E3o para dividir os times em 4 grupos");
		lblClicar.setBounds(193, 77, 348, 19);
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

		JTextArea taGrupos = new JTextArea();
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

		JTextArea tabGrupos = new JTextArea();
		spGrupos.setViewportView(tabGrupos);
		tabGrupos.setForeground(Color.black);

		// Acao do bot�o gerar grupos

		
		Button btnBuscaGrupo = new Button("Mostrar");
		btnBuscaGrupo.setBounds(234, 69, 192, 23);
		btnBuscaGrupo.setForeground(Color.BLUE);
		btnBuscaGrupo.setFont(new Font("Georgia", Font.PLAIN, 12));
		pGrupos.add(btnBuscaGrupo);
		
		btnBuscaGrupo.addActionListener(
				  new ActionListener()
				  {
				    public void actionPerformed(ActionEvent e)
				    {
				      try {
						cG.mostraGrupos(tabGrupos);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    }
				  }
				);
		
		btnGrupos.addActionListener(
				  new ActionListener()
				  {
				    public void actionPerformed(ActionEvent e)
				    {
				      try {
						cG.geraGrupos(taGrupos);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    }
				  }
				);

		// Tela gerar rodadas e jogos
		JPanel pRodadas = new JPanel();
		tabbedPane.addTab("Rodadas", null, pRodadas, null);
		pRodadas.setLayout(null);

		JLabel lblRodadas = new JLabel("Rodadas");
		lblRodadas.setBounds(311, 11, 106, 28);
		lblRodadas.setForeground(Color.BLUE);
		lblRodadas.setFont(new Font("Georgia", Font.BOLD, 24));
		pRodadas.add(lblRodadas);

		JLabel lblClicarRodadas = new JLabel("Clique no bot\u00E3o para gerar as rodadas dos jogos");
		lblClicarRodadas.setBounds(204, 62, 308, 17);
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
	
		

		
		btnRodadas.addActionListener(
				  new ActionListener()
				  {
				    public void actionPerformed(ActionEvent e)
				    {
				      try {
						cR.geraRodadas(table);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    }
				  }
				);

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

		JFormattedTextField tfData = new JFormattedTextField(mascaraData);
		tfData.setBounds(232, 94, 102, 23);
		pBuscaRodadas.add(tfData);
		
		Button btnBuscaJogo = new Button("Buscar");
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
		scrollPane_1.setColumnHeaderView(tableBusca);
		
		btnBuscaJogo.addActionListener(
				  new ActionListener()
				  {
				    public void actionPerformed(ActionEvent e)
				    {
				      try {
						cR.buscaRodadas(tableBusca, tfData.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    }
				  }
				);
		
		
	}
}
