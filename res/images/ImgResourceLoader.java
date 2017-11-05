package images;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Allow proper packaging of image files into the .jar file
 * 
 * @author brian
 *
 */
public class ImgResourceLoader {

	static ImgResourceLoader irl = new ImgResourceLoader();
	
	/**
	 * Allow access to the packaged images
	 * 
	 * @param filename - name of the image
	 * @return - the image as an Image object
	 */
	public static Image getImage (String filename) {
		return Toolkit.getDefaultToolkit().getImage(irl.getClass().getResource(filename));
	}
	
}
