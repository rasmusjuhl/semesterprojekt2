package guilayer;
import ctrlayer.*;
import modellayer.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;

public class UserGUI extends JFrame {

	private JPanel contentPane;
	private UserCtr userCtr;
	private JTextField txtNavn;
	private JTextField txtTelefon;
	private JTextField txtEmail;
	private JPanel panelFind;
	private JButton btnOpretBruger;
	private JButton btnFindBruger;
	private JLabel lblNavn;
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
	private JButton btnRetBruger;
	private DefaultTableModel modelRet;
	private JTable tableRet;
	private JButton btnRet;
	private JPanel panelOpret;
	private JScrollPane scrollPane;
	private JButton btnSlet;
	private JPasswordField passwordField;
	private static UserGUI frame;
	private JButton btnTilbage;
	private User user;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					frame = new UserGUI();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private UserGUI() 
	{
		if(user == null)
		{
			user = LoginMenu.getInstance().getUser();
		}
		userCtr = new UserCtr();
		initComponents();
	}
	
	public static UserGUI getInstance()
	{
		if(frame == null)
		{
			frame = new UserGUI();
		}
		return frame;
	}
	
	public void initComponents()
	{
		setTitle("Bruger menu - logget ind som " + user.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnOpretBruger = new JButton("Opret bruger");
		btnOpretBruger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelOpretBruger();
			}
		});
		btnOpretBruger.setBounds(10, 11, 146, 50);
		contentPane.add(btnOpretBruger);
		
		btnFindBruger = new JButton("Find bruger");
		btnFindBruger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelFindBruger();
			}
		});
		btnFindBruger.setBounds(10, 72, 146, 50);
		contentPane.add(btnFindBruger);	
		
		btnRetBruger = new JButton("Ret/slet bruger");
		btnRetBruger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRetBruger();
				sletTabel(modelRet);
				FillTableRet ftr = new FillTableRet();
				ftr.worker.execute();
			}
		});
		btnRetBruger.setBounds(10, 133, 146, 50);
		contentPane.add(btnRetBruger);
		//END Opret kunde panel
		
		//START Ret kunde panel
		panelRet = new JPanel();
		panelRet.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelRet.setBounds(166, 11, 836, 600);
		contentPane.add(panelRet);
		panelRet.setLayout(null);	
		
		scrollPaneRet = new JScrollPane();
		scrollPaneRet.setBounds(10, 11, 816, 319);
		panelRet.add(scrollPaneRet);
		
		modelRet = new DefaultTableModel(new Object[][] {},new String[] {"Navn", "Telefon", "Email"});
		tableRet = new JTable(modelRet)
		{
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		scrollPaneRet.setViewportView(tableRet);
		
		
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
				findUser(model);
			}
		});
		btnFind.setBounds(10, 36, 100, 23);
		panelFind.add(btnFind);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(244, 11, 582, 311);
		panelFind.add(scrollPane);
		
		model = new DefaultTableModel(new Object[][] {},new String[] {"Navn", "Telefon", "Email"});
		table = new JTable(model)
		{
			Class[] columnTypes = new Class[] 
			{
					String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) 
			{
				return columnTypes[columnIndex];
			}
		};
		scrollPane.setViewportView(table);
		
		JButton btnFindAlle = new JButton("Find alle");
		btnFindAlle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sletTabel(model);
				FillTableAllUsers ftau = new FillTableAllUsers();
				ftau.worker.execute();
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
//		scrollPaneRet.setRowHeaderView(panelOpret);
		
		panelOpret.setLayout(null);		
		
		lblNavn = new JLabel("Navn");
		lblNavn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNavn.setBounds(10, 16, 77, 14);
		panelOpret.add(lblNavn);
		
		lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefon.setBounds(10, 41, 77, 14);
		panelOpret.add(lblTelefon);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(10, 66, 77, 14);
		panelOpret.add(lblEmail);
		
		txtNavn = new JTextField();
		txtNavn.setBounds(97, 13, 134, 20);
		panelOpret.add(txtNavn);
		txtNavn.setColumns(10);
		
		txtTelefon = new JTextField();
		txtTelefon.setColumns(10);
		txtTelefon.setBounds(97, 39, 134, 20);
		panelOpret.add(txtTelefon);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(97, 64, 134, 20);
		panelOpret.add(txtEmail);
		
		btnOpret = new JButton("Opret");
		btnOpret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertUser();
			}
		});
		btnOpret.setBounds(10, 136, 89, 23);
		panelOpret.add(btnOpret);		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(10, 97, 77, 14);
		panelOpret.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(97, 89, 134, 22);
		panelOpret.add(passwordField);
		panelOpret.setVisible(false);
		
		btnRet = new JButton("Ret");
		btnRet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUser();
			}
		});
		btnRet.setBounds(10, 341, 89, 23);
		panelRet.add(btnRet);
		
		btnSlet = new JButton("Slet");
		btnSlet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUser();
			}
		});
		btnSlet.setBounds(109, 341, 89, 23);
		panelRet.add(btnSlet);
		
		btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainMenuGUI.main(null);	
			}
		});
		btnTilbage.setBounds(10, 194, 146, 50);
		contentPane.add(btnTilbage);
		//END Ret kunde panel	
		
		panelRet.setVisible(false);
		panelFind.setVisible(false);
		panelOpret.setVisible(false);
	}
	
	public void panelOpretBruger()
	{	
		panelRet.setVisible(false);
		panelFind.setVisible(false);
		panelOpret.setVisible(true);
		
	}
	
	public void panelFindBruger()
	{
		panelRet.setVisible(false);
		panelOpret.setVisible(false);
		panelFind.setVisible(true);
	}
	
	private void panelRetBruger()
	{
		panelOpret.setVisible(false);
		panelFind.setVisible(false);
		panelRet.setVisible(true);
	}
	
	private void insertUser()
	{
		String name = txtNavn.getText();
		String phone = txtTelefon.getText();
		String email = txtEmail.getText();
		String password = passwordField.getText();						
		try 
		{
			userCtr.insertNewUser(name, phone, email, password);
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}
	
	public void findUser(DefaultTableModel dmodel)
	{
		sletTabel(dmodel);
		String phone = txtFindTelefon.getText();
		User user = userCtr.findUser(phone);
		
		dmodel.addRow(new Object[]{user.getName(), user.getPhone(), user.getEmail()});
	}
	
	public void findAllUsers(DefaultTableModel dmodel)
	{
		sletTabel(dmodel);
		ArrayList<User> list = new ArrayList<User>();
		list = userCtr.findAlleUsers();
		for(int i = 0; i < list.size(); i++)
		{
			dmodel.addRow(new Object[]{list.get(i).getName(), list.get(i).getPhone(), list.get(i).getEmail()});
		}
		
	}
	
	private void sletTabel(DefaultTableModel dmodel)
	{
		int rowCount = dmodel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) 
		{
			dmodel.removeRow(i);
		}	
	}
	
	private void updateUser()
	{
		int row = tableRet.getSelectedRow();
		String name = (String) tableRet.getValueAt(row, 0);
		String phone = (String) tableRet.getValueAt(row, 1);
		String email  = (String) tableRet.getValueAt(row, 2);
		userCtr.updateUser(name, phone, email);
	}
	
	private void deleteUser()
	{
		final JFrame parent = new JFrame();
		int row = tableRet.getSelectedRow();
		String slet = "Slet kunde: " + (String) tableRet.getValueAt(row, 0);
		int valg = JOptionPane.showConfirmDialog(parent, "Er du sikker?", slet, JOptionPane.YES_NO_OPTION);
		if(valg == 0)
		{
			String phone = (String) tableRet.getValueAt(row, 4);
			userCtr.deleteUser(phone);
			sletTabel(modelRet);
		}
	}
	
	private class FillTableAllUsers
	{
		public SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>()
				{

			@Override			
			protected Boolean doInBackground() throws Exception
			{			
				ArrayList<User> list = new ArrayList<User>();
				list = userCtr.findAlleUsers();
				for(int i = 0; i < list.size(); i++)
				{
					model.addRow(new Object[]{list.get(i).getName(), list.get(i).getPhone(), list.get(i).getEmail()});
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
				ArrayList<User> list = new ArrayList<User>();
				list = userCtr.findAlleUsers();
				for(int i = 0; i < list.size(); i++)
				{
					modelRet.addRow(new Object[]{list.get(i).getName(), list.get(i).getPhone(), list.get(i).getEmail()});
				}
				return false;
			}

				};
		
	}
}
