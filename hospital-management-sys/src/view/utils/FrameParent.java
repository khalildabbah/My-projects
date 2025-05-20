package view.utils;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class FrameParent extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	public FrameParent(String title, int width, int height, boolean close) {
		setTitle(title);
		if (close) {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(144, 238, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public JPanel getMainPane() {
		return contentPane;
	}
	
}
