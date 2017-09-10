package rcp.entities;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Container for image files.
 * 
 * @author art
 *
 */
public class Img {

	private BufferedImage pic;
	private String name;

	public Img(BufferedImage pic, String name) {
		this.pic = pic;
		this.name = name;
	}

	public BufferedImage getBufferedImage() {
		return pic;
	}

	public ImageIcon getImageIcon() {
		return new ImageIcon(pic);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
