package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TelaBuscarRodadas extends JFrame {

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
					TelaBuscarRodadas frame = new TelaBuscarRodadas();
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
	public TelaBuscarRodadas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscar = new JLabel("BUSCAR  RODADA");
		lblBuscar.setForeground(Color.BLUE);
		lblBuscar.setFont(new Font("Georgia", Font.BOLD, 20));
		lblBuscar.setBounds(156, 11, 214, 43);
		contentPane.add(lblBuscar);
		
		JLabel lblDigite = new JLabel("Digite uma data");
		lblDigite.setForeground(Color.DARK_GRAY);
		lblDigite.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		lblDigite.setBounds(104, 92, 127, 23);
		contentPane.add(lblDigite);
		
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
		contentPane.add(tfData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 173, 514, 244);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}
