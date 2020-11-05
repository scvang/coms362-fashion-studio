package com.fashion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Picture extends JFrame {

	private JPanel contentPane;
	
	public static void display(String imgPath, String description) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Picture frame = new Picture();
					frame.setVisible(true);
					
					// This creates a test model.
					String text ="<html>" + description + "</html>";
					
					frame.add(new JLabel(text,new ImageIcon(imgPath),JLabel.RIGHT));
					frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Picture() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
