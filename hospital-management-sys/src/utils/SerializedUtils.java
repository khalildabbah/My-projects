package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import control.Hospital;

public class SerializedUtils {

	private static final String FILENAME = "Hospital.ser";
	
	public static void writeHospital() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				new File(FILENAME)));
		oos.writeObject(Hospital.getInstance());
		oos.flush();
		oos.close();
	}
	
	public static Hospital readHospital() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				new File(FILENAME)));
		Hospital hospital = (Hospital) ois.readObject();
		ois.close();
		return hospital;
	}
	
}
