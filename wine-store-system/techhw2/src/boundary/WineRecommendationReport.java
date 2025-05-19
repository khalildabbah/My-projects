package boundary;

import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import control.DatabaseController;
import control.JasperReportRunner;
import entity.FoodPairing;
import entity.Occasion;
import entity.WineBottle;
import entity.WineType;

public class WineRecommendationReport extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<FoodPairing> foodComboBox;
	private JComboBox<Occasion> occasionComboBox;
	private JComboBox<WineType> wineTypeComboBox;
	private JButton generateReportButton;
	private JList<WineBottle> wineList;
	private DefaultListModel<WineBottle> listModel_1;
	private JList<FoodPairing> foodList;
	private DefaultListModel<FoodPairing> listModel_2;
	private JList<Occasion> occasionList;
	private DefaultListModel<Occasion> listModel_3;
	private JLabel statusLabel;

	public WineRecommendationReport() {
	    setBorder(new EmptyBorder(5, 5, 5, 5));
	    setBackground(SystemColor.activeCaption);
	    setLayout(null);

	    JLabel lblTitle = new JLabel("Wine Recommendation Report");
	    lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblTitle.setBounds(10, 11, 400, 30);
	    add(lblTitle);

	    JLabel lblFood = new JLabel("Food");
	    lblFood.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblFood.setBounds(10, 60, 120, 20);
	    add(lblFood);

	    foodComboBox = new JComboBox<>();
	    foodComboBox.setBounds(10, 91, 146, 20);
	    HashMap<Integer,FoodPairing> foodData = DatabaseController.getAllFoodPairings();
	    for(FoodPairing fp : foodData.values())
	    	foodComboBox.addItem(fp);
	    add(foodComboBox);

	    JLabel lblOccasion = new JLabel("Occasion");
	    lblOccasion.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblOccasion.setBounds(229, 60, 120, 20);
	    add(lblOccasion);

	    occasionComboBox = new JComboBox<>();
	    occasionComboBox.setBounds(229, 91, 151, 20);
	    HashMap<Integer,Occasion> occasionData = DatabaseController.getAllOccasions();
	    for(Occasion o : occasionData.values())
	    	occasionComboBox.addItem(o);
	    add(occasionComboBox);

	    JLabel lblWineType = new JLabel("Wine Type");
	    lblWineType.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblWineType.setBounds(439, 60, 120, 20);
	    add(lblWineType);

	    wineTypeComboBox = new JComboBox<>();
	    wineTypeComboBox.setBounds(439, 91, 146, 20);
	    HashMap<String,WineType> winetypeData = DatabaseController.getAllWineTypes();
	    for(WineType wt : winetypeData.values()) {
	    	wineTypeComboBox.addItem(wt);
	    }
	    wineTypeComboBox.addActionListener(e -> getDetails());
	    add(wineTypeComboBox);

	    generateReportButton = new CustomButton("Generate Report", 150, 50, 40);
	    generateReportButton.setBounds(229, 443, 146, 42);
	    generateReportButton.addActionListener(e -> generateReport());
	    add(generateReportButton);

	    
	    listModel_3 = new DefaultListModel<>();
	    occasionList = new JList<>();
	    occasionList.setBounds(229, 122, 151, 47);
	    occasionList.setModel(listModel_3);
	    JScrollPane scrollPane_2 = new JScrollPane(occasionList);
	    scrollPane_2.setBounds(229, 122, 151, 47);
	    add(scrollPane_2);
	    
	    listModel_2 = new DefaultListModel<>();
	    foodList = new JList<>();
	    foodList.setBounds(10, 50, 150, 100);
	    foodList.setModel(listModel_2);
	    JScrollPane scrollPane_1 = new JScrollPane(foodList);
	    scrollPane_1.setBounds(10, 122, 146, 47);
	    add(scrollPane_1);
	    
	    listModel_1 = new DefaultListModel<>();
	    HashMap<String,WineBottle>wineData = DatabaseController.getAllWineBottles();
	    for(WineBottle wb : wineData.values())
	    	listModel_1.addElement(wb);
	    
	    wineList = new JList<>();
	    wineList.setBounds(10, 220, 580, 200);
	    wineList.setModel(listModel_1);
	    JScrollPane scrollPane = new JScrollPane(wineList);
	    scrollPane.setBounds(10, 220, 580, 200);
	    add(scrollPane);
	    
	    JLabel lblAvailableWineBotttles = new JLabel("Available Wine Bottles");
	    lblAvailableWineBotttles.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblAvailableWineBotttles.setBounds(10, 189, 343, 20);
	    add(lblAvailableWineBotttles);
	    
	    JButton addFoodBtn = new CustomButton("+",150,50,40);
	    addFoodBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    addFoodBtn.setBounds(166, 90, 34, 31);
	    addFoodBtn.addActionListener(e -> addFoodPreference());
	    add(addFoodBtn);
	    
	    CustomButton removeFoodBtn = new CustomButton("-", 150, 50, 40);
	    removeFoodBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    removeFoodBtn.setBounds(166, 132, 34, 30);
	    removeFoodBtn.addActionListener(e -> removeFoodPreference());
	    add(removeFoodBtn);
	    
	    CustomButton addOccasionBtn = new CustomButton("+", 150, 50, 40);
	    addOccasionBtn.setText("+");
	    addOccasionBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    addOccasionBtn.setBounds(390, 91, 34, 31);
	    addOccasionBtn.addActionListener(e -> addOccasionPreference());
	    add(addOccasionBtn);
	    
	    CustomButton removeOccasionBtn = new CustomButton("-", 150, 50, 40);
	    removeOccasionBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    removeOccasionBtn.setBounds(390, 133, 34, 30);
	    removeOccasionBtn.addActionListener(e -> removeOccasionPreference());
	    add(removeOccasionBtn);
	    
        // Reset Filters Button
        JButton resetFiltersButton = new JButton("Reset Filters");
        resetFiltersButton.setBounds(229, 500, 146, 25);
        resetFiltersButton.addActionListener(e -> resetFilters());
        add(resetFiltersButton);

        // Status Label
        statusLabel = new JLabel("");
        statusLabel.setBounds(10, 430, 400, 20);
        add(statusLabel);
        
	    CustomButton btnNewButton = new CustomButton("Reset Preferences", 150, 50, 40);
        btnNewButton.setBounds(439, 138, 151, 23);
        btnNewButton.addActionListener(e -> resetAll());
        add(btnNewButton);
        
	    getDetails();


	}

    private void resetFilters() {
        listModel_2.clear(); // Clear food preferences
        listModel_3.clear(); // Clear occasion preferences
        wineTypeComboBox.setSelectedIndex(-1); // Reset wine type selection
        statusLabel.setText(""); // Clear status
        listModel_1.clear(); // Clear the wine list
    }
    
	private void addFoodPreference() {
	
		FoodPairing fp = (FoodPairing)foodComboBox.getSelectedItem();
		if(fp!=null) {
			if(listModel_2.contains(fp) == false)
				listModel_2.addElement(fp);
			foodComboBox.removeItem(fp);
		}
		getDetails();
	}
	
	private void removeFoodPreference() {
		
		FoodPairing fp = foodList.getSelectedValue();
		if(fp!=null) {
			if(listModel_2.contains(fp))
				listModel_2.removeElement(fp);
			foodComboBox.addItem(fp);
		}
		foodList.setSelectedIndex(0);
		getDetails();
	}
	
	private void addOccasionPreference() {
		
		Occasion o = (Occasion)occasionComboBox.getSelectedItem();
		if(o!=null) {
			if(listModel_3.contains(o) == false)
				listModel_3.addElement(o);
			occasionComboBox.removeItem(o);
		}
		
		getDetails();
	}
	
	private void removeOccasionPreference() {
		
		Occasion o = occasionList.getSelectedValue();
		if(o!=null) {
			if(listModel_3.contains(o))
				listModel_3.removeElement(o);
			occasionComboBox.addItem(o);
		}
		occasionList.setSelectedIndex(0);
		
		getDetails();
	}
	
	private void getDetails() {
		
		ArrayList<FoodPairing> foods = new ArrayList<>();
		for(int i=0; i<listModel_2.size();i++) {
			FoodPairing f = listModel_2.getElementAt(i);
			foods.add(f);
		}
			
		ArrayList<Occasion> occasions = new ArrayList<>();
		for(int i=0; i<listModel_3.size();i++) {
			Occasion o = listModel_3.getElementAt(i);
			occasions.add(o);
		}
		
		HashMap<String,WineBottle> wineData = DatabaseController.getAllWineBottles();
		listModel_1.clear(); 
		
		boolean foundMatchingWine = false;
		
		for(WineBottle wb : wineData.values()) {
			boolean addBottle = true;
			
			WineType type = (WineType)wineTypeComboBox.getSelectedItem();
			if(wb.getType()==null) 
				continue;
			String typeName = type.getWineTypeSerial();
			if(wb.getType().getWineTypeSerial().equals(typeName)==false)
				addBottle = false;
			
			for(Occasion o : occasions) {
				if(o.getType().equals(wb.getType().getWineTypeSerial()) == false) {
					addBottle = false;
					break;
				}
			}
			
			for(FoodPairing fp : foods) {

				if(fp.getType().equals(wb.getType().getWineTypeSerial()) == false) {
					addBottle = false;
					break;
				}
			}
			
	        if (addBottle) {
	            listModel_1.addElement(wb);
	            foundMatchingWine = true;
	        }
		}
		
	    if (!foundMatchingWine) {
	        statusLabel.setText("No matching wines found. Please adjust the filter criteria.");
	    } else {
	        statusLabel.setText(listModel_1.size() + " wines found matching the filters.");
	    }
		
	}
	
	private void resetAll() {
		listModel_1.removeAllElements();
		listModel_2.removeAllElements();
		listModel_3.removeAllElements();
		wineTypeComboBox.setSelectedIndex(0);
	}
	
	private void generateReport() {
		DatabaseController.emptyRecReport();

		for(int i=0;i<listModel_1.size();i++) {
			WineBottle bottle= listModel_1.get(i);
			if(bottle != null)
				DatabaseController.updateRecReport(bottle.getWineSerialNum());
		}
		JasperReportRunner.initiate("recReport.jasper");
		
	}
}
