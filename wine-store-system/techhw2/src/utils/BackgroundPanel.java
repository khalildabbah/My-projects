package utils;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
            	
    	        String relativePath = "resources/images/" + imagePath;
    	        File imageFile = new File(relativePath);

    	        if (imageFile.exists()) {
    	            ImageIcon imageIcon = new ImageIcon(relativePath);
    	            backgroundImage = imageIcon.getImage();
    	        }
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    
}
