package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.RepositorioMidia;
import DAO.RepositorioProduto;
import Models.Produto;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Canvas;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class Interface extends JFrame {

	private JPanel contentPane;
	private JTextField codField;
	private JTextField valorField;
	private JTextField descField;
	private String caminho;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
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
	public Interface() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Consulta = new JPanel();
		tabbedPane.addTab("Consulta", null, Consulta, null);
		Consulta.setLayout(new BorderLayout(0, 0));
		
		JPanel Busca = new JPanel();
		Consulta.add(Busca, BorderLayout.NORTH);
		
		JLabel lblCdigo_1 = new JLabel("C\u00F3digo:");
		Busca.add(lblCdigo_1);
		
		textField_3 = new JTextField();
		Busca.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnConsultar = new JButton("Ver Detalhes");
		Busca.add(btnConsultar);
		
		JPanel Lista = new JPanel();
		Consulta.add(Lista, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		Consulta.add(panel, BorderLayout.SOUTH);
		
		JButton Alfabetica = new JButton("Ordem Alfab\u00E9tica");
		panel.add(Alfabetica);
		
		JButton Crescente = new JButton("Valor (Crescente)");
		Crescente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(Crescente);
		
		JButton Decrescente = new JButton("Valor (Decrescente)");
		panel.add(Decrescente);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		
		JPanel Cadastro = new JPanel();
		tabbedPane.addTab("Cadastro", null, Cadastro, null);
		Cadastro.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCdigo.setBounds(10, 11, 73, 19);
		Cadastro.add(lblCdigo);
		
		codField = new JTextField();
		codField.setBounds(100, 12, 309, 20);
		Cadastro.add(codField);
		codField.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(10, 42, 73, 19);
		Cadastro.add(lblValor);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescrio.setBounds(10, 72, 73, 19);
		Cadastro.add(lblDescrio);
		
		valorField = new JTextField();
		valorField.setBounds(100, 43, 309, 20);
		Cadastro.add(valorField);
		valorField.setColumns(10);
		
		descField = new JTextField();
		descField.setBounds(100, 73, 309, 44);
		Cadastro.add(descField);
		descField.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setEnabled(false);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo = Integer.parseInt(codField.getText());
				double valor = Double.parseDouble(valorField.getText());
				String descricao = descField.getText();
				Produto produto = new Produto(codigo,descricao,valor);
				try {
					RepositorioProduto.armazenar(produto);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					RepositorioMidia.armazenar(produto, caminho);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(274, 184, 135, 29);
		Cadastro.add(btnCadastrar);
		
		JButton btnSelecionarImagem = new JButton("Selecionar imagem");
		btnSelecionarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser abrir = new JFileChooser();
				int retorno = abrir.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					caminho = abrir.getSelectedFile().getAbsolutePath();
					btnCadastrar.setEnabled(true);
				}
			}
		});
		btnSelecionarImagem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSelecionarImagem.setBounds(100, 184, 136, 29);
		Cadastro.add(btnSelecionarImagem);

		
		
	}
}
