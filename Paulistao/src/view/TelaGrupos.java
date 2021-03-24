package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class TelaGrupos extends JFrame {

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
					TelaGrupos frame = new TelaGrupos();
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
	public TelaGrupos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGrupos = new JLabel("GRUPOS");
		lblGrupos.setForeground(Color.BLUE);
		lblGrupos.setFont(new Font("Georgia", Font.BOLD, 24));
		lblGrupos.setBounds(199, 30, 118, 49);
		contentPane.add(lblGrupos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 514, 282);
		contentPane.add(scrollPane);
		
		JTextArea taGrupos = new JTextArea();
		scrollPane.setViewportView(taGrupos);
	}

}
