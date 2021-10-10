package com.comflip.engine;

public class Collisions {
	public static boolean axisX(int posX1, int widht1, int posX2, int widht2) {
		return ((posX1 + widht1 > posX2) && (posX2 + widht2 > posX1));
	}
	public static boolean axisY(int posY1, int height1, int posY2, int height2) {
		return ((posY1 + height1 > posY2) && (posY2 + height2 > posY1));

	}
	
	public static boolean toLowerSide(int posY1, int height1, int posY2, int step) {
		return ((posY1 + height1) > (posY2 - step) && (posY1 < posY2));
	}
	public static boolean toUpperSide(int posY2, int height2, int posY1, int step) {
		return ((posY2 + height2) > (posY1 - step) && (posY1 > posY2));
	}
	public static boolean toRightSide(int posX1, int widht1, int posX2, int step) {
		return ((posX1 + widht1) > (posX2 - step) && (posX1 < posX2));
	}
	public static boolean toLeftSide(int posX2, int widht2, int posX1, int step) {
		return ((posX2 + widht2) > (posX1 - step) && (posX1 > posX2));
	}
}
