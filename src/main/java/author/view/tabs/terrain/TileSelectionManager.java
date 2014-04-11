package main.java.author.view.tabs.terrain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;

/**
 * This class manages the interactions between the TileDisplay (where TileObjects are selected),
 * the TileEditingPanel (where TileObjects are edited), and the Canvas (where TileObjects are displayed)
 *
 */
public class TileSelectionManager {

	private Canvas myCanvas;
	private TileEditingPanel myTileEditPanel;
	private List<TileDisplay> myTileDisplays;
	private JTabbedPane myTileDisplayTab;
	
	public TileSelectionManager(Canvas canvas) {
		myCanvas = canvas;
        myTileDisplays = new ArrayList<TileDisplay>();
        myTileDisplayTab = new JTabbedPane();
		myTileEditPanel = new TileEditingPanel(this);
	}
	
	protected void addTileDisplay(File mapFile, int pixels) {
		TileDisplay currTileDisp = new TileDisplay(this, mapFile, pixels);
		myTileDisplays.add(currTileDisp);
		myTileDisplayTab.addTab(mapFile.getName(), currTileDisp.getTileScrollPane());
	}
	
	public JTabbedPane getTileDisplayTabs() {
		return myTileDisplayTab;
	}
	
	public TileDisplay getTileDisplay() {
		int index = myTileDisplayTab.getSelectedIndex();
		return myTileDisplays.get(index);
	}
	
	public TileEditingPanel getTileEditPanel() {
		return myTileEditPanel;
	}
	
	public Canvas getCanvas() {
		return myCanvas;
	}
}
