package guilayer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modellayer.*;

public class MainMenuGUI extends JFrame {

	private JPanel contentPane;
	private CustomerGUI  cGUI;
	private UserGUI uGUI;
	private RouteGUI rGUI;
	private LoginMenu lMenu;
	private static MainMenuGUI frame;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainMenuGUI();
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
	private MainMenuGUI() {
		if(user == null)
		{
			user = LoginMenu.getInstance().getUser();
		}
		initComponents();

	}	
	
	public static MainMenuGUI getInstance()
	{
		if(frame == null)
		{
			frame = new MainMenuGUI();
		}
		return frame;
	}
	
	private void initComponents()
	{
		setTitle("Main menu - logget ind som " + user.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBruger = new JButton("Bruger");
		btnBruger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				uGUI.main(null);
			}
		});
		btnBruger.setBounds(51, 66, 146, 75);
		contentPane.add(btnBruger);

		JButton btnKunder = new JButton("Kunder");
		btnKunder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				cGUI.main(null);					
			}
		});
		btnKunder.setBounds(240, 66, 146, 75);
		contentPane.add(btnKunder);

		JButton btnRute = new JButton("Rute");
		btnRute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				rGUI.main(null);
			}
		});
		btnRute.setBounds(429, 66, 146, 75);
		contentPane.add(btnRute);

		JButton btnLogUd = new JButton("Log ud");
		btnLogUd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lMenu.main(null);
				dispose();
			}
		});
		btnLogUd.setBounds(51, 176, 146, 75);
		contentPane.add(btnLogUd);
	}
}