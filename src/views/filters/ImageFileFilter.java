package views.filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * It provides a filter which can be used in the JFileChooser when a user
 * wants to specify an image file.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
public class ImageFileFilter extends FileFilter {

	String [] suffixes;
	String description;
	
	public ImageFileFilter(String []suffixes, String description) {
		this.suffixes = suffixes;
		this.description = description;
	}
	
	boolean appartient(String suffixe) {
		for( int i = 0; i < suffixes.length ; ++i) {
			if(suffixe.equals(suffixes[i])) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public boolean accept(File f) {
		if (f.isHidden()) {
			return false;
		}
		if (f.isDirectory()) {
			return true;
		}
		
		String suffixe = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 &&  i < s.length() - 1) {
			suffixe=s.substring(i+1).toLowerCase();
		}
		return suffixe != null && appartient(suffixe);
	}

	@Override
	public String getDescription() {
		return description;
	}

}
