package bll;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;

import dal.PictureData;

/**
 * @author Simen Sollie & Kristine Svaboe
 * @since 2013-11-04
 */

public class DisplayController {

	/**
	 * Manage list with pictures
	 */

	private List<PictureData> sortedPictureList;
	private List<PictureData> randomPictureList;
	private List<PictureData> thumbnailPictureList;
	private PictureController pictureController;
	private int current = 0;
	private final int MAX_SIZE = 100;
	private boolean isRandom;
	private int displayTime;

	public DisplayController(PictureController pictureController){
		this.pictureController = pictureController;
		sortedPictureList      = new ArrayList<PictureData>();
		
		// Default settings
		isRandom    = false;
		displayTime = 100;
	}

	public void getSortedList() {
		sortedPictureList 		= pictureController.getSortedPictureData();
		randomPictureList 		= new ArrayList<PictureData>(sortedPictureList);
		thumbnailPictureList 	= new ArrayList<PictureData>(sortedPictureList);
		Collections.shuffle(randomPictureList);
		if (!sortedPictureList.equals(randomPictureList))
			System.out.println("sorted og random er ikke like");
	}

	public BufferedImage getCurrentPicture() throws IOException{
		if ( sortedPictureList.isEmpty() ) {
			System.out.println("Henter ny liste");
			getSortedList();
			System.out.println("Ferdig � hente liste");
		}
		
		PictureData p;

		if ( isRandom ) {
			p = randomPictureList.remove(0);
			sortedPictureList.remove(p);
		}
		else {
			p = sortedPictureList.remove(0);
			randomPictureList.remove(p);
		}
		
//		if (current < MAX_SIZE-1){
//			current++;
//		} else {
//			current = 0;
//		}
		
		return getBufImage(p);
	}
	
	public List<PictureData> getCurrentPictureData() {
		return thumbnailPictureList;
	}

	private BufferedImage getBufImage(PictureData p) throws IOException{
		//		URL testUrl = new URL("http://pbs.twimg.com/media/BXrietbIgAAiroP.jpg");
		URL imageUrl = new URL(p.getUrlStd());
		InputStream in = imageUrl.openStream();
		BufferedImage image = ImageIO.read(in);
		in.close();
		return image;
	}

	public void setIsRandom(boolean isRandom) {
		this.isRandom = isRandom;
	}

	public int getDisplayTime() {
		return displayTime;
	}

	public void setDisplayTime(int displayTime) {
		this.displayTime = displayTime;
	}
}
