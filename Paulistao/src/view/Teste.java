package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Teste extends JFrame {

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
					Teste frame = new Teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Teste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pInicial = new JPanel();
		tabbedPane.addTab("Inicial", null, pInicial, null);
		
		JLabel lblPaulistao = new JLabel("Campeonato Paulista");
		lblPaulistao.setForeground(Color.BLUE);
		lblPaulistao.setFont(new Font("Georgia", Font.BOLD, 24));
		lblPaulistao.setBounds(115, 24, 287, 52);
		pInicial.add(lblPaulistao);
		
		JLabel lblClicar = new JLabel("Clique no bot\u00E3o para dividir os times em 4 grupos");
		lblClicar.setForeground(Color.DARK_GRAY);
		lblClicar.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblClicar.setBounds(92, 114, 357, 24);
		pInicial.add(lblClicar);
		
		JButton btnGrupos = new JButton("GERAR GRUPOS");
		btnGrupos.setForeground(Color.BLUE);
		btnGrupos.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnGrupos.setBounds(186, 184, 147, 23);
		pInicial.add(btnGrupos);
		
		JScrollPane spInicial = new JScrollPane();
		spInicial.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spInicial.setBounds(10, 236, 514, 183);
		pInicial.add(spInicial);
		
		JTextArea taGrupos = new JTextArea();
		spInicial.setViewportView(taGrupos);
		
		JPanel pRodadas = new JPanel();
		tabbedPane.addTab("Rodadas", null, pRodadas, null);
		
		JLabel lblRodadas = new JLabel("Rodadas");
		lblRodadas.setForeground(Color.BLUE);
		lblRodadas.setFont(new Font("Georgia", Font.BOLD, 24));
		lblRodadas.setBounds(198, 23, 115, 28);
		pRodadas.add(lblRodadas);
		
		JLabel lblClicarRodadas = new JLabel("Clique no bot\u00E3o para gerar as rodadas dos jogos");
		lblClicarRodadas.setForeground(Color.DARK_GRAY);
		lblClicarRodadas.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		lblClicarRodadas.setBounds(86, 81, 323, 28);
		pRodadas.add(lblClicarRodadas);
		
		JButton btnRodadas = new JButton("GERAR RODADAS");
		btnRodadas.setForeground(Color.BLUE);
		btnRodadas.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnRodadas.setBounds(157, 140, 176, 23);
		pRodadas.add(btnRodadas);
		
		JScrollPane spRodadas = new JScrollPane();
		spRodadas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spRodadas.setBounds(10, 185, 514, 245);
		pRodadas.add(spRodadas);
		
		JTextArea taRodadas = new JTextArea();
		spRodadas.setViewportView(taRodadas);
		taRodadas.setForeground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
	}

}
