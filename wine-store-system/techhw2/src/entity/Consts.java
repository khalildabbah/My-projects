package entity;

import java.net.URLDecoder;

import utils.AccessDatabaseConnection;


        public final class Consts {
            private Consts() {
                throw new AssertionError();
            }
   //         protected static final String DB_FILEPATH = getDBPath();
   //         public static final String CONN_STR = "jdbc:ucanaccess://"  + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
            
         // all select queries   
            public static final String  SQL_SEL_Manufacturers= "SELECT Manufacturer_tbl.* FROM Manufacturer_tbl";
            public static final String  SQL_SEL_Wines= "SELECT WineBottle_tbl.* FROM WineBottle_tbl";
            public static final String  SQL_SEL_Wine_Comp= "SELECT WineBottleComposite_tbl.* FROM WineBottleComposite_tbl";
            public static final String  SQL_SEL_Wine_Types= "SELECT WineType_tbl.* FROM WineType_tbl";
            public static final String  SQL_SEL_Food_Pairings= "SELECT FoodPairings_tbl.* FROM FoodPairings_tbl";
            public static final String  SQL_SEL_Occasions= "SELECT Occasions_tbl.* FROM Occasions_tbl";
            public static final String  SQL_SEL_Employees= "SELECT Employee_tbl.* FROM Employee_tbl";
            public static final String  SQL_SEL_Customers= "SELECT Customer_tbl.* FROM Customer_tbl";
            public static final String  SQL_SEL_Orders= "SELECT Order_tbl.* FROM Order_tbl";
            public static final String  SQL_SEL_UrgentOrders= "SELECT * FROM UrgentOrder_tbl WHERE OrderNumber = ?";
            public static final String  SQL_SEL_OrderItems= "SELECT OrderItem_tbl.* FROM OrderItem_tbl";

            
            // all Add queries 
            public static final String SQL_Ins_Manufacturer = "{ call INS_MANUFACTURER(?,?,?,?)}";
            public static final String SQL_Ins_Wine = "{ call Ins_Wine(?,?,?,?,?,?,?,?)}";
            public static final String SQL_Ins_Wine_Comp = "{ call Ins_wineComposite(?,?,?)}";
            public static final String SQL_Ins_Order = "{ call Ins_Order(?,?,?,?,?,?,?)}";
            public static final String SQL_Ins_UrgentOrder = "{ call Ins_UrgentOrder(?,?,?)}";
            public static final String SQL_Ins_Ins_OrderItem = "{ call Ins_OrderItem(?,?,?)}";


            
            //all Update queries
            public static final String SQL_Upd_Wine_Bottle =  "UPDATE WineBottle_tbl SET WineBottle_tbl.WineName = ?, WineBottle_tbl.Description = ?, WineBottle_tbl.ProductionYear = ?, WineBottle_tbl.PricePerBottle = ?, WineBottle_tbl.SweetnessLevel = ?, WineBottle_tbl.ProductImage = ?  WHERE WineBottle_tbl.WineBottleSerial = ?;";
            public static final String SQL_Upd_Manufacturer =  "UPDATE Manufacturer_tbl SET Manufacturer_tbl.FullName = ?, Manufacturer_tbl.ContactPhoneNumber = ?, Manufacturer_tbl.Address = ?, Manufacturer_tbl.Email = ? WHERE Manufacturer_tbl.ID = ?;";
            public static final String SQL_Upd_Employee =  "UPDATE Employee_tbl SET Employee_tbl.Name = ?, Employee_tbl.PhoneNumber = ?, Employee_tbl.Email = ?, Employee_tbl.OfficeAddress = ?, Employee_tbl.EmploymentDate = ?, Employee_tbl.Department = ? WHERE Employee_tbl.ID = ?;";
            public static final String SQL_Upd_Order =  "UPDATE Order_tbl SET Order_tbl.EmployeeID = ?, Order_tbl.MainCustomerID = ?, Order_tbl.OrderType = ?, Order_tbl.OrderDate = ?, Order_tbl.CurrentStatus = ?, Order_tbl.ShipmentDate = ? WHERE Order_tbl.OrderNumber = ?;";
            public static final String SQL_Upd_UrgentOrder =  "UPDATE UrgentOrder_tbl SET UrgentOrder_tbl.PriorityLevel = ?, UrgentOrder_tbl.SpecificDeliveryDate = ? WHERE UrgentOrder_tbl.OrderNumber = ?;";
            public static final String SQL_Upd_OrderItem =  "UPDATE OrderItem_tbl SET OrderItem_tbl.OrderNumber = ?, OrderItem_tbl.WineBottleSerial = ?, OrderItem_tbl.Quantity = ? WHERE OrderItem_tbl.OrderItemNumber = ?;";

            
            //all delete queries
            public static final String SQL_Delete_Wine = "{ call Delete_wine_Bottle(?)}";
            public static final String SQL_Delete_Man = "{ call Delete_Man(?)}";
            public static final String SQL_Delete_Employee = "{ call Delete_Employee(?)}";
            public static final String SQL_Delete_Order = "{ call Delete_Order(?)}";
            public static final String SQL_Delete_UrgentOrder = "{ call Delete_UrgentOrder(?)}";
            public static final String SQL_Delete_OrderItem = "{ call Delete_OrderItem(?)}";

            		
            //wine recommendation report queries
            public static final String SQL_Empty_Rec = "{ call emptyRecommendationTbl}";
            public static final String SQL_Upd_Rec = "{ call generateRecommendationReport(?)}";
            


            
         //   private static String getDBPath() {
            	
            	//return AccessDatabaseConnection.getConnection();
        //    }
            	/*try {
            		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            		String decoded = URLDecoder.decode(path, "UTF-8");
            		if (decoded.contains(".jar"))
            		{
            			decoded = decoded.substring(0, decoded.lastIndexOf('/'));
            			System.out.println(decoded);
            	
            			return decoded + "/resources/Cheers_System1.accdb";}
            		else {
            			decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
            			//System.out.println(decoded);
            	
            			return decoded + "src/resources/Cheers_System1.accdb";}
            			
            		
            	} catch (Exception e) {
            		e.printStackTrace();
            		return null;
            	}
            }*/

}
