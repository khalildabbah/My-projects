package control;

import entity.Consts;
import entity.Customer;
import entity.Employee;
import entity.FoodPairing;
import entity.Manufacturer;
import entity.Occasion;
import entity.Order;
import entity.OrderItem;
import entity.WineType;
import utils.AccessDatabaseConnection;
import entity.WineBottle;
import entity.WineBottleComposite;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DatabaseController {
	
	 private static DatabaseController _instance;

	    public static DatabaseController getInstance() {
	        if (_instance == null)
	            _instance = new DatabaseController();
	        return _instance;
	    }
    
    public static HashMap<Integer,Manufacturer> getAllManufacturers() {
    	HashMap<Integer,Manufacturer> map = new HashMap<Integer,Manufacturer>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_Manufacturers);
	                    ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    int i = 1;
	                    Manufacturer m = new Manufacturer(rs.getInt(i++),rs.getString(i++), rs.getString(i++), rs.getString(i++),rs.getString(i++));
	                    map.put(m.getManufacturerId(), m);
	                 
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return map;
    }
    
    public static HashMap<String,WineBottle> getAllWineBottles() {
    	HashMap<String,WineBottle> map = new HashMap<String,WineBottle>();

	        
    	try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_Wines);
	                    ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	             
	                    int i = 1;
	                    String wS = rs.getString(i++);
	                    String catNum = getAllWineComps().get(wS).getCatalogNumber();
	                    int manNum = getAllWineComps().get(wS).getManufacturerID();
	                    String name = rs.getString(i++);
	                    String description = rs.getString(i++);
	                    int productionYear = rs.getInt(i++);
	                    Double pricePerBottle = rs.getDouble(i++);
	                    String sweetnessLevel = rs.getString(i++);
	                    String productImage = rs.getString(i++);
	                    String wineTypeS = rs.getString(i++);
	                    WineType wt = getAllWineTypes().get(wineTypeS);
	                    WineBottle m = new WineBottle(wS, catNum,manNum, name, description,productionYear ,pricePerBottle, sweetnessLevel,productImage,wt);
	                    map.put(wS, m);
	                 
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return map;
    }
    
    public static HashMap<String,WineBottleComposite> getAllWineComps() {
    	HashMap<String,WineBottleComposite> map = new HashMap<String,WineBottleComposite>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_Wine_Comp);
	                    ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    int i = 1;
	                    String wineSerial = rs.getString(i++);
	                    int manufacturerId = rs.getInt(i++);
	                    String catalogNum = rs.getString(i++);
	                    map.put(wineSerial,new WineBottleComposite(wineSerial, manufacturerId, catalogNum));
	                    
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return map;
    }
    
    public static HashMap<String,WineType> getAllWineTypes() {
    	HashMap<String,WineType> map = new HashMap<String,WineType>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_Wine_Types);
	                    ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    int i = 1;
	                    WineType m = new WineType(rs.getString(i++),rs.getString(i++), rs.getString(i++));
	                    map.put(m.getWineTypeSerial(),m);
	                      
	                 
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return map;
    }
    
    public static HashMap<Integer,FoodPairing> getAllFoodPairings() {
    	HashMap<Integer,FoodPairing> map = new HashMap<Integer,FoodPairing>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_Food_Pairings);
	                    ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    FoodPairing fp = new FoodPairing(rs.getInt(1), rs.getString(2), rs.getString(3));
	                    map.put(fp.getId(), fp);
	                 
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return map;
    }
    
    public static HashMap<Integer,Occasion> getAllOccasions() {
    	HashMap<Integer,Occasion> map = new HashMap<Integer,Occasion>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_Occasions);
	                    ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                	Occasion o = new Occasion(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
	                    map.put(o.getId(), o);
	                
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return map;
    }
    
    public static HashMap<Integer,Customer> getAllCustomers() {
    	HashMap<Integer,Customer> map = new HashMap<Integer,Customer>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_Customers);
	                    ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    int i = 1;
	                    Customer c = new Customer(rs.getInt(i++),rs.getString(i++), rs.getString(i++), rs.getString(i++),rs.getString(i++), rs.getDate(i++));
	                    map.put(c.getId(), c);	                 
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return map;
    }
    
    public static HashMap<Integer,Employee> getAllEmployees() {
    	HashMap<Integer,Employee> map = new HashMap<Integer,Employee>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_Employees);
	                    ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    int i = 1;
	                    Employee e = new Employee(rs.getInt(i++),rs.getString(i++), rs.getString(i++), rs.getString(i++),rs.getString(i++), rs.getDate(i++),rs.getString(i++));
	                    map.put(e.getId(), e);	                 
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return map;
    }
    public static boolean updateEmployee(Employee emp) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_Upd_Employee)) {
            	
                java.sql.Date sqlDate = new java.sql.Date(emp.getEmployementStartDate().getTime());


                stmt.setString(1, emp.getName());  
                stmt.setString(2, emp.getPhoneNumber());  
                stmt.setString(3, emp.getEmail());
                stmt.setString(4, emp.getOfficeAddress());
                stmt.setDate(5, sqlDate);
                stmt.setString(6, emp.getDepartment()); 
                stmt.setInt(7, emp.getId());  
                stmt.executeUpdate();
                
	  	                return true;
              
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteEmployee(int id) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_Delete_Employee)) {
            	
                stmt.setInt(1, id); 
                stmt.executeUpdate();
	  	                return true;
              
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public static boolean updateWineBottle(WineBottle wine) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_Upd_Wine_Bottle)) {
              

                stmt.setString(1, wine.getName()); // Set isAssigned to 3
                stmt.setString(2, wine.getDescription());
                stmt.setInt(3, wine.getProductionYear());
                stmt.setDouble(4, wine.getPricePerBottle());
                stmt.setString(5, wine.getSweetnessLevel());  
                stmt.setString(6, wine.getProductImage());  
                stmt.setString(7, wine.getWineSerialNum());  


                stmt.executeUpdate();
	  	                return true;
              
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public static boolean updateManufacturer(Manufacturer man) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_Upd_Manufacturer)) {
              

                stmt.setString(1, man.getName()); // Set isAssigned to 3
                stmt.setString(2, man.getPhone());
                stmt.setString(3, man.getAddress());
                stmt.setString(4, man.getEmail());
                stmt.setInt(5, man.getManufacturerId());  
                 


                stmt.executeUpdate();
	  	                return true;
              
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public static boolean deleteWineBottle(String wineSerial) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_Delete_Wine)) {
              

                stmt.setString(1, wineSerial); 
                stmt.executeUpdate();
	  	                return true;
              
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public static boolean deleteManufacturer(int manId) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = AccessDatabaseConnection.getConnection();
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_Delete_Man)) {
              

                stmt.setInt(1, manId); 
                stmt.executeUpdate();
	  	                return true;
              
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
     
     
     public static boolean emptyRecReport() {
         try {
             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
                     CallableStatement stmt = conn.prepareCall(Consts.SQL_Empty_Rec)) {
                 stmt.executeUpdate();
 	  	                return true;
               
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
         return false;
     }
     
     public static boolean updateRecReport(String serial) {
         try {
             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
                     CallableStatement stmt = conn.prepareCall(Consts.SQL_Upd_Rec)) {
               

                 stmt.setString(1, serial); 
                 stmt.executeUpdate();
 	  	                return true;
               
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
         return false;
     }
     
	    public static void addManufacturer(Manufacturer man) {
	        // Define the SQL query
	        final String SQL_Insert_Manufacturer = "INSERT INTO Manufacturer_tbl (FullName, ContactPhoneNumber, Address, Email) VALUES (?, ?, ?, ?)";
	        
	        try (Connection conn = AccessDatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(SQL_Insert_Manufacturer)) {

	            // Set the query parameters
	            stmt.setString(1, man.getName());
	            stmt.setString(2, man.getPhone());
	            stmt.setString(3, man.getAddress());
	            stmt.setString(4, man.getEmail());

	            // Execute the query
	            stmt.executeUpdate();
	            System.out.println("Manufacturer added successfully.");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    static public boolean addWineBottleComposite(WineBottleComposite wineBottleComposite) {
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                    CallableStatement stmt = conn.prepareCall(Consts.SQL_Ins_Wine_Comp)) {
	              
	            			
	                stmt.setString(1, wineBottleComposite.getWineBottleSerial()); 
	                stmt.setInt(2, wineBottleComposite.getManufacturerID()); 
	                stmt.setString(3, wineBottleComposite.getCatalogNumber()); 
	                stmt.execute();
		  	                return true;
	              
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    static public boolean addWine(WineBottle wine) {
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                    CallableStatement stmt = conn.prepareCall(Consts.SQL_Ins_Wine)) {
	            	
	            	String name = wine.getName();
	            	String catalog = wine.getCatalogNumber();
	            	int manId = wine.getManufacturerID();
	            	String wineS = ""+manId+"-"+catalog;
	              
	            			
	                stmt.setString(1, wineS); 
	                stmt.setString(2, name); 
	                stmt.setString(3, wine.getDescription()); 
	                stmt.setInt(4, wine.getProductionYear()); 
	                stmt.setDouble(5, wine.getPricePerBottle());
	                stmt.setString(6, wine.getSweetnessLevel());
	                stmt.setString(7, wine.getProductImage());
	                stmt.setString(8, wine.getType().getWineTypeSerial());


	                stmt.execute();
		  	                return true;
	              
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    public static boolean addEmployee(Employee emp) {
	        // Define the SQL query
	        final String SQL_Insert_Employee = "INSERT INTO Employee_tbl (ID, Name, PhoneNumber, Email, OfficeAddress, EmploymentDate, Department) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        
	        try (Connection conn = AccessDatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(SQL_Insert_Employee)) {

                java.sql.Date sqlDate = new java.sql.Date(emp.getEmployementStartDate().getTime());

	            // Set the query parameters
	            stmt.setInt(1, emp.getId());
	            stmt.setString(2, emp.getName());
	            stmt.setString(3, emp.getPhoneNumber());
	            stmt.setString(4, emp.getEmail());
	            stmt.setString(5, emp.getOfficeAddress());
	            stmt.setDate(6, sqlDate);
	            stmt.setString(7, emp.getDepartment());

	            // Execute the query
	            stmt.executeUpdate();
	            
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    public static HashMap<String, String> getStorageDetails(String storageSerial) {
	        HashMap<String, String> storageDetails = new HashMap<>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();
	                 Statement stmt = conn.createStatement();
	                 ResultSet rs = stmt.executeQuery("SELECT [Storage Serial], ID, Name FROM Storages_tbl WHERE [Storage Serial] = '" + storageSerial + "'")) {

	                if (rs.next()) {
	                    storageDetails.put("Storage Serial", rs.getString("Storage Serial"));
	                    storageDetails.put("ID", rs.getString("ID"));
	                    storageDetails.put("Name", rs.getString("Name"));
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return storageDetails;
	    }

	    public static ArrayList<String> getAllStorageSerials() {
	        ArrayList<String> storageSerials = new ArrayList<>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();
	                 Statement stmt = conn.createStatement();
	                 ResultSet rs = stmt.executeQuery("SELECT [Storage Serial] FROM Storages_tbl ORDER BY ID")) {

	                while (rs.next()) {
	                    storageSerials.add(rs.getString("Storage Serial"));
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return storageSerials;
	    }
 
	    
	    public static ArrayList<HashMap<String, String>> getWinesForStorage(String storageSerial) {
	        ArrayList<HashMap<String, String>> wineList = new ArrayList<>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();
	                 Statement stmt = conn.createStatement();
	                 ResultSet rs = stmt.executeQuery(
	                         "SELECT [WineBottle Serial], Quantity FROM WineStorage_tbl WHERE [Storage Serial] = '" + storageSerial + "'")) {

	                while (rs.next()) {
	                    HashMap<String, String> wine = new HashMap<>();
	                    wine.put("Wine Serial", rs.getString("WineBottle Serial"));
	                    wine.put("Quantity", rs.getString("Quantity"));
	                    wineList.add(wine);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return wineList;
	    }
	    public static ArrayList<String> getAllWineSerials() {
	        ArrayList<String> wineList = new ArrayList<>();
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();
	                 Statement stmt = conn.createStatement();
	                 ResultSet rs = stmt.executeQuery("SELECT DISTINCT [WineBottle Serial] FROM WineStorage_tbl")) {
	                while (rs.next()) {
	                    wineList.add(rs.getString("WineBottle Serial"));
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return wineList;
	    }
	    public static int getWineInventory(String wineSerial, String storageSerial) {
	        int quantity = 0;
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();
	                 Statement stmt = conn.createStatement();
	                 ResultSet rs = stmt.executeQuery("SELECT Quantity FROM WineStorage_tbl WHERE [WineBottle Serial] = '" 
	                                                  + wineSerial + "' AND [Storage Serial] = '" + storageSerial + "'")) {
	                if (rs.next()) {
	                    quantity = rs.getInt("Quantity");
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return quantity;
	    }
	    public static boolean transferWine(String wineSerial, String sourceStorage, String destinationStorage, int quantity) {
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            Connection conn = AccessDatabaseConnection.getConnection();
	            conn.setAutoCommit(false); // Enable transaction management

	            // Reduce quantity from source storage
	            String deductQuery = "UPDATE WineStorage_tbl SET Quantity = Quantity - ? WHERE [WineBottle Serial] = ? AND [Storage Serial] = ?";
	            PreparedStatement deductStmt = conn.prepareStatement(deductQuery);
	            deductStmt.setInt(1, quantity);
	            deductStmt.setString(2, wineSerial);
	            deductStmt.setString(3, sourceStorage);
	            int rowsAffected1 = deductStmt.executeUpdate();

	            // Remove row if quantity reaches zero
	            String deleteQuery = "DELETE FROM WineStorage_tbl WHERE Quantity = 0 AND [WineBottle Serial] = ? AND [Storage Serial] = ?";
	            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
	            deleteStmt.setString(1, wineSerial);
	            deleteStmt.setString(2, sourceStorage);
	            int rowsAffectedDelete = deleteStmt.executeUpdate();

	            // Check if wine exists in destination storage
	            String checkQuery = "SELECT Quantity FROM WineStorage_tbl WHERE [WineBottle Serial] = ? AND [Storage Serial] = ?";
	            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
	            checkStmt.setString(1, wineSerial);
	            checkStmt.setString(2, destinationStorage);
	            ResultSet rs = checkStmt.executeQuery();

	            int rowsAffected2;
	            if (rs.next()) {
	                // If wine already exists in the destination, update the quantity
	                String addQuery = "UPDATE WineStorage_tbl SET Quantity = Quantity + ? WHERE [WineBottle Serial] = ? AND [Storage Serial] = ?";
	                PreparedStatement addStmt = conn.prepareStatement(addQuery);
	                addStmt.setInt(1, quantity);
	                addStmt.setString(2, wineSerial);
	                addStmt.setString(3, destinationStorage);
	                rowsAffected2 = addStmt.executeUpdate();
	            } else {
	                // If wine does not exist in the destination, insert a new row
	                String insertQuery = "INSERT INTO WineStorage_tbl ([WineBottle Serial], [Storage Serial], Quantity) VALUES (?, ?, ?)";
	                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
	                insertStmt.setString(1, wineSerial);
	                insertStmt.setString(2, destinationStorage);
	                insertStmt.setInt(3, quantity);
	                rowsAffected2 = insertStmt.executeUpdate();
	            }

	            conn.commit(); // Commit transaction
	            conn.close();
	            
	            return rowsAffected1 > 0 && rowsAffected2 > 0; // Transfer successful

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public static HashMap<String, String> getWineDetails(String wineSerial, String storageSerial) {
	        HashMap<String, String> wineDetails = new HashMap<>();

	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            Connection conn = AccessDatabaseConnection.getConnection();

	            String query = "SELECT [WineBottle Serial], [Storage Serial], Quantity FROM WineStorage_tbl WHERE [WineBottle Serial] = ? AND [Storage Serial] = ?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, wineSerial);
	            stmt.setString(2, storageSerial);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                wineDetails.put("Wine Serial", rs.getString("WineBottle Serial"));
	                wineDetails.put("Storage Serial", rs.getString("Storage Serial"));
	                wineDetails.put("Quantity", String.valueOf(rs.getInt("Quantity")));
	            }

	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return wineDetails;
	    }
	    
	    public static HashMap<Integer,Order> getAllOrders() {
	    	HashMap<Integer,Order> map = new HashMap<Integer,Order>();
	    	HashMap<Integer,ArrayList<OrderItem>> orderitems = getAllItems();
		        try {
		            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
		                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_Orders);
		                    ResultSet rs = stmt.executeQuery()) {
		                while (rs.next()) {
		                    int i = 1;
		                    int orderNumber = rs.getInt(i++);
		                    int employeeId = rs.getInt(i++);
		                    int customerId = rs.getInt(i++);
		                    String orderType = rs.getString(i++);
		                    Date orderDate = rs.getDate(i++);
		                    String currentStatus = rs.getString(i++);
		                    Date shipmentDate = rs.getDate(i++);
		                    ArrayList<OrderItem> items;
		                    if(orderitems.containsKey(orderNumber))
		                    	items = orderitems.get(orderNumber);
		                    else {
		                    	items = new ArrayList<>();
		                    }
		                    Order o = new Order(orderNumber, orderType, orderDate, currentStatus, shipmentDate, employeeId, customerId, items);
		                    if(orderType.equals("Urgent")) {
		                    	o = getUrgentOrder(o);
		                    }
		                    if(o!=null)
		                    	map.put(o.getOrderNumber(), o);
		                 
		                }
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }

		        return map;
	    }
	    
	    public static HashMap<Integer,ArrayList<OrderItem>> getAllItems() {
	    	HashMap<Integer,ArrayList<OrderItem>> map = new HashMap<Integer,ArrayList<OrderItem>>();
	    	HashMap<String,WineBottle> bottles = DatabaseController.getAllWineBottles();
		        try {
		            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		            try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
		                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_OrderItems);
		                    ResultSet rs = stmt.executeQuery()) {
		                while (rs.next()) {
		                    int i = 1;
		                    int orderItemNumber = rs.getInt(i++);
		                    int orderNumber = rs.getInt(i++);
		                    String bottleSerial = rs.getString(i++);
		                    int quantity = rs.getInt(i++);
		                    WineBottle bottle = bottles.get(bottleSerial);
		                    
		                    //create item from variables
		                    OrderItem item = new OrderItem(orderItemNumber, orderNumber, bottle, quantity);

		                    // add to map
		                    if(map.containsKey(orderNumber))
		                    	map.get(orderNumber).add(item);
		                    else {
		                    	ArrayList<OrderItem>items = new ArrayList<>();
		                    	items.add(item);
		                    	map.put(orderNumber, items);
		                    }
		                 
		                }
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }

		        return map;
	    }
	    

	    
	    public static Order getUrgentOrder(Order o) {
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = AccessDatabaseConnection.getConnection();
	                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_UrgentOrders)) {
	                
	                stmt.setInt(1, o.getOrderNumber());  // Set the parameter for OrderNumber
	                
	                try (ResultSet rs = stmt.executeQuery()) {
	                    if (rs.next()) {  // Fetch the first result
	                        int i = 1;
	                        int orderNumber = rs.getInt(i++);
	                        int priority = rs.getInt(i++);
	                        Date deliveryDate = rs.getDate(i++);

	                        return new Order(o.getOrderNumber(), o.getOrderType(), o.getOrderDate(), 
	                                         o.getCurrentStatus(), o.getShipmentDate(), 
	                                         o.getEmployeeId(), o.getCustomerId(), 
	                                         o.getOrderItems(), priority, deliveryDate);
	                    }
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
   
	    public static boolean addOrder(Order o) {
	        // Define the SQL query
	        final String SQL_Insert_Order = "INSERT INTO Order_tbl (OrderNumber, EmployeeID, MainCustomerID, OrderType, OrderDate, CurrentStatus, ShipmentDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        
	        try (Connection conn = AccessDatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(SQL_Insert_Order)) {
	        	
                java.sql.Date sqlDateOrder = new java.sql.Date(o.getOrderDate().getTime());
                java.sql.Date sqlDateShipment = new java.sql.Date(o.getShipmentDate().getTime());
                
	            // Set the query parameters
	            stmt.setInt(1, o.getOrderNumber());
	            stmt.setInt(2, o.getEmployeeId());
	            stmt.setInt(3, o.getCustomerId());
	            stmt.setString(4, o.getOrderType());
	            stmt.setDate(5, sqlDateOrder);
	            stmt.setString(6, o.getCurrentStatus());
	            stmt.setDate(7, sqlDateShipment);
	            
	            // Execute the query
	            stmt.executeUpdate();
	            
	            
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                	int number = generatedKeys.getInt(1);
	                    JOptionPane.showMessageDialog(null, "New Order number (" +number+ ") data added successfully!");
	                	if(o.getOrderType()=="Urgent")
	                		return addUrgentOrder(o, number);


	                } else {
	                    throw new SQLException("Inserting order failed, no AutoNumber obtained.");
	                }
	            }
	            return true;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public static boolean addUrgentOrder(Order o, int number) {
	    	
	        // Define the SQL query
	        final String SQL_Insert_UrgentOrder = "INSERT INTO UrgentOrder_tbl (OrderNumber, PriorityLevel, SpecificDeliveryDate) VALUES (?, ?, ?)";
	        
	        try (Connection conn = AccessDatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(SQL_Insert_UrgentOrder)) {
	        	
                java.sql.Date sqlDelivery = new java.sql.Date(o.getSpecificDeliveryDate().getTime());
                
	            // Set the query parameters
	            stmt.setInt(1,number);
	            stmt.setInt(2, o.getPriorityLevel());
	            stmt.setDate(3, sqlDelivery);
	            
	            // Execute the query
	            stmt.executeUpdate();
	            
	            return true;

		        } catch (SQLException e) {
		            e.printStackTrace();
		            return false;
		        }	    
	        }

	     public static boolean deleteUrgentOrder(int orderNumber) {
	         try {
	             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	             try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                     CallableStatement stmt = conn.prepareCall(Consts.SQL_Delete_UrgentOrder)) {
	             	
	                 stmt.setInt(1, orderNumber); 
	                 stmt.executeUpdate();
	 	  	                return true;
	               
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         }
	         return false;
	     }
	    
	     public static boolean updateOrder(Order o) {
	         try {
	             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	             try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                     CallableStatement stmt = conn.prepareCall(Consts.SQL_Upd_Order)) {
	               
	                java.sql.Date sqlDateOrder = new java.sql.Date(o.getOrderDate().getTime());
	                java.sql.Date sqlDateShipment = new java.sql.Date(o.getShipmentDate().getTime());
	                
		            stmt.setInt(1, o.getEmployeeId());
		            stmt.setInt(2, o.getCustomerId());
		            stmt.setString(3, o.getOrderType());
		            stmt.setDate(4, sqlDateOrder);
		            stmt.setString(5, o.getCurrentStatus());
		            stmt.setDate(6, sqlDateShipment);
	 	            stmt.setInt(7, o.getOrderNumber());


	                 stmt.executeUpdate();
	 	  	                return true;
	               
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         }
	         return false;
	     }
	     
	     public static boolean updateUrgentOrder(Order o) {
	         try {
	             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	             try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                     CallableStatement stmt = conn.prepareCall(Consts.SQL_Upd_Order)) {
	               
	                java.sql.Date sqlDateOrder = new java.sql.Date(o.getOrderDate().getTime());
	                java.sql.Date sqlDateShipment = new java.sql.Date(o.getShipmentDate().getTime());
	                
		            stmt.setInt(1, o.getEmployeeId());
		            stmt.setInt(2, o.getCustomerId());
		            stmt.setString(3, o.getOrderType());
		            stmt.setDate(4, sqlDateOrder);
		            stmt.setString(5, o.getCurrentStatus());
		            stmt.setDate(6, sqlDateShipment);
	 	            stmt.setInt(7, o.getOrderNumber());
	 	            
	                stmt.executeUpdate();
	               
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         }
	         
	         try {
	             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	             try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                     CallableStatement stmt = conn.prepareCall(Consts.SQL_Upd_UrgentOrder)) {
	               
	                java.sql.Date sqlDateOrder = new java.sql.Date(o.getSpecificDeliveryDate().getTime());
	                
		            stmt.setInt(1, o.getPriorityLevel());
		            stmt.setDate(2, sqlDateOrder);
	 	            stmt.setInt(3, o.getOrderNumber());
	 	            
	                stmt.executeUpdate();
	               
	                return true;
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         }
	         return false;
	     }
	     
	     public static boolean deleteOrder(int orderNumber) {
	         try {
	             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	             try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                     CallableStatement stmt = conn.prepareCall(Consts.SQL_Delete_Order)) {
	             	
	                 stmt.setInt(1, orderNumber); 
	                 stmt.executeUpdate();
	 	  	                return true;
	               
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         }
	         return false;
	     }
	     
	     
	     public static boolean updateStorage(String serial, String name, String id) {
	    	    String query = "UPDATE storages SET Name = ?, ID = ? WHERE Storage_Serial = ?";

	    	    try {
	    	        // Load UCanAccess Driver
	    	        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	    	        // Get database connection
	    	        try (Connection conn = AccessDatabaseConnection.getConnection();
	    	             PreparedStatement pstmt = conn.prepareStatement(query)) {

	    	            pstmt.setString(1, name);
	    	            pstmt.setString(2, id);
	    	            pstmt.setString(3, serial);

	    	            return pstmt.executeUpdate() > 0;
	    	        }
	    	    } catch (Exception e) {
	    	        e.printStackTrace();
	    	        return false;
	    	    }
	    	}

	     public static boolean addItem(Order o, WineBottle bottle, int quantity) {
		        // Define the SQL query
		        final String SQL_Insert_Order = "INSERT INTO OrderItem_tbl (OrderItemNumber, OrderNumber, WineBottleSerial, Quantity) VALUES (?, ?, ?, ?)";
		        
		        try (Connection conn = AccessDatabaseConnection.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(SQL_Insert_Order)) {
	                
		            // Set the query parameters
		            stmt.setInt(1, 0);
		            stmt.setInt(2, o.getOrderNumber());
		            stmt.setString(3, bottle.getWineSerialNum());
		            stmt.setInt(4, quantity);

		            // Execute the query
		            stmt.executeUpdate();
		            
		            
		            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
		                if (generatedKeys.next()) {
		                	int number = generatedKeys.getInt(1);
		                    JOptionPane.showMessageDialog(null, "New Item number (" +number+ ") data added successfully!");
		                } else {
		                    throw new SQLException("Inserting item failed, no AutoNumber obtained.");
		                }
		            }
		            return true;

		        } catch (SQLException e) {
		            e.printStackTrace();
		            return false;
		        }
	     }
	     
	     public static boolean deleteOrderItem(int itemNumber) {
	         try {
	             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	             try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                     CallableStatement stmt = conn.prepareCall(Consts.SQL_Delete_OrderItem)) {
	             	
	                 stmt.setInt(1, itemNumber); 
	                 stmt.executeUpdate();
	 	  	                return true;
	               
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         }
	         return false;
	     }
	     
	     public static boolean updateOrderItem(OrderItem item) {
	         try {
	             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	             try (Connection conn = AccessDatabaseConnection.getConnection();//DriverManager.getConnection(Consts.CONN_STR);
	                     CallableStatement stmt = conn.prepareCall(Consts.SQL_Upd_OrderItem)) {
	               
		            stmt.setInt(1, item.getOrderNumber());
		            stmt.setString(2, item.getWine().getWineSerialNum());
		            stmt.setInt(3, item.getQuantity());
	 	            stmt.setInt(4, item.getOrderItemNumber());

	                 stmt.executeUpdate();
	 	  	                return true;
	               
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         }
	         return false;
	     }
	     
}
	     

