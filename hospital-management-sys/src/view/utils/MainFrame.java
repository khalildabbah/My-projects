package view.utils;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Hospital;
import model.StaffMember;
import utils.SerializedUtils;

public class MainFrame extends JFrame {

	private JPanel mainPanel;
	private CardLayout cardLayout;
	private static MainFrame mainFrame;
	private static boolean admin = true;
	private static StaffMember staff = null;
	private static String profileImage = null;
	
	public static MainFrame getInstance() {
		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}
		return mainFrame;
	}
	
	public static void setStaff(StaffMember mem) {
		staff = mem;
	}
	
	public static StaffMember getStaff() {
		return staff;
	}
	
	public static void setAdmin(boolean _admin) {
		admin = _admin;
	}
	
	public static String getProfileImage() {
		return profileImage;
	}

	public static void setProfileImage(String profileImage) {
		MainFrame.profileImage = profileImage;
	}

	private MainFrame() {
		
		setTitle("Port Hospital");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(900, 600);
		setLocationRelativeTo(null);
		
		cardLayout = new CardLayout();
		
		mainPanel = new JPanel(cardLayout);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		
	}
	
	public void load() {
		try {
			Hospital.setInstance(SerializedUtils.readHospital());
		} catch (Exception e) {
			// Skip if unable to load.
		}
		// adding views
		for (ViewType type: ViewType.values()) {
			mainPanel.add(type.getPanel(), type.name());
		}
	}
	
	public void setView(ViewType type) {
		cardLayout.show(mainPanel, type.name());
		if (type != ViewType.LOGIN) {
			((IView) type.getPanel()).setViewType(admin);
		}
	}

}

