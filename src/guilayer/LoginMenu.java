package guilayer;

import modellayer.*;
import dblayer.*;
import ctrlayer.*;
import java.sql.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtTelefon;
	private MainMenuGUI mGUI;
	private Connection con;
	private UserCtr uCtr;
	
	private JLabel lblStatus;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu frame = new LoginMenu();
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
	public LoginMenu() {
		con = DBConnection.getInstance().getDBcon();
		uCtr = new UserCtr();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					btnLogin.doClick();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 292, 199);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					btnLogin.doClick();
				}
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(12, 16, 94, 16);
		contentPane.add(lblTelefon);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 45, 94, 16);
		contentPane.add(lblPassword);
		
		txtTelefon = new JTextField();
		txtTelefon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					btnLogin.doClick();
				}
			}
		});
		txtTelefon.setBounds(128, 13, 116, 22);
		contentPane.add(txtTelefon);
		txtTelefon.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					btnLogin.doClick();
				}
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTelefon.getText().equals("") || txtPassword.getText().equals(""))
				{
					txtPassword.setText("");
					lblStatus.setVisible(true);
				}
				else
				{
					String phone = txtTelefon.getText();
					String password = txtPassword.getText();
					User user = uCtr.findUser(phone);
					
					if(password.equals(user.getPassword()))
					{
						mGUI.main(null);
						dispose();
					}
				}
			}
		});
		btnLogin.setBounds(12, 74, 232, 25);
		contentPane.add(btnLogin);
		
		lblStatus = new JLabel("Brugernavn eller password er forkert");
		lblStatus.setBounds(12, 112, 232, 16);
		lblStatus.setVisible(false);
		contentPane.add(lblStatus);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					btnLogin.doClick();
				}
			}
		});
		txtPassword.setBounds(128, 42, 116, 22);
		contentPane.add(txtPassword);
	}
}
