package control;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import entity.Manufacturer;
import entity.WineBottle;
import entity.WineBottleComposite;

public class FileController {

	
	public static void importToAccess(File file) { 
		if(file == null)
			return;
		String filePath = file.getPath();

		//get all access data
		HashMap<Integer,Manufacturer> accessManData = DatabaseController.getInstance().getAllManufacturers();
		HashMap<String,WineBottle> accessWineData = DatabaseController.getInstance().getAllWineBottles();
		HashMap<String,WineBottleComposite> accessCompData = DatabaseController.getInstance().getAllWineComps();
		
		//get all xml data
		ArrayList<Manufacturer>importedMans = XmlController.parseManufacturers(filePath);
		ArrayList<WineBottle> importedWines =  XmlController.parseWines(filePath);
		ArrayList<WineBottleComposite> importedComposite = new ArrayList<>();
		
		ArrayList<Manufacturer>importManLog = new ArrayList<>();
		ArrayList<WineBottle>importWbLog = new ArrayList<>();
		
		// import new manufacturers while ignoring the ones that already exist
		for(Manufacturer man : importedMans) {
			Boolean toBeAdded = true;
			for(Manufacturer accessMan : accessManData.values()) {
				if(accessMan.getAddress().equals(man.getAddress())
						&&accessMan.getEmail().equals(man.getEmail())
						&&accessMan.getName().equals(man.getName())
						&&accessMan.getPhone().equals(man.getPhone())) {
					toBeAdded = false;
					importManLog.add(man);
					break;
				}
			}
			if(toBeAdded)
				DatabaseController.addManufacturer(man);
		}
		
		
		// Generate winebottlecomposite for the new wine bottles
		for(WineBottle wb : importedWines) {
        	if(accessManData.containsKey(wb.getManufacturerID()))
				importedComposite.add(new WineBottleComposite(wb.getWineSerialNum()
																,wb.getManufacturerID()
																,wb.getCatalogNumber()));
		}
		
		
		// import new winecomps while ignoring the ones that already exist
		for(WineBottleComposite comp : importedComposite) {
			if(accessCompData.containsKey(comp.getWineBottleSerial())) 
				continue;
			DatabaseController.addWineBottleComposite(comp);
		}
		
		// import new winebottles while ignoring the ones that already exist
        for(WineBottle wb : importedWines) {
        	String serial = wb.getManufacturerID()+ "-" + wb.getCatalogNumber();
        	if(accessWineData.containsKey(serial)) 
        		continue;
        	if(accessManData.containsKey(wb.getManufacturerID())) 
        		DatabaseController.addWine(wb);
        	else
        		importWbLog.add(wb);
        }
        
        JOptionPane.showMessageDialog(null, 
                "Operation completed successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, 
                "The following Manufacturers were not imported :\n" + importManLog, 
                "Notify", 
                JOptionPane.WARNING_MESSAGE);
        JOptionPane.showMessageDialog(null, 
                "The following Wine Bottles were not imported :\n" + importWbLog, 
                "Notify", 
                JOptionPane.WARNING_MESSAGE);
	}
	
}
