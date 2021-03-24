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

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		JLabel lblPaulistao = new JLabel("Campeonato Paulista");
		lblPaulistao.setForeground(Color.BLUE);
		lblPaulistao.setFont(new Font("Georgia", Font.BOLD, 24));
		lblPaulistao.setBounds(115, 24, 287, 52);
		contentPane.add(lblPaulistao);
		
		JLabel lblClicar = new JLabel("Clique no bot\u00E3o para dividir os times em 4 grupos");
		lblClicar.setForeground(Color.DARK_GRAY);
		lblClicar.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblClicar.setBounds(92, 114, 357, 24);
		contentPane.add(lblClicar);
		
		JButton btnGrupos = new JButton("GERAR GRUPOS");
		btnGrupos.setForeground(Color.BLUE);
		btnGrupos.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnGrupos.setBounds(186, 184, 147, 23);
		contentPane.add(btnGrupos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 236, 514, 183);
		contentPane.add(scrollPane);
		
		JTextArea taGrupos = new JTextArea();
		scrollPane.setViewportView(taGrupos);
	}
}
