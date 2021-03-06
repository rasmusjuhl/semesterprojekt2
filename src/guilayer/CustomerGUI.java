package guilayer;
import ctrlayer.*;
import dblayer.*;
import modellayer.*;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JProgressBar;


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
	private JScrollPane scrollPaneRet;
	private DefaultTableModel model;
	private JPanel panelRet;
	private JButton btnRetKunde;
	private DefaultTableModel modelRet;
	private JTable tableRet;
	private JButton btnRet;
	private JPanel panelOpret;
	private JScrollPane scrollPane;
	private JButton btnSlet;
	private JLabel lblBy;
	private JLabel lblKundeOprettet;
	private JLabel lblForbindelse;
	private JProgressBar progressBar;
	private JButton btnTilbage;
	private static CustomerGUI frame;
	private User user;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CustomerGUI();
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
	private CustomerGUI() 
	{
		if(user == null)
		{
			user = LoginMenu.getInstance().getUser();
		}
		cusCtr = new CustomerCtr();
		initComponents();
	}

	public static CustomerGUI getInstance()
	{
		if(frame == null)
		{
			frame = new CustomerGUI();
		}
		return frame;
	}


	public void initComponents()
	{
		setTitle("Kunde menu - logget ind som " + user.getName());
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
				sletTabel(model);
				panelFindKunde();
			}
		});
		btnFindKunde.setBounds(10, 72, 146, 50);
		contentPane.add(btnFindKunde);	

		btnRetKunde = new JButton("Ret/slet kunde");
		btnRetKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sletTabel(modelRet);
				panelRetKunde();
				FillTableRet ftr = new FillTableRet();  // private class
				ftr.worker.execute();
				//				findAllCustomers(modelRet);				
			}
		});
		btnRetKunde.setBounds(10, 133, 146, 50);
		contentPane.add(btnRetKunde);

		btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				MainMenuGUI.main(null);	
			}
		});
		btnTilbage.setBounds(10, 194, 146, 50);
		contentPane.add(btnTilbage);


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
				findCustomer(model);
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
				findAllCustomersFind();
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
		txtNavn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblKundeOprettet.setVisible(false);
			}
		});
		txtNavn.setBounds(97, 9, 134, 20);
		panelOpret.add(txtNavn);
		txtNavn.setColumns(10);

		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(97, 34, 134, 20);
		panelOpret.add(txtAdresse);

		txtPostnr = new JTextField();
		txtPostnr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				getCity();
			}
		});
		txtPostnr.setColumns(10);
		txtPostnr.setBounds(97, 59, 134, 20);
		panelOpret.add(txtPostnr);

		txtTelefon = new JTextField();
		txtTelefon.setColumns(10);
		txtTelefon.setBounds(97, 84, 134, 20);
		panelOpret.add(txtTelefon);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					btnOpret.doClick();
				}		
			}
		});
		txtEmail.setColumns(10);
		txtEmail.setBounds(97, 109, 134, 20);
		panelOpret.add(txtEmail);

		btnOpret = new JButton("Opret");
		btnOpret.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				checkRegularExp();
			}
		});
		btnOpret.setBounds(10, 136, 89, 23);
		panelOpret.add(btnOpret);		

		lblBy = new JLabel("By");
		lblBy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBy.setBounds(240, 62, 102, 14);
		panelOpret.add(lblBy);
		lblBy.setVisible(false);

		lblKundeOprettet = new JLabel("");
		lblKundeOprettet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKundeOprettet.setBounds(10, 170, 418, 14);
		panelOpret.add(lblKundeOprettet);
		lblKundeOprettet.setVisible(false);
		//END Opret kunde panel

		//START Ret/slet kunde panel
		panelRet = new JPanel();
		panelRet.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelRet.setBounds(166, 11, 836, 600);
		contentPane.add(panelRet);
		panelRet.setLayout(null);	

		scrollPaneRet = new JScrollPane();
		scrollPaneRet.setBounds(10, 11, 816, 319);
		panelRet.add(scrollPaneRet);

		modelRet = new DefaultTableModel(new Object[][] {},new String[] {"Navn", "Adresse", "Postnr", "By", "Telefon", "Email"});
		tableRet = new JTable(modelRet)
		{
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		scrollPaneRet.setViewportView(tableRet);

		btnRet = new JButton("Ret");
		btnRet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCustomer();
			}
		});
		btnRet.setBounds(10, 341, 89, 23);
		panelRet.add(btnRet);

		btnSlet = new JButton("Slet");
		btnSlet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCustomer();
			}
		});
		btnSlet.setBounds(109, 341, 89, 23);
		panelRet.add(btnSlet);



		lblForbindelse = new JLabel("");
		lblForbindelse.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblForbindelse.setBounds(10, 597, 146, 14);
		contentPane.add(lblForbindelse);

		progressBar = new JProgressBar();
		progressBar.setBounds(10, 572, 146, 14);
		contentPane.add(progressBar);
		progressBar.setMaximum(50);



		CheckOnline co = new CheckOnline();
		co.worker.execute();


		panelRet.setVisible(false);
		panelOpret.setVisible(false);
		panelFind.setVisible(false);
	}


	public void panelOpretKunde()
	{	
		panelRet.setVisible(false);
		panelFind.setVisible(false);
		panelOpret.setVisible(true);

	}

	public void panelFindKunde()
	{
		panelRet.setVisible(false);
		panelOpret.setVisible(false);
		panelFind.setVisible(true);
	}

	private void panelRetKunde()
	{
		panelOpret.setVisible(false);
		panelFind.setVisible(false);
		panelRet.setVisible(true);
	}


	public void findCustomer(DefaultTableModel dmodel)
	{
		sletTabel(dmodel);
		String phone = txtFindTelefon.getText();
		if(!phone.equals(""))
		{
			Customer cus = cusCtr.findByPhoneNo(phone);
			dmodel.addRow(new Object[]{cus.getName(),cus.getAddress(),cus.getZipCode(),cus.getLocation().getCity(),cus.getPhone(),cus.getEmail()});
		}		
	}

	public void findAllCustomersFind()
	{
		FillTable ft = new FillTable();  // private class
		ft.worker.execute();
	}

	public void findAllCustomers(DefaultTableModel dmodel)
	{
		sletTabel(dmodel);

		ArrayList<Customer> list = new ArrayList<Customer>();
		list = cusCtr.findAllCustomers();
		for(int i = 0; i < list.size(); i++)
		{
			dmodel.addRow(new Object[]{list.get(i).getName(), list.get(i).getAddress(), list.get(i).getZipCode(),
					list.get(i).getLocation().getCity(), list.get(i).getPhone(), list.get(i).getEmail()});
		}

	}

	private void sletTabel(DefaultTableModel dmodel)
	{
		int rowCount = dmodel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			dmodel.removeRow(i);
		}	
	}

	private void updateCustomer()
	{
		int row = tableRet.getSelectedRow();
		if(row != -1)
		{
			String name = (String) tableRet.getValueAt(row, 0);
			String phone = (String) tableRet.getValueAt(row, 4);
			String email  = (String) tableRet.getValueAt(row, 5);
			String address =(String) tableRet.getValueAt(row, 1);
			String zipCode = (String) tableRet.getValueAt(row, 2);		
			cusCtr.updateCustomer(name, phone, email, address, zipCode);
		}
	}

	private void deleteCustomer()
	{
		int row = tableRet.getSelectedRow();
		if(row != -1)
		{
			final JFrame parent = new JFrame();
			String slet = "Slet kunde: " + (String) tableRet.getValueAt(row, 0);
			int valg = JOptionPane.showConfirmDialog(parent, "Er du sikker?", slet, JOptionPane.YES_NO_OPTION);
			if(valg == 0)
			{
				String phone = (String) tableRet.getValueAt(row, 4);
				cusCtr.deleteCustomer(phone);
				sletTabel(modelRet);
			}
		}
	}

	private void checkRegularExp()
	{
		checkEmail();
	}

	private void checkEmail()
	{
		String email = txtEmail.getText();
		String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}{5,50}$";

		Pattern patEmail = Pattern.compile(emailPattern);
		Matcher m = patEmail.matcher(email);

		if(m.find()) // check email
		{
			checkPhoneNo();	
		}
		else
		{
			lblKundeOprettet.setText("Kunde ikke oprettet. Email skal være i formattet: navn@mail.dk");
			lblKundeOprettet.setVisible(true);
		}
	}

	private void checkPhoneNo()
	{
		String phone = txtTelefon.getText();
		String phonePattern = "^[0-9]{1,8}$";

		Pattern patPhone = Pattern.compile(phonePattern);
		Matcher p = patPhone.matcher(phone);

		if(p.find()) // check phone
		{
			checkZipCode();			
		}
		else
		{
			lblKundeOprettet.setText("Kunde ikke oprettet. Telefon nr. skal bestå af tal.");
			lblKundeOprettet.setVisible(true);
		}		
	}

	private void checkZipCode()
	{
		String zipCode = txtPostnr.getText();
		String zipPattern = "^[0-9]{4}$";

		Pattern patZip = Pattern.compile(zipPattern);
		Matcher z = patZip.matcher(zipCode);

		if(z.find())  // check zipcode
		{
			checkAddress();
		}
		else
		{
			lblKundeOprettet.setText("Kunde ikke oprettet. Postnr. skal bestå af præcis 4 tal.");
			lblKundeOprettet.setVisible(true);
		}
	}

	private void checkAddress()
	{
		String address = txtAdresse.getText();
		String adrPattern = "^[a-zA-ZæøåÆØÅ0-9 ]{1,50}$";

		Pattern patAdr = Pattern.compile(adrPattern);
		Matcher a = patAdr.matcher(address);

		if(a.find())  // check address
		{
			checkName();
		}
		else
		{
			lblKundeOprettet.setText("Kunde ikke oprettet. Adressen er for lang.");
			lblKundeOprettet.setVisible(true);
		}
	}

	private void checkName()
	{
		String name = txtNavn.getText();
		String namePattern = "^[a-zA-ZæøåÆØÅ ]{1,50}$";

		Pattern patName = Pattern.compile(namePattern);
		Matcher n = patName.matcher(name);

		if(n.find())  // check name
		{
			insertCustomer();
			clearFieldsOpret();
		}
		else
		{
			lblKundeOprettet.setText("Kunde ikke oprettet. Navn er for lang.");
			lblKundeOprettet.setVisible(true);
		}
	}

	private void insertCustomer()
	{
		String name = txtNavn.getText();
		String phone = txtTelefon.getText();
		String email = txtEmail.getText();
		String address = txtAdresse.getText();
		String zipCode = txtPostnr.getText();						
		try 
		{
			if(cusCtr.insertNewCustomer(name, phone, email, address, zipCode))
			{
				lblKundeOprettet.setText("Kunden blev oprettet.");
				lblKundeOprettet.setVisible(true);
			}
			else
			{
				lblKundeOprettet.setText("Kunden blev ikke oprettet.");
				lblKundeOprettet.setVisible(true);
			}
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}

	private void getCity()
	{
		String zipCode = txtPostnr.getText();
		DBLocation dbLoc = new DBLocation();
		Location loc = dbLoc.findLocation(zipCode);
		String city = loc.getCity();
		lblBy.setText(city);
		lblBy.setVisible(true);
	}

	private void clearFieldsOpret()
	{
		txtNavn.setText("");
		txtAdresse.setText("");
		txtPostnr.setText("");
		txtEmail.setText("");
		txtTelefon.setText("");
		lblBy.setText("");

	}

	private class FillTable
	{
		public SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>()
				{

			@Override			
			protected Boolean doInBackground() throws Exception
			{			
				ArrayList<Customer> list = new ArrayList<Customer>();
				list = cusCtr.findAllCustomers();
				sletTabel(model);
				for(int i = 0; i < list.size(); i++)
				{
					model.addRow(new Object[]{list.get(i).getName(), list.get(i).getAddress(), list.get(i).getZipCode(),
							list.get(i).getLocation().getCity(), list.get(i).getPhone(), list.get(i).getEmail()});
				}

				return false;
			}

				};

	}

	private class FillTableRet
	{
		public SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>()
				{

			@Override			
			protected Boolean doInBackground() throws Exception
			{			
				ArrayList<Customer> list = new ArrayList<Customer>();
				list = cusCtr.findAllCustomers();
				sletTabel(modelRet);
				for(int i = 0; i < list.size(); i++)
				{
					modelRet.addRow(new Object[]{list.get(i).getName(), list.get(i).getAddress(), list.get(i).getZipCode(),
							list.get(i).getLocation().getCity(), list.get(i).getPhone(), list.get(i).getEmail()});
				}

				return false;
			}

				};

	}

	private class CheckOnline 
	{
		public SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>()
				{

			@Override			
			protected Boolean doInBackground() throws Exception
			{			
				for(int i = 0; i <= 50; i++)
				{
					progressBar.setValue(i);
					if(i == 50)
					{
						i = 0;
					}
					Thread.sleep(100);

				}

				//				while(true)
				//				{
				//					DBConnection dbCon = DBConnection.getInstance();
				//					if(dbCon.getDBcon() != null)
				//					{
				//						lblForbindelse.setText("Forbindelse til DB: Oprettet");
				//					}
				//					else
				//					{
				//						lblForbindelse.setText("Forbindelse til DB: Mistet");
				//					}
				//					Thread.sleep(5000);
				//				}
				return false;
			}

				};
	}
}
