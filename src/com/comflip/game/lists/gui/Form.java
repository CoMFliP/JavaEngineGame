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
    private String text;

    private String messageError;

    private float timerKeyboard = 0;
    private float timerBlink = 0;


    public Form() {

    }

    public void update(GameContainer gc, float dt) {
        Input input = gc.getInput();

        Cursor cursor = GUI.CURSOR;

        boolean axisX = Collisions.axisX(this.posX, this.width, cursor.getPosX(), 0);
        boolean axisY = Collisions.axisY(this.posY, this.height, cursor.getPosY(), 0);

        if ((axisX && axisY) && input.isButtonDown(MouseEvent.BUTTON1)) {
            isActive = true;

        } else if (!(axisX && axisY) && input.isButtonDown(MouseEvent.BUTTON1)) {
            isActive = false;
        }

        if (this.isActive) {
            timerBlink += dt * 20;
            if (timerBlink > 30) {
                timerBlink = 0;
            }

            for (Map.Entry<Integer, Character> entry : input.getMapKey().entrySet()) {
                if (input.isKeyDown(entry.getKey()) && (entry.getValue() > 31 && entry.getValue() < 127)
                        || input.isKeyDown(KeyEvent.VK_SHIFT) || input.isKeyDown(KeyEvent.VK_BACK_SPACE)) {
                    if ((input.isKey(KeyEvent.VK_SHIFT) && !Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
                            || (!input.isKey(KeyEvent.VK_SHIFT) && Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)))) {
                        content.append(entry.getValue().toString().toUpperCase());
                    } else if (input.isKey(KeyEvent.VK_SHIFT) && Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
                        content.append(entry.getValue().toString().toLowerCase());
                    } else if (input.isKey(KeyEvent.VK_BACK_SPACE)) {
                        try {
                            content.deleteCharAt(content.length() - 1);
                        } catch (Exception ignored) {
                            break;
                        }
                    } else {
                        content.append(entry.getValue().toString().toLowerCase());
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
        }
    }

    public void render(Renderer r) {
        r.drawFillRect(this.posX, this.posY, this.width, this.height, Color.LIGTH_GREY);
        r.drawRect(this.posX, this.posY, this.width, this.height, Color.GREY);

        GameObject imageText = r.drawText(content.toString(), 0, 0, 0);
        r.drawText(content.toString(), this.posX + 3, this.posY + ((this.height - imageText.getHeight()) / 2), Color.DARK_GREY);

        if (timerBlink < 15 && this.isActive) {
            r.drawRect(this.posX + (imageText.getWidth() + 4), this.posY + ((this.height - imageText.getHeight()) / 2), 0, imageText.getHeight(), Color.DARK_GREY);
        }

        if (content.length() == 0 && !this.isActive) {
            r.drawText(this.text, this.posX + 3, this.posY + ((this.height - imageText.getHeight()) / 2), Color.GREY);
        }

        if (content.length() >= 15) {
            content.setLength(15);
            messageError = "* Too many characters!!! Max 15!";
            r.drawText(messageError, this.posX + 3, this.posY + ((this.height - imageText.getHeight()) + 15), Color.BLACK);
        }
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
}
