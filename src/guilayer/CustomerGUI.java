package guilayer;
import ctrlayer.*;
import dblayer.*;
import modellayer.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class CustomerGUI extends JFrame {

	private CustomerCtr cusCtr;
	private JPanel contentPane;
	private JTextField txtNavn;
	private JTextField txtAdresse;
	private JTextField txtPostnr;
	private JTextField txtTelefon;
	private JTextField txtEmail;
	private JPanel panelFind;
	private JButton btnOpretKunde;
	private JButton btnFindKunde;
	private JLabel lblNavn;
	private JLabel lblAdresse;
	private JLabel lblPostnr;
	private JLabel lblTelefon;
	private JLabel lblEmail;
	private JButton btnOpret;
	private JLabel lblTelefon_1;
	private JTextField txtFindTelefon;
	private JButton btnFind;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JPanel panelOpret;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI();
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
	public CustomerGUI() 
	{
		cusCtr = new CustomerCtr();
		initComponents();
		
	}
	
	public void initComponents()
	{
		setTitle("Customer GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnOpretKunde = new JButton("Opret kunde");
		btnOpretKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelOpretKunde();
			}
		});
		btnOpretKunde.setBounds(10, 11, 146, 50);
		contentPane.add(btnOpretKunde);
		
		btnFindKunde = new JButton("Find kunde");
		btnFindKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelFindKunde();
			}
		});
		btnFindKunde.setBounds(10, 72, 146, 50);
		contentPane.add(btnFindKunde);	
		
		
		//START Find kunde components
		panelFind = new JPanel();
		panelFind.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelFind.setBounds(166, 11, 836, 600);
		contentPane.add(panelFind);
		panelFind.setLayout(null);
						
		lblTelefon_1 = new JLabel("Telefon");
		lblTelefon_1.setBounds(10, 11, 68, 14);
		panelFind.add(lblTelefon_1);
		
		txtFindTelefon = new JTextField();
		txtFindTelefon.setBounds(88, 8, 125, 20);
		panelFind.add(txtFindTelefon);
		txtFindTelefon.setColumns(10);
		
		btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findCustomer();
			}
		});
		btnFind.setBounds(10, 36, 100, 23);
		panelFind.add(btnFind);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(244, 11, 582, 311);
		panelFind.add(scrollPane);
		
		model = new DefaultTableModel(new Object[][] {},new String[] {"Navn", "Adresse", "Postnr", "By", "Telefon", "Email"});
		table = new JTable(model)
		{
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		scrollPane.setViewportView(table);
		
		JButton btnFindAlle = new JButton("Find alle");
		btnFindAlle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findAllCustomers();
			}
		});
		btnFindAlle.setBounds(120, 36, 89, 23);
		panelFind.add(btnFindAlle);
		//END Find kunde panel
		
		
		//START Opret kunde panel components
		panelOpret = new JPanel();
		panelOpret.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelOpret.setBounds(166, 11, 836, 600);
		contentPane.add(panelOpret);
		panelOpret.setLayout(null);
		
		
		lblNavn = new JLabel("Navn");
		lblNavn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNavn.setBounds(10, 11, 77, 14);
		panelOpret.add(lblNavn);
				
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdresse.setBounds(10, 36, 77, 14);
		panelOpret.add(lblAdresse);
		
		lblPostnr = new JLabel("Postnr");
		lblPostnr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPostnr.setBounds(10, 61, 77, 14);
		panelOpret.add(lblPostnr);
		
		lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefon.setBounds(10, 86, 77, 14);
		panelOpret.add(lblTelefon);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(10, 111, 77, 14);
		panelOpret.add(lblEmail);
				
		txtNavn = new JTextField();
		txtNavn.setBounds(97, 9, 134, 20);
		panelOpret.add(txtNavn);
		txtNavn.setColumns(10);
		
		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(97, 34, 134, 20);
		panelOpret.add(txtAdresse);
		
		txtPostnr = new JTextField();
		txtPostnr.setColumns(10);
		txtPostnr.setBounds(97, 59, 134, 20);
		panelOpret.add(txtPostnr);
		
		txtTelefon = new JTextField();
		txtTelefon.setColumns(10);
		txtTelefon.setBounds(97, 84, 134, 20);
		panelOpret.add(txtTelefon);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(97, 109, 134, 20);
		panelOpret.add(txtEmail);
		
		btnOpret = new JButton("Opret");
		btnOpret.setBounds(10, 136, 89, 23);
		panelOpret.add(btnOpret);
			
		panelOpret.setVisible(false);

		//END Opret kunde panel
		
	}
	
	
	public void panelOpretKunde()
	{	
		panelFind.setVisible(false);
		panelOpret.setVisible(true);
		
	}
	
	public void panelFindKunde()
	{
		panelOpret.setVisible(false);
		panelFind.setVisible(true);
	}
	
	public void findCustomer()
	{
		String phone = txtFindTelefon.getText();
		Customer cus = cusCtr.findByPhoneNo(phone);
		
		model.addRow(new Object[]{cus.getName(),cus.getAddress(),cus.getZipCode(),cus.getLocation().getCity(),cus.getPhone(),cus.getEmail()});
	}
	
	public void findAllCustomers()
	{
		ArrayList<Customer> list = new ArrayList<Customer>();
		list = cusCtr.findAllCustomers();
		for(int i = 0; i < list.size(); i++)
		{
			model.addRow(new Object[]{list.get(i).getName(), list.get(i).getAddress(), list.get(i).getZipCode(),
						 list.get(i).getLocation().getCity(), list.get(i).getPhone(), list.get(i).getEmail()});
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
