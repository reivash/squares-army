package com.caberodev.squarearmy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.caberodev.squarearmy.appearance.ShapeDrawer;

/**
 * 
 * @author Javier Cabero Guerra <br>
 * 
 * Copyright 2015 (c) All Rights Reserved.
 *                                          
 */
public class DrawEngine {
	
	// Drawing helper
	public static SpriteBatch batch;

	// Camera handler
	public static HeroCamera camera;
	
	// Default font for 2D text
	public static BitmapFont font;

	// List for drawables
	private static ArrayList<Drawer> drawList;
	private static ArrayList<Text2D> textList;
	
	// Background color
	public static Vec4 bgColor = new Vec4(0.58f, 0.58f, 0.7f, 1);
	
	public static void addDrawable(Drawer item) {
		drawList.add(item);
	}
	
	public static void delDrawable(Drawer item) {
		drawList.remove(item);
	}
	
	public static void addDrawable(Text2D item) {
		textList.add(item);
	}
	
	public static void delDrawable(Text2D item) {
		textList.remove(item);
	}
	 
	public static void start() {
		
		// Default SpriteBatch
		batch = new SpriteBatch();
		
		// Default font is Calibri
		font = new BitmapFont(Gdx.files.internal("data/calibri.fnt"),
				 			  Gdx.files.internal("data/calibri.png"), 
				 			  false);
		
		drawList = new ArrayList<Drawer>();
		textList = new ArrayList<Text2D>();
        camera   = new HeroCamera();
        
        Text2D screenInfo = new Text2D(batch, font, 15, Gdx.graphics.getHeight() - 15);
        screenInfo.setText("Dev Text | Screen (" 
        		+ Gdx.graphics.getWidth() + ", " 
        		+ Gdx.graphics.getHeight() 
        		+ ") Density: " + Gdx.graphics.getDensity());
        
        Text2D playerInfo = new Text2D(batch, font, 15, Gdx.graphics.getHeight() - 55);
        playerInfo.setText("Player: (0, 0)"); // TODO: Connect with hero player
	}
	
	public static void draw() {
		
		// Update game camera
		camera.followHeroPlayer(); 
		ShapeDrawer.shapeRenderer.setProjectionMatrix(camera.combined);
		
		// Clear and set background tint
		Gdx.gl.glClearColor(bgColor.x, bgColor.y, bgColor.z, bgColor.w);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Shape drawing
		batch.begin();
		for(Drawer item : drawList) 
			item.draw();
		batch.end();
		
		// Text drawing
		batch.begin();
		for(Text2D item : textList)
			item.draw();
		batch.end();
	}
}
