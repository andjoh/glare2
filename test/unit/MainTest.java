package unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dal.*;
import bll.*;
import glare.ClassFactory;
import gui.*;
import resources.*;

public class MainTest {
	/**
	 * USE THIS CLASS TO RETRIEVE PICTUREDATA, MAKE PICTURES IN DISPLAYCONTROLLER AND DISPLAY
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {

		// Test using dummy DatabaseManager and dummy hashtag
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				// DUMMY CHECK
				DatabaseHandler dbHandler     = new DatabaseHandler();
				DatabaseManagerDummy dbManDum = new DatabaseManagerDummy(dbHandler);
				PictureController picCtrl     = new PictureController(dbManDum);

				// Do some dummy stuff
				List<String> sourcesDummy;
				Set<String> hashtagsDummy;

				// Set dummy sources
				sourcesDummy = new ArrayList<String>();
				sourcesDummy.add("instagram");
				sourcesDummy.add("twitter");

				// Set test data for source and hashtag
				hashtagsDummy = new HashSet<String>();

				hashtagsDummy.add("twittermotjavatesting");
				hashtagsDummy.add("raskebriller");

				// Set dummy sources and hashtag
				dbManDum.setSources(sourcesDummy);
				dbManDum.setHashtags(hashtagsDummy);

				// Run test
				picCtrl.searchPictureData();
				picCtrl.processPictureData();
				ViewController vc = new ViewController(picCtrl,dbManDum);
				

				try {
					ShowInterface showInterface = new ShowInterface(vc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}