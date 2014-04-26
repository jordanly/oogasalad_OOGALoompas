package main.java.author.view.tabs.item;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

import main.java.author.controller.TabController;
import main.java.author.controller.tabbed_controllers.ItemController;
import main.java.author.view.components.ImageCanvas;
import main.java.author.view.global_constants.ObjectEditorConstants;
import main.java.author.view.tabs.EditorTab;
import main.java.author.view.tabs.ObjectEditorTab;
import main.java.schema.tdobjects.ItemSchema;
import main.java.schema.tdobjects.TDObjectSchema;
import main.java.schema.tdobjects.items.AnnihilatorItemSchema;
import main.java.schema.tdobjects.items.AreaBombItemSchema;
import main.java.schema.tdobjects.items.InstantFreezeItemSchema;
import main.java.schema.tdobjects.items.LifeSaverItemSchema;
import main.java.schema.tdobjects.items.RowBombItemSchema;

public abstract class AbstractItemEditorTab extends ObjectEditorTab {
	JSpinner timeSpinner, costSpinner, damageSpinner, flashSpinner, rangeSpinner, freezeSpinner;
	List<ItemSchema> itemSchemas;
	List<JRadioButton> allButtons;
	JButton itemImageButton;
	ImageCanvas itemImageCanvas;
	
	public AbstractItemEditorTab(TabController itemController, String objectName) {
		super(itemController, objectName);
	}

	@Override
	public void saveTabData() {
		ItemController controller = (ItemController) myController;
		itemSchemas = new ArrayList<ItemSchema>();
		
		//implement better reflection here (proper polymorphism)
		for (TDObjectSchema item : objectMap.values()) {
			ItemSchema itemSchema = null;
			if (item instanceof AnnihilatorItemSchema) {
				itemSchema = new AnnihilatorItemSchema();
			}
			if (item instanceof AreaBombItemSchema) {
				itemSchema = new AreaBombItemSchema();
			}
			if (item instanceof InstantFreezeItemSchema) {
				itemSchema = new InstantFreezeItemSchema();
			}
			if (item instanceof LifeSaverItemSchema) {
				itemSchema = new LifeSaverItemSchema();
			}
			if (item instanceof RowBombItemSchema){
				itemSchema = new RowBombItemSchema();
			}

			Map<String, Serializable> itemAttributes = item.getAttributesMap();
			
			for (String attribute : itemAttributes.keySet()) {
				itemSchema.addAttribute(attribute, itemAttributes.get(attribute));	
			}
			
			itemSchema.addAttribute(TDObjectSchema.IMAGE_NAME, "item.gif");
			itemSchemas.add(itemSchema);
		}
		controller.addItems(itemSchemas);
	}
	
	public List<ItemSchema> getItemSchemas() {
		return itemSchemas;
	}
	
	@Override
	protected void updateSchemaDataFromView() {
		super.updateSchemaDataFromView();
		//TDObjectSchema myCurrentObject = getSelectedObject();
	}
	
	@Override
	protected void updateViewWithSchemaData(Map<String, Serializable> map) {
		super.updateViewWithSchemaData(map);
		//update upgradeDropDown in TowerEditorTab
	}
	
	@Override
	protected void addListeners() {
		super.addListeners();
		itemImageButton.addActionListener(new FileChooserListener(itemImageCanvas));
		for (JRadioButton button : allButtons) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateSchemaDataFromView();
				}
			});
		}
	}
	
	public abstract class AbstractItemTabViewBuilder extends ObjectTabViewBuilder {

		public AbstractItemTabViewBuilder(EditorTab editorTab) {
			super(editorTab);
		}

		@Override
		protected JComponent makePrimaryObjectGraphicPane() {
			JPanel result = new JPanel();
			result.setLayout(new BorderLayout());

			itemImageCanvas.setSize(new Dimension(
					ObjectEditorConstants.IMAGE_CANVAS_SIZE,
					ObjectEditorConstants.IMAGE_CANVAS_SIZE));
			itemImageCanvas.setBackground(Color.BLACK);
			result.add(itemImageCanvas, BorderLayout.CENTER);
			itemImageButton = makeChooseGraphicsButton("Set " + objectName
					+ " Image");
			result.add(itemImageButton, BorderLayout.SOUTH);
			return result;
		}

		@Override
		protected JComponent makeSecondaryImagesGraphicPane() {
			return null;
		}
	
		@Override
		protected JComponent makeFieldPane() {
			JPanel result = new JPanel(new GridLayout(0, 2));
			for (JSpinner spinner : spinnerFields) {
				result.add(makeFieldTile(spinner));
			}
			return result;
		}
	}
}
