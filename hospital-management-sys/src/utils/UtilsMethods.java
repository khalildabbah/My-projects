package utils;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UtilsMethods {
	
	public static double dateDiffInDays(Date date1, Date date2) {
		return  Math.abs( ((date1.getTime() - date2.getTime())
                / (1000 * 60 * 60 * 24)));
	}
	public static Date parseDate(String string) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(string);
		} catch (ParseException e) {
			throw new RuntimeException("Unable to parse the Date");
		}
		return date;
	}
	
	public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

	public static void resizeAndCopyImage(File inputFile, File outputFile) throws IOException {
		BufferedImage originalImage = ImageIO.read(inputFile);
		BufferedImage resizedImage = new BufferedImage(75, 75, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = resizedImage.createGraphics();
		g2d.drawImage(originalImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH), 0, 0, null);
		g2d.dispose();
		ImageIO.write(resizedImage, "jpg", outputFile);
	}

	public static void openImage(String filePath) {
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			ImageIcon imageIcon = new ImageIcon(filePath);
			JLabel imageLabel = new JLabel(imageIcon);
			JFrame frame = new JFrame("Image Viewer");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().add(imageLabel, BorderLayout.CENTER);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Image file not found!");
		}
	}

}
