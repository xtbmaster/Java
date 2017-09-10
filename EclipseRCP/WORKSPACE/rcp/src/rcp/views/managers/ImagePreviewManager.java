package rcp.views.managers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.eclipse.swt.widgets.Event;

import rcp.entities.Img;

public class ImagePreviewManager {

	private static ImagePreviewManager instance;

	private List<Img> imgList; // A list of all images

	// A map to detect whether there are already exist some copies of an image
	private static Map<String, Integer> copiesCounter;

	private ImagePreviewManager() {
		imgList = new ArrayList<>();
		copiesCounter = new HashMap<>();
	}

	public static synchronized ImagePreviewManager getInstance() {
		if (instance == null) {
			instance = new ImagePreviewManager();
		}
		return instance;
	}

	/**
	 * Checks the existence of the provided file name in a user collection.
	 * 
	 * @param fileName
	 *            to check.
	 * @return
	 */
	private boolean containsName(final String fileName) {
		for (Img img : imgList) {
			if (img.getName().equals(fileName))
				return true;
		}
		return false;
	}

	/**
	 * Retrieves the position index of a called table item to find the
	 * corresponding layer in the preview frame.
	 * 
	 * @param arg
	 *            table item instance.
	 * @return index of table item.
	 */
	public int getIndexToChange(final Event arg) {
		int index = 0;
		String str = arg.item.toString();
		String result = str.substring(str.indexOf("{") + 1, str.indexOf("}")).trim();

		for (int i = 0; i <= imgList.size(); i++) {
			if (imgList.get(i).getName().equals(result)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public void addToCollection(final BufferedImage img, final String fileName) {
		Img imgEntity = new Img(img, fileName);
		imgList.add(imgEntity);
	}

	/**
	 * Adds a 'copy' mark to a name if the file already exist in a user
	 * collection.
	 * 
	 * @param originalFileName
	 *            to process.
	 * @return processed file name.
	 */
	public String getTableItemName(final String originalFileName) {

		String finalFileName;
		int numberOfCopies = 1;

		if (containsName(originalFileName)) {
			numberOfCopies = copiesCounter.get(originalFileName) + 1;
			finalFileName = originalFileName + " (copy " + numberOfCopies + ")";

		} else {
			finalFileName = originalFileName;
		}

		copiesCounter.put(originalFileName, numberOfCopies);

		return finalFileName;

	}
	
	public BufferedImage uploadImage(String path) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File(path));
		} catch (IOException ex) {
		}

		return img;
	}

}
