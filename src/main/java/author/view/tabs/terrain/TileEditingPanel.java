package main.java.author.view.tabs.terrain;

import static main.java.author.util.ActionListenerUtil.actionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Originally written to allow author to alter look of images. However, class is currently
 * not used.
 *
 */
public class TileEditingPanel extends JPanel {

	private TileSelectionManager myTileManager;
	private static final String ROTATE = "Rotate";
	private int myImgAngle; // in degrees

	public TileEditingPanel(TileSelectionManager tileManager) {
		myTileManager = tileManager;
		setPreferredSize(new Dimension(275, 350));
		add(constructRotateButton(), BorderLayout.NORTH);
	}

	private JButton constructRotateButton() {
		JButton rotateButton = new JButton(ROTATE);
		rotateButton.addActionListener(actionListener(this, "rotateImage"));
		return rotateButton;
	}

	@Override 
	public void paintComponent(Graphics g) {
		TileObject selectedTile = myTileManager.getCanvas().getSelectedTileObj();
		int pixelSize = myTileManager.getCurrentTileDisplay().getMyPixelSize();
		Image img = (selectedTile == null) ? null : selectedTile.getImage();

		if (img != null) {
			img = rotate((BufferedImage) img, myImgAngle);
			selectedTile.setImage(img);
			g.drawImage(img, (getWidth() - pixelSize)/2, (getHeight() - pixelSize)/2, null, null);
		}
	}

	/**
	 * NOTE: Obtained from http://www.javalobby.org/articles/ultimate-image/
	 * 
	 * Rotates a BufferedImage
	 * 
	 * @return the img rotated by the specified angle
	 */
	private BufferedImage rotate(BufferedImage img, int angle) {  
		int width = img.getWidth();  
		int height = img.getHeight();  
		BufferedImage dimg = new BufferedImage(width, height, img.getType());  
		Graphics2D g = dimg.createGraphics();  
		g.rotate(Math.toRadians(angle), width/2, height/2);  
		g.drawImage(img, null, 0, 0);  
		return dimg;  
	} 

	protected void setImageAngle(int degrees) {
		myImgAngle = degrees;
	}

	public void rotateImage(ActionEvent e) {
		myImgAngle += 90;
		update(getGraphics());
	} 

}
