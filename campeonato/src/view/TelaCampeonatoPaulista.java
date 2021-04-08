package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ControllerBuscaJogos;
import controller.ControllerGrupo;
import controller.ControllerRodada;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class TelaCampeonatoPaulista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String data;

	
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
		
	// Tela Inicial	que gera os grupos do campeonato paulista
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
		lblGrupos.setBounds(300, 11, 127, 28);
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
		
		ActionListener chamadaGrupos = new ControllerGrupo(taGrupos, tabGrupos);
		btnGrupos.addActionListener(chamadaGrupos);
	
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
		
		JScrollPane spRodadas = new JScrollPane();
		spRodadas.setBounds(24, 157, 703, 278);
		pRodadas.add(spRodadas);
		spRodadas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spRodadas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JTextArea taRodadas = new JTextArea();
		spRodadas.setViewportView(taRodadas);
		taRodadas.setForeground(Color.black);
		
		// Acao do bot�o gerar rodadas
		
		ActionListener chamadaRodadas = new ControllerRodada(taRodadas);
		btnRodadas.addActionListener(chamadaRodadas);
		
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
		lblDigite.setBounds(243, 92, 127, 23);
		lblDigite.setForeground(Color.DARK_GRAY);
		lblDigite.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		pBuscaRodadas.add(lblDigite);
		
		//Define as m�scaras
	    MaskFormatter mascaraData = null;

	    try{
	           mascaraData = new MaskFormatter("##/##/####");
	           mascaraData.setPlaceholderCharacter('_');
	    }
	    catch(ParseException excp) {
	           System.err.println("Erro na formata��o: " + excp.getMessage());
	           System.exit(-1);
	    }
		
		JFormattedTextField tfData = new JFormattedTextField(mascaraData);
		tfData.setBounds(442, 94, 102, 23);
		pBuscaRodadas.add(tfData);
		
		JScrollPane spBuscaRodadas = new JScrollPane();
		spBuscaRodadas.setBounds(49, 162, 653, 254);
		spBuscaRodadas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pBuscaRodadas.add(spBuscaRodadas);
		
		JTextArea taBusca = new JTextArea();
		spBuscaRodadas.setViewportView(taBusca);
		taBusca.setForeground(Color.black);
		
		// acao de buscar jogos
		ActionListener chamadaJogos = new ControllerBuscaJogos(tfData.getText(), taBusca);
		tfData.addActionListener(chamadaJogos);
	}
}
