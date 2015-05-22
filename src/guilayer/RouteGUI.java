package guilayer;

import ctrlayer.*;
import dblayer.*;
import modellayer.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RouteGUI extends JFrame {
	private CustomerCtr cCtr;

	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPaneAdded;
	private DefaultTableModel modelAdded;

	private JTable tableAdded;
	private JButton btnFjernDestination;
	private JButton btnOpretRute;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteGUI frame = new RouteGUI();
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
	public RouteGUI() {
		cCtr = new CustomerCtr();
		initComponents();
	}

	public void initComponents()
	{
		setTitle("RouteGUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(166, 11, 836, 600);
		contentPane.add(panel);
		panel.setLayout(null);	

		scrollPane = new JScrollPane();
		scrollPane.setBounds(244, 11, 748, 178);
		panel.add(scrollPane);

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
		
		findAllCustomers(model);
		
		scrollPaneAdded = new JScrollPane();
		scrollPaneAdded.setBounds(244, 234, 748, 178);
		panel.add(scrollPaneAdded);

		modelAdded = new DefaultTableModel(new Object[][] {},new String[] {"Navn", "Adresse", "Postnr", "By", "Telefon", "Email"});
		tableAdded = new JTable(modelAdded)
		{
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		scrollPaneAdded.setViewportView(tableAdded);
		
		JButton btnTilfjDestination = new JButton("Tilføj destination");
		btnTilfjDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomerToModelAdded();
				deleteCustomerFromModel();
				
			}
		});
		btnTilfjDestination.setBounds(254, 200, 145, 23);
		panel.add(btnTilfjDestination);
		
		btnFjernDestination = new JButton("Fjern destination");
		btnFjernDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeDestFromModelAdded();
			}
		});
		btnFjernDestination.setBounds(409, 200, 145, 23);
		panel.add(btnFjernDestination);
		
		btnOpretRute = new JButton("Opret rute");
		btnOpretRute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createListOfCustomers();
			}
		});
		btnOpretRute.setBounds(254, 423, 145, 23);
		panel.add(btnOpretRute);



















	}

	private void findAllCustomers(DefaultTableModel dmodel)
	{
		sletTabel(dmodel);
		ArrayList<Customer> list = new ArrayList<Customer>();
		list = cCtr.findAllCustomers();
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
	
	private void addCustomerToModelAdded()
	{
		int row = table.getSelectedRow();
		if(row > 0)
		{
		String phone = (String) table.getValueAt(row, 4);
		Customer cus = cCtr.findByPhoneNo(phone);
		modelAdded.addRow(new Object[]{cus.getName(), cus.getAddress(), cus.getZipCode(),
				cus.getLocation().getCity(), cus.getPhone(), cus.getEmail()});
		}
	}
	
	private void deleteCustomerFromModel()
	{
		int row = table.getSelectedRow();
		model.removeRow(row);
	}
	
	private void removeDestFromModelAdded()
	{
		int row = tableAdded.getSelectedRow();
		if(row > 0)
		{
		String phone = (String) tableAdded.getValueAt(row, 4);
		Customer cus = cCtr.findByPhoneNo(phone);		
		modelAdded.removeRow(row);
		model.addRow(new Object[]{cus.getName(), cus.getAddress(), cus.getZipCode(),
				cus.getLocation().getCity(), cus.getPhone(), cus.getEmail()});
		}
	}
	
	private ArrayList<Customer> createListOfCustomers()
	{
		ArrayList<Customer> cusList = new ArrayList<Customer>();
		int rowCount = modelAdded.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) 
		{
			String phone = (String) tableAdded.getValueAt(0, 4);
			Customer cus = cCtr.findByPhoneNo(phone);
			cusList.add(cus);
			modelAdded.removeRow(0);
		}	
		for(Customer cus : cusList)
		{
			System.out.println(cus.getName());
		}
		findAllCustomers(model);
		return cusList;
	}
	
	
	
	
	
}
