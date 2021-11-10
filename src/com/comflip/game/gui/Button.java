package com.comflip.game.gui;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Font;
import com.comflip.game.abstracts.GUI;

public class Button extends GUI {
	private Font font;
	private String string;
	private int widhtText;
	
	public Button(String string) {
		this.string = string;		
	}
	
	public void update(GameContainer gameContainer, float dt) {
		
	}
	
	public void render(Renderer r) {		
		r.drawFillRect(posX, posY, width, height, 0xFFFF00FF);
		r.drawText(string, posX, posY, 0xFFFFFFFF);
//		r.drawRect(posX, posY, widhtText, height-1, 0xFF00FFFF);
		
		
		System.err.println(font.getWidths());
	}
}
