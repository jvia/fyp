package org.bham.aucom.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RedGreenGradientImageGenerator {
	private static ArrayList<Color> normalcolors; 
	private static ArrayList<Color> abnormalcolors; 
	private static void createColors(){
		setNormalcolors(new ArrayList<Color>());
		getNormalcolors().add(new Color(0,255,0));
		getNormalcolors().add(new Color(0,230,0));
		getNormalcolors().add(new Color(0,215,0));
		getNormalcolors().add(new Color(0,200,0));
		getNormalcolors().add(new Color(0,180,0));
		getNormalcolors().add(new Color(0,170,0));
		getNormalcolors().add(new Color(0,150,0));
		getNormalcolors().add(new Color(0,140,0));
		getNormalcolors().add(new Color(0,120,0));
		getNormalcolors().add(new Color(0,100,0));
		getNormalcolors().add(new Color(0,90,0));
		setAbnormalcolors(new ArrayList<Color>());
		getAbnormalcolors().add(new Color(200,0,0));
		getAbnormalcolors().add(new Color(255,40,0));
		getAbnormalcolors().add(new Color(255,70,0));
		getAbnormalcolors().add(new Color(255,85,0));
		getAbnormalcolors().add(new Color(255,100,0));
		getAbnormalcolors().add(new Color(255,115,0));
		getAbnormalcolors().add(new Color(255,130,0));
		getAbnormalcolors().add(new Color(255,140,0));
		getAbnormalcolors().add(new Color(255,150,0));
		getAbnormalcolors().add(new Color(255,170,0));
		getAbnormalcolors().add(new Color(255,180,0));
		
	}
	/*
	 * returns a color for the specified value.
	 * Creates colors when running for the first time
	 * for values bigger than or equal to 1 colors are derivates of green
	 * for values  lower than 1 colors are red
	 */
	public static Color getColor(double val){
		if(getAbnormalcolors()==null)
			createColors();
		if(val >=1){
			int index = Math.max(0,Math.min(getNormalcolors().size()-1,(int)Math.round((val-1)*(getNormalcolors().size()-1))));
			return getNormalcolors().get(index);
		}else{
			int index = Math.max(0, Math.min(getAbnormalcolors().size()-1,(int)Math.round(val*(getAbnormalcolors().size()-1))));
			return getAbnormalcolors().get(index);
		}
	}
	public static Image getImage(double val, int num){
		int width = 12;
		int height = 12;
		Color c = getColor(val);
		BufferedImage off_Image = new BufferedImage(20, 15, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = off_Image.createGraphics();
		g2.setColor(c);
		g2.fillRect(0, 0, width, height);
		g2.setColor(Color.black);
		g2.drawRect(0, 0, width, height);
		char chars[] = new Integer(num).toString().toCharArray();
		if(chars.length == 1){
			char tmp = chars[0];
			chars = new char[2];
			chars[0]= '0';
			chars[1] = tmp;
		}
		g2.setFont(new Font("Arial", Font.PLAIN, 10));
		g2.drawChars(chars,0,1,4,10);
		return off_Image;
	}
	public static Image getImage(Color c){
		BufferedImage off_Image = new BufferedImage(20, 15, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = off_Image.createGraphics();
		g2.setColor(c);
		g2.fillRect(0, 0, 18, 15);
		g2.setColor(Color.black);
		g2.drawRect(0, 0, 18, 15);
		return off_Image;
	}
	private static void setAbnormalcolors(ArrayList<Color> abnormalcolors) {
		RedGreenGradientImageGenerator.abnormalcolors = abnormalcolors;
	}
	public static ArrayList<Color> getAbnormalcolors() {
		if(abnormalcolors==null)
			createColors();
		return abnormalcolors;
	}
	private static void setNormalcolors(ArrayList<Color> normalcolors) {
		RedGreenGradientImageGenerator.normalcolors = normalcolors;
	}
	public static ArrayList<Color> getNormalcolors() {
		if(normalcolors==null)
			createColors();
		return normalcolors;
	}
}