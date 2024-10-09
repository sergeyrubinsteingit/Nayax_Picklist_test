package ProgramFiles.TestUsedClasses;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationPopupPanel {

	public static JFrame InformationPopupPanel (String popupMessage, boolean status_) {
		
		JFrame jFrame = new JFrame();
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout());
		JLabel jLabel = new JLabel(popupMessage);

		if (status_ == true) {
			
			jFrame.setBackground(Color.GREEN);

		} else {
			
			jFrame.setBackground(Color.RED);
			
		};//if	
		
		jPanel.add(jLabel);
		jFrame.add(jPanel);
		
		jFrame.setSize(400,200);
		jFrame.setLocationRelativeTo(null);
		
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
				
		return jFrame;
		
	};//InformationPopupPanel
	
}
