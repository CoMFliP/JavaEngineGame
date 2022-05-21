package com.comflip.game.lists.gui;

import com.comflip.engine.*;
import com.comflip.engine.gfc.Color;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;

import java.awt.event.MouseEvent;

public class Button extends LoaderManager implements GUI {
    private String text = "";

    private boolean isActive = false;
    private boolean isExecute = false;

    @Override
    public void update(GameContainer gameContainer, float dt) {
        Input input = gameContainer.getInput();
        Cursor cursor = GUI.CURSOR;

        boolean axisX = Collisions.axisX(this.posX, this.width, cursor.getPosX(), 0);
        boolean axisY = Collisions.axisY(this.posY, this.height, cursor.getPosY(), 0);

        if (axisX && axisY) {
            if (input.isButtonDown(MouseEvent.BUTTON1)) {
                isActive = true;
                isExecute = false;
            } else if (input.isButtonUp(MouseEvent.BUTTON1)) {
                isActive = false;
                isExecute = true;
            }
        } else {
            isExecute = false;
            isActive = false;
        }
    }

    @Override
    public void render(Renderer r) {
        GameObject imageText = r.drawText(text, 0, 0, 0);

        if (isActive) {
            r.drawFillRect(posX, posY, width, height, Color.DARK_GREY);
            r.drawRect(posX, posY, width, height, Color.BLACK);
        } else {
            r.drawFillRect(posX, posY, width, height, Color.GREY);
            r.drawRect(posX, posY, width, height, Color.DARK_GREY);
        }

        r.drawText(text, posX + ((width - imageText.getWidth()) / 2), posY + ((height - imageText.getHeight()) / 2), Color.WHITE);
    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isExecute() {
        return this.isExecute;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return this.text;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setExecute(boolean execute) {
        this.isExecute = execute;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() { return this.width; }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
