package main.java.author.view.tabs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.author.controller.MainController;

public class GameSettingsEditorTab extends EditorTab{

	private JPanel settingsPanel = new JPanel();
	private JComboBox gameModeList;
	private JComboBox gameDifficultyList;
	
	private JLabel levelsPerGameLabel;
	private JLabel wavesPerLevelLabel;
	private JLabel enemiesPerWaveLabel;
	private JLabel beginningMoneyLabel;
	private JLabel tilesPerRowLabel;
	private JLabel tilesPerColumnLabel;
	
	private JTextField levelsPerGameField;
	private JTextField wavesPerLevelField;
	private JTextField enemiesPerWaveField;
	private JTextField beginningMoneyField;
	private JTextField tilesPerRowField;
	private JTextField tilesPerColumnField;
	
	private NumberFormat numberFormat;
	
	private JButton submitButton;

	public GameSettingsEditorTab(MainController controller){
		super(controller);
		createSettingsPanel();
		add(settingsPanel, BorderLayout.CENTER);
	}

	private void createSettingsPanel() {
		
		settingsPanel.setLayout(new BorderLayout());
		
		settingsPanel.add(makeDropDownMenus(), BorderLayout.NORTH);
		settingsPanel.add(makeAttributesPane(), BorderLayout.SOUTH);
		
		settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}
	
	private JComponent makeDropDownMenus(){
		JPanel dropDownMenus = new JPanel();
		dropDownMenus.setLayout(new BorderLayout());
		
		String[] gameModeStrings = {"Survival Mode", "Boss Mode"};
		gameModeList = new JComboBox(gameModeStrings); 
		gameModeList.setSelectedIndex(1);
		gameModeList.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// Would probably switch between the specific attributes to display or just make unique panels for each as classes.
				// and then do some more logic outside of this action listener to decide what to display.
			}
			
		});
		
		String[] gameDifficultyStrings = {"Easy", "Medium", "Hard"};
		gameDifficultyList = new JComboBox(gameDifficultyStrings);
		gameDifficultyList.setSelectedIndex(1);
		gameDifficultyList.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		dropDownMenus.add(gameModeList, BorderLayout.NORTH);
		dropDownMenus.add(gameDifficultyList, BorderLayout.SOUTH);
		
		return dropDownMenus;
	}
	
	private JComponent makeAttributesPane(){
		JPanel attributes = new JPanel();
		
		attributes.setLayout(new BorderLayout());
		attributes.add(makeLabelPane(), BorderLayout.WEST);
		attributes.add(makeFieldPane(), BorderLayout.EAST);
		
		attributes.add(makeSubmitButton(), BorderLayout.SOUTH);
		
		return attributes;
	}
	
	private JComponent makeSubmitButton(){
		submitButton = new JButton("Submit");
		
		submitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		
		return submitButton;
	}
	
	private JComponent makeLabelPane(){
		levelsPerGameLabel = new JLabel("Levels Per Game: ");
		wavesPerLevelLabel = new JLabel("Waves Per Level: ");
		enemiesPerWaveLabel = new JLabel("Enemies Per Wave: ");
		beginningMoneyLabel = new JLabel("Beginning Money: ");
		tilesPerRowLabel = new JLabel("Number of Rows: ");
		tilesPerColumnLabel = new JLabel("Number of Columns: ");
		
		JPanel labels = new JPanel( new GridLayout(0, 1));
		
		labels.add(levelsPerGameLabel);
		labels.add(wavesPerLevelLabel);
		labels.add(enemiesPerWaveLabel);
		labels.add(beginningMoneyLabel);
		labels.add(tilesPerRowLabel);
		labels.add(tilesPerColumnLabel);
		
		return labels;
	}
	
	private JComponent makeFieldPane(){
		levelsPerGameField = new JFormattedTextField(numberFormat);
		levelsPerGameField.setColumns(10);
		wavesPerLevelField = new JFormattedTextField(numberFormat);
		enemiesPerWaveField = new JFormattedTextField(numberFormat);
		beginningMoneyField = new JFormattedTextField(numberFormat);
		tilesPerRowField = new JFormattedTextField(numberFormat);
		tilesPerColumnField = new JFormattedTextField(numberFormat);
		
		JPanel fields = new JPanel(new GridLayout(0, 1));
		
		fields.add(levelsPerGameField);
		fields.add(wavesPerLevelField);
		fields.add(enemiesPerWaveField);
		fields.add(beginningMoneyField);
		fields.add(tilesPerRowField);
		fields.add(tilesPerColumnField);
		
		return fields;
	}
}