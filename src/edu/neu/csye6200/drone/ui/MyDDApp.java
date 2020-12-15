package edu.neu.csye6200.drone.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import edu.neu.csye6200.drone.DDSimulation;


/**
 * 
 * @author {NUID: 001061445}
 *
 */
public class MyDDApp extends DDApp {
	
    public static void main(String[] args) {
    	new MyDDApp();
    }
	
	private DDSimulation simulation;	
	private DDPanel panel;
	private JPanel buttonPanel = null;
	
	private static JComboBox<String> ruleComboBox;
	private static JButton startBtn;
	private static JButton stopBtn;
	private static JButton pauseBtn;
	
	public MyDDApp() {
		
		simulation = new DDSimulation(); // Ensure that the simulation exists
		
		customizeGUI(); // Final GUI adjustments
		showUI();     // Launch the GUI and make it visible
		
		simulation.addObserver(panel);
	}
	
	/**
	 * Misc. UI adjustments
	 */
	private void customizeGUI() {
		frame.setMinimumSize(new Dimension(500, 300));
		frame.setMaximumSize(new Dimension(1200, 900));
		frame.setSize(800, 600);     // Width, height
		frame.setTitle("MyDDApp");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	/**
	 * Add buttons to the north panel and create action listener events to 
	 * perform operations
	 */
	@Override
	public JPanel getNorthPanel() {
		buttonPanel = new JPanel();    // Build the button panel
		buttonPanel.setLayout(new FlowLayout()); // Buttons will flow from the left
		buttonPanel.setBackground(Color.GRAY);
		
		ruleComboBox = new JComboBox(new String[] {"DDRule1", "DDRule2", "DDRule3"});
		ruleComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String rule = (String)ruleComboBox.getSelectedItem();
				simulation.setRule(rule);
			}
		});
		
		startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ruleComboBox.setEnabled(false);
				simulation.startSim();
			}
		});
		
		stopBtn = new JButton("Stop");
		stopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ruleComboBox.setEnabled(true);
				simulation.stopSim();
			}
		});
		
		pauseBtn = new JButton("Pause / Resume");
		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ruleComboBox.setEnabled(false);
				simulation.pauseSim();
			}
		});
	
		buttonPanel.add(ruleComboBox);
		buttonPanel.add(startBtn);
		buttonPanel.add(stopBtn);
		buttonPanel.add(pauseBtn);
		
		return buttonPanel;
	}

	@Override
	public JPanel getMainPanel() {
		panel = new DDPanel();
		return panel;
	}	
}
