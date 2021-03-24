package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TelaRodadas extends JFrame {

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
					TelaRodadas frame = new TelaRodadas();
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
	public TelaRodadas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRodadas = new JLabel("Rodadas");
		lblRodadas.setForeground(Color.BLUE);
		lblRodadas.setFont(new Font("Georgia", Font.BOLD, 24));
		lblRodadas.setBounds(198, 23, 115, 28);
		contentPane.add(lblRodadas);
		
		JLabel lblClicarRodadas = new JLabel("Clique no bot\u00E3o para gerar as rodadas dos jogos");
		lblClicarRodadas.setForeground(Color.DARK_GRAY);
		lblClicarRodadas.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		lblClicarRodadas.setBounds(86, 81, 323, 28);
		contentPane.add(lblClicarRodadas);
		
		JButton btnRodadas = new JButton("GERAR RODADAS");
		btnRodadas.setForeground(Color.BLUE);
		btnRodadas.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnRodadas.setBounds(157, 140, 176, 23);
		contentPane.add(btnRodadas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 185, 514, 245);
		contentPane.add(scrollPane);
		
		JTextArea taRodadas = new JTextArea();
		scrollPane.setViewportView(taRodadas);
		taRodadas.setForeground(Color.WHITE);
	}

}
