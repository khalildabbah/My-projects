package boundary;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import utils.BackgroundPanel;

import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;


public class SalesFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenu manageFunctionsMenu;
	private JMenu addFunctionsMenu;
	private JMenu ReportsMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SalesFrame frame = new SalesFrame();
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
	public SalesFrame() {
		//setResizable(false);
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome To Cheers System");
		lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 398, 325, 48);
		getContentPane().add(lblNewLabel);
		
        BackgroundPanel backgroundPanel = new BackgroundPanel("homepage.jpg");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Welcome to Cheers – The Art of Wine, Perfected.");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Rockwell Nova Cond Light", Font.BOLD, 20));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(81, 449, 449, 49);
        backgroundPanel.add(lblNewLabel_1);
        
		setTitle("Cheers System");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(250, 10, 623, 550);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Console");
		mnNewMenu.setFont(new Font("Dialog", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem2 = new JMenuItem("MainMenu");
		mntmNewMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin AD = new Admin();
				AD.setVisible(true);
				dispose();
				
			}
		});
		mntmNewMenuItem2.setFont(new Font("Dialog", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Close program");
		mntmNewMenuItem.setFont(new Font("Dialog", Font.PLAIN, 16));
		mntmNewMenuItem.addActionListener(e -> dispose());
		mnNewMenu.add(mntmNewMenuItem);

		addFunctionsMenu = new JMenu("View");
		addFunctionsMenu.setFont(new Font("Dialog", Font.PLAIN, 16));
		menuBar.add(addFunctionsMenu);
		
		JMenuItem xml = new JMenuItem("View Wine Bottles");
		xml.setFont(new Font("Dialog", Font.PLAIN, 16));
		xml.addActionListener(e -> openPanel(xml.getText()));
		addFunctionsMenu.add(xml);
	
		
		JMenuItem xml2 = new JMenuItem("View Manufacturers");
		xml2.setFont(new Font("Dialog", Font.PLAIN, 16));
		xml.addActionListener(e -> openPanel(xml2.getText()));
		addFunctionsMenu.add(xml2);

		manageFunctionsMenu = new JMenu("Manage");
		manageFunctionsMenu.setFont(new Font("Dialog", Font.PLAIN, 16));
		menuBar.add(manageFunctionsMenu);
		
		JMenuItem mntmNewMenuItem_90 = new JMenuItem("Manage Orders");
		mntmNewMenuItem_90.setFont(new Font("Dialog", Font.PLAIN, 16));
		mntmNewMenuItem_90.addActionListener(e -> openPanel(mntmNewMenuItem_90.getText()));
		manageFunctionsMenu.add(mntmNewMenuItem_90);
		
		JMenuItem mntmNewMenuItem_50 = new JMenuItem("Manage Customers");
		mntmNewMenuItem_50.addActionListener(e -> openPanel("Manage Customers"));
		mntmNewMenuItem_50.setFont(new Font("Dialog", Font.PLAIN, 16));
		manageFunctionsMenu.add(mntmNewMenuItem_50);
		
		JMenuItem mntmNewMenuItem100 = new JMenuItem("Logout");
		mntmNewMenuItem100.setFont(new Font("Dialog", Font.PLAIN, 16));
		mntmNewMenuItem100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);
				dispose();
				
			}
		});		
		mnNewMenu.add(mntmNewMenuItem100);
		
		ReportsMenu = new JMenu("Reports");
		ReportsMenu.setFont(new Font("Dialog", Font.PLAIN, 16));
		menuBar.add(ReportsMenu);
		
		JMenuItem mntmNewMenuItem_45 = new JMenuItem("Wine Recommendation Report");
		mntmNewMenuItem_45.setFont(new Font("Dialog", Font.PLAIN, 16));
		mntmNewMenuItem_45.addActionListener(e -> openPanel(mntmNewMenuItem_45.getText()));
		ReportsMenu.add(mntmNewMenuItem_45);
		
		JMenuItem mntmNewMenuItem_47 = new JMenuItem("Unproductive Employees Report");
		mntmNewMenuItem_47.addActionListener(e -> openPanel("Unproductive Employees Report"));
		mntmNewMenuItem_47.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		ReportsMenu.add(mntmNewMenuItem_47);
		
	}

	public void openPanel(String panelName) {
		getContentPane().setVisible(false);
	    	    JPanel p = null;

	    switch(panelName) {
	    
	        case   "Manage Customers":
                p = new ManageCustomers();
                getContentPane().removeAll(); 
                getContentPane().add(p); 
                revalidate();
                repaint();
                setBounds(250, 10, 680, 520);
                break;
	    
	        case "Current Inventory Report":
	            p = new InventoryReport();
	            getContentPane().removeAll(); 
	            getContentPane().add(p); 
	            revalidate();
	            repaint();
	            setBounds(250, 10, 700, 600);
	            break;
	    
	        case "Unproductive Employees Report":
	            p = new UnproductiveReport();
	            getContentPane().removeAll(); 
	            getContentPane().add(p); 
	            revalidate();
	            repaint();
	            setBounds(250, 10, 740, 600);
	            break;
	        
	            
	        case "Manage Inventory":
	            p = new ManageInventory();
	            getContentPane().removeAll(); 
	            getContentPane().add(p); 
	            revalidate();
	            repaint();
	            setBounds(170, 5, 700, 650);
	            break;

	        case "Add Manufacturer":
	            p = new AddManufacturer();
	    		setBounds(250, 10, 623, 550);
	            break;

	        case "Add Wine Bottle":
	            p = new AddWineBottle();
	    		setBounds(250, 10, 623, 550);
	            break;

	        case "View Manufacturers":
	            p = new ViewManufacturers();
	    		setBounds(250, 10, 780, 690);

	            break;

	        case "View Wine Bottles":
	            p = new ViewWineBottles();
	    		setBounds(250, 10, 635, 470);
	            break;
	            
	        case "Wine Recommendation Report":
	            p = new WineRecommendationReport();
	    		setBounds(250, 10, 623, 600);
	            break;
	            

	        case "Manage Employees":
	            p = new ManageEmployees();
	    		setBounds(250, 10, 820, 620);
	            break;	
	            
	        case "Manage Orders":
	            p = new ManageOrders();
	    		setBounds(250, 10, 820, 620);
	            break;	
	    }

	    if (p==null) {
			return;
		}
	    setContentPane(p);
	    getContentPane().setVisible(true);
	    repaint();
	    revalidate();
	}
}
