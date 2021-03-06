package guilayer;

import ctrlayer.*;
import dblayer.*;
import modellayer.*;

import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JProgressBar;

import org.jgrapht.Graph;

import javax.swing.JLabel;


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
	private JButton btnTilfjDestination;
	private JPanel panelRoute;
	private JScrollPane scrollPaneRoute;
	private DefaultTableModel modelRoute;
	private JTable tableRoute;
	private JButton btnTilbage;
	private static RouteGUI frame;
	private User user;
//	private RouteCtr rCtr;
	private Graph<Customer, Edge> g;
	private DBEdge dbe;

	private JButton btnNyRute;

	private JLabel lblTotalDistance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RouteGUI();
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
	private RouteGUI() {
		if(user == null)
		{
			user = LoginMenu.getInstance().getUser();
		}
		dbe = new DBEdge();
		cCtr = new CustomerCtr();
		g = RouteCtr.createGraph(cCtr.findAllCustomers(), dbe.findAllEdges());
//		rCtr = new RouteCtr();
		initComponents();
		findAllCustomers(model);
	}
	
	public static RouteGUI getInstance()
	{
		if(frame == null)
		{
			frame = new RouteGUI();
		}
		return frame;
	}
	

	public void initComponents()
	{
		setTitle("Rute menu - logget ind som " + user.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();		
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Start panel
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(166, 11, 836, 612);
		contentPane.add(panel);
		panel.setLayout(null);	

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 816, 178);
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
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
						
		scrollPaneAdded = new JScrollPane();
		scrollPaneAdded.setBounds(10, 234, 816, 178);
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
		tableAdded.getColumnModel().getColumn(2).setPreferredWidth(10);
		tableAdded.getColumnModel().getColumn(3).setPreferredWidth(10);
		tableAdded.getColumnModel().getColumn(4).setPreferredWidth(10);
		tableAdded.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		
		btnTilfjDestination = new JButton("Tilføj destination");
		btnTilfjDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomerToModelAdded();
				deleteCustomerFromModel();
				
			}
		});
		btnTilfjDestination.setBounds(20, 200, 145, 23);
		panel.add(btnTilfjDestination);
		
		btnFjernDestination = new JButton("Fjern destination");
		btnFjernDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeDestFromModelAdded();
			}
		});
		btnFjernDestination.setBounds(175, 200, 145, 23);
		panel.add(btnFjernDestination);
		
		btnOpretRute = new JButton("Opret rute");
		btnOpretRute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateRoute cr = new CreateRoute();
				cr.worker.execute();
				changePanel();
			}
		});
		btnOpretRute.setBounds(20, 423, 145, 23);
		panel.add(btnOpretRute);
		//Slut panel

		//Start route panel
		panelRoute = new JPanel();
		panelRoute.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelRoute.setBounds(166, 11, 836, 612);
		contentPane.add(panelRoute);
		panelRoute.setLayout(null);	

		scrollPaneRoute = new JScrollPane();
		scrollPaneRoute.setBounds(10, 11, 816, 199);
		panelRoute.add(scrollPaneRoute);
		
		modelRoute = new DefaultTableModel(new Object[][] {},new String[] {"Nr", "Navn", "Adresse", "Postnr", "By", "Telefon", "Email"});
		tableRoute = new JTable(modelRoute)
		{
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		scrollPaneRoute.setViewportView(tableRoute);
		
		btnNyRute = new JButton("Ny rute");
		btnNyRute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sletTabel(modelRoute);
				changeBackPanel();
			}
		});
		btnNyRute.setBounds(10, 221, 89, 23);
		panelRoute.add(btnNyRute);
		
		lblTotalDistance = new JLabel();
		lblTotalDistance.setBounds(109, 225, 717, 14);
		panelRoute.add(lblTotalDistance);
		
		btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainMenuGUI.main(null);
			}
		});
		btnTilbage.setBounds(10, 11, 146, 50);
		contentPane.add(btnTilbage);
		
		tableRoute.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableRoute.getColumnModel().getColumn(3).setPreferredWidth(10);
		tableRoute.getColumnModel().getColumn(5).setPreferredWidth(10);
		
		// centers columns "Nr" and "Postnr"
		tableRoute.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableRoute.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		//Slut route panel
		












		
		panelRoute.setVisible(false);
	}
	
	private void changePanel()
	{
		panel.setVisible(false);
		panelRoute.setVisible(true);
	}
	private void changeBackPanel()
	{
		panelRoute.setVisible(false);
		panel.setVisible(true);		
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
		if(row >= 0)
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
		if(row>=0)
		{
		model.removeRow(row);
		}
	}
	
	private void removeDestFromModelAdded()
	{
		int row = tableAdded.getSelectedRow();
		if(row >= 0)
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
	
	private void addToPanelRoute(ArrayList<Customer> cusList)
	{
		for(int i = 0; i < cusList.size(); i++)
		{
			modelRoute.addRow(new Object[]{Integer.toString(i+1), cusList.get(i).getName(), cusList.get(i).getAddress(), cusList.get(i).getZipCode(),
					cusList.get(i).getLocation().getCity(), cusList.get(i).getPhone(), cusList.get(i).getEmail()});
		}
	}
	
	private class CreateRoute
	{
		public SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>()
				{

			@Override			
			protected Boolean doInBackground() throws Exception
			{			
				DecimalFormat df = new DecimalFormat("#.00");
				ArrayList<Customer> list = createListOfCustomers();
				ArrayList<Customer> route = new ArrayList<Customer>();
				double distance = 0;
				route.add((Customer) list.get(0));
				
				for(int i = 0; i < list.size()-1; i++)
				{
					Route r = RouteCtr.createRoute(user, g, list.get(i), list.get(i+1));
					distance += r.getRouteLength();
					List<Edge> e = r.getEdges();
					for(int j = 0; j < e.size(); j++)
					{
						if(!route.get(route.size()-1).equals(e.get(j).getSource()))
						{
							route.add((Customer) e.get(j).getSource());
						}
						else if(!route.get(route.size()-1).equals(e.get(j).getTarget()))
						{
							route.add((Customer) e.get(j).getTarget());
						}			
					}
				}
				lblTotalDistance.setText("Rutens længde: " + df.format(distance) + " meter");
				addToPanelRoute(route);

				return false;
			}

				};

	}
}
