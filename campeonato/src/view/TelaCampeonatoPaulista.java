package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class TelaCampeonatoPaulista extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public TelaCampeonatoPaulista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 480);
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
		lblPaulistao.setForeground(Color.BLUE);
		lblPaulistao.setFont(new Font("Georgia", Font.BOLD, 24));
		lblPaulistao.setBounds(128, 5, 262, 28);
		pInicial.add(lblPaulistao);
		
		JLabel lblClicar = new JLabel("Clique no bot\u00E3o para dividir os times em 4 grupos");
		lblClicar.setForeground(Color.DARK_GRAY);
		lblClicar.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblClicar.setBounds(82, 82, 348, 19);
		pInicial.add(lblClicar);
		
		JButton btnGrupos = new JButton("GERAR GRUPOS");
		btnGrupos.setForeground(Color.BLUE);
		btnGrupos.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnGrupos.setBounds(179, 126, 170, 23);
		pInicial.add(btnGrupos);
		
		JScrollPane spInicial = new JScrollPane();
		spInicial.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spInicial.setBounds(10, 160, 504, 206);
		pInicial.add(spInicial);
		
		JTextArea taGrupos = new JTextArea();
		spInicial.setViewportView(taGrupos);
		
	// Tela que mostra os grupos formados
		JPanel pGrupos = new JPanel();
		tabbedPane.addTab("Grupos", null, pGrupos, null);
		pGrupos.setLayout(null);
		
		JLabel lblGrupos = new JLabel("GRUPOS");
		lblGrupos.setBounds(198, 11, 127, 28);
		lblGrupos.setForeground(Color.BLUE);
		lblGrupos.setFont(new Font("Georgia", Font.BOLD, 24));
		pGrupos.add(lblGrupos);
		
		JScrollPane spGrupos = new JScrollPane();
		spGrupos.setBounds(10, 135, 514, 282);
		pGrupos.add(spGrupos);
		
		JTextArea tabGrupos = new JTextArea();
		spGrupos.setViewportView(tabGrupos);
	
	// Tela gerar rodadas e jogos	
		JPanel pRodadas = new JPanel();
		tabbedPane.addTab("Rodadas", null, pRodadas, null);
		pRodadas.setLayout(null);
		
		JLabel lblRodadas = new JLabel("Rodadas");
		lblRodadas.setForeground(Color.BLUE);
		lblRodadas.setFont(new Font("Georgia", Font.BOLD, 24));
		lblRodadas.setBounds(201, 11, 106, 28);
		pRodadas.add(lblRodadas);
		
		JLabel lblClicarRodadas = new JLabel("Clique no bot\u00E3o para gerar as rodadas dos jogos");
		lblClicarRodadas.setForeground(Color.DARK_GRAY);
		lblClicarRodadas.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		lblClicarRodadas.setBounds(100, 61, 308, 17);
		pRodadas.add(lblClicarRodadas);
		
		JButton btnRodadas = new JButton("GERAR RODADAS");
		btnRodadas.setForeground(Color.BLUE);
		btnRodadas.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnRodadas.setBounds(159, 102, 192, 23);
		pRodadas.add(btnRodadas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 499, 221);
		pRodadas.add(scrollPane);
		
		JScrollPane spRodadas = new JScrollPane();
		scrollPane.setViewportView(spRodadas);
		spRodadas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JTextArea taRodadas = new JTextArea();
		spRodadas.setViewportView(taRodadas);
		taRodadas.setForeground(Color.WHITE);
		
	// Tela que busca as rodadas pela data que o usuário digitou
		JPanel pBuscaRodadas = new JPanel();
		tabbedPane.addTab("Buscar Rodadas", null, pBuscaRodadas, null);
		pBuscaRodadas.setLayout(null);
		
		JLabel lblBuscar = new JLabel("BUSCAR  RODADA");
		lblBuscar.setBounds(156, 11, 214, 43);
		lblBuscar.setForeground(Color.BLUE);
		lblBuscar.setFont(new Font("Georgia", Font.BOLD, 20));
		pBuscaRodadas.add(lblBuscar);
		
		JLabel lblDigite = new JLabel("Digite uma data");
		lblDigite.setBounds(104, 92, 127, 23);
		lblDigite.setForeground(Color.DARK_GRAY);
		lblDigite.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		pBuscaRodadas.add(lblDigite);
		
		//Define as máscaras
	    MaskFormatter mascaraData = null;

	    try{
	           mascaraData = new MaskFormatter("##/##/####");
	           mascaraData.setPlaceholderCharacter('_');
	    }
	    catch(ParseException excp) {
	           System.err.println("Erro na formatação: " + excp.getMessage());
	           System.exit(-1);
	    }
		
		JFormattedTextField tfData = new JFormattedTextField(mascaraData);
		tfData.setBounds(279, 94, 102, 23);
		pBuscaRodadas.add(tfData);
		
		JScrollPane spBuscaRodadas = new JScrollPane();
		spBuscaRodadas.setBounds(10, 173, 487, 219);
		spBuscaRodadas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pBuscaRodadas.add(spBuscaRodadas);
		
		JTextArea taBusca = new JTextArea();
		spBuscaRodadas.setViewportView(taBusca);
	}
}
