package utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtillsMethods {
	
	
	
	
	public static Date parseDate(String string) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(string);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
