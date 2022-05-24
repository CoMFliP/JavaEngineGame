package com.comflip.game.lists.gui;

import com.comflip.engine.*;
import com.comflip.engine.gfc.Color;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Map;

public class Form extends LoaderManager implements GUI {
    private boolean isActive;

    private final StringBuilder content = new StringBuilder();
    private String value = "";
    private String text = "";

    private String messageError = "";

    private float timerKeyboard = 0;
    private float timerBlink = 0;
    private int maxSize = 1024;

    public void update(GameContainer gc, float dt) {
        Input input = gc.getInput();

        Cursor cursor = GUI.CURSOR;

        boolean axisX = Collisions.axisX(this.posX, this.width, cursor.getPosX(), 0);
        boolean axisY = Collisions.axisY(this.posY, this.height, cursor.getPosY(), 0);

        if ((axisX && axisY) && input.isButtonDown(MouseEvent.BUTTON1)) {
            this.isActive = true;

        } else if (!(axisX && axisY) && input.isButtonDown(MouseEvent.BUTTON1)) {
            this.isActive = false;
        }

        if (this.isActive) {
            timerBlink += dt * 20;
            if (timerBlink > 30) {
                timerBlink = 0;
            }

            for (Map.Entry<Integer, Character> entry : input.getMapKey().entrySet()) {
                if (input.isKeyDown(entry.getKey())) {
                    String key = "";

                    if (entry.getValue() >= 32 && entry.getValue() <= 126) {
                        key = entry.getValue().toString();
                    }

                    if ((input.isKey(KeyEvent.VK_SHIFT) && !Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
                            || (!input.isKey(KeyEvent.VK_SHIFT) && Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)))) {
                        content.append(key.toUpperCase());
                    } else if (input.isKey(KeyEvent.VK_SHIFT) && Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
                        content.append(key.toLowerCase());
                    } else if (input.isKey(KeyEvent.VK_BACK_SPACE)) {
                        try {
                            content.deleteCharAt(content.length() - 1);
                        } catch (Exception ignored) {
                            break;
                        }
                    } else {
                        content.append(key);
                    }
                }

                if (input.isKey(KeyEvent.VK_BACK_SPACE)) {
                    timerKeyboard += dt * 20;

                    if (timerKeyboard > 15) {
                        try {
                            content.deleteCharAt(content.length() - 1);
                        } catch (Exception ignored) {
                            break;
                        }
                    }
                } else {
                    timerKeyboard = 0;
                }

            }

            if (content.toString().length() > this.maxSize) {
                content.deleteCharAt(content.length() - 1);
            }

            this.value = content.toString();

        }
    }

    public void render(Renderer r) {
        r.drawFillRect(this.posX, this.posY, this.width, this.height, Color.LIGTH_GREY);

        if (this.isActive) {
            r.drawRect(this.posX, this.posY, this.width, this.height, Color.DARK_GREY);
        } else {
            r.drawRect(this.posX, this.posY, this.width, this.height, Color.GREY);
        }

        GameObject imageText = r.drawText(content.toString(), 0, 0, 0);

        if (this.tag.contains("form_password")) {
            imageText = r.drawText(content.toString().replaceAll("[\\w\\W\\d]", "*"), 0, 0, 0);
            r.drawText(content.toString().replaceAll("[\\w\\W\\d]", "*"), this.posX + 3, this.posY + ((this.height - imageText.getHeight()) / 2), Color.DARK_GREY);
        } else {
            r.drawText(content.toString(), this.posX + 3, this.posY + ((this.height - imageText.getHeight()) / 2), Color.DARK_GREY);
        }

        if (timerBlink < 15 && this.isActive) {
            r.drawRect(this.posX + (imageText.getWidth() + 4), this.posY + ((this.height - imageText.getHeight()) / 2), 0, imageText.getHeight(), Color.DARK_GREY);
        }

        if (content.length() == 0 && !this.isActive) {
            r.drawText(this.text, this.posX + 3, this.posY + ((this.height - imageText.getHeight()) / 2), Color.GREY);
        }

        r.drawText(this.messageError, this.posX + 3, this.posY + ((this.height - imageText.getHeight()) + 15), Color.BLACK);
    }

    public void clearContent() {
        this.content.delete(0, this.content.length());
        this.value = this.content.toString();
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

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    public String getValue() {
        return this.value;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public StringBuilder getContent() {
        return this.content;
    }
}
