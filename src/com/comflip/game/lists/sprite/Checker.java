package com.comflip.game.lists.sprite;

import com.comflip.engine.Collisions;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.audio.SoundClip;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.SFX;
import com.comflip.game.lists.Sprites;
import com.comflip.game.lists.gui.Cursor;

import java.awt.event.MouseEvent;

public class Checker extends LoaderManager implements Sprites, SFX {
    private final Sprite spriteSuper;
    private final SoundClip pickUpSFX;

    private final SoundClip deadSFX;
    private boolean pickUp;

    private int idTileBoard;

    public Checker(String path) {
        this.sprite = new Sprite(path);

        this.pickUpSFX = SFX.PICK_UP_CHECKER;
        this.deadSFX = SFX.KILL_CHECKER;

        if (path.contains("white")) {
            this.spriteSuper = new Sprite("/checker/white_super.png");
        } else {
            this.spriteSuper = new Sprite("/checker/black_super.png");
        }
    }

    public void update(GameContainer gc, float dt) {
        this.width = this.sprite.getWidth();
        this.height = this.sprite.getHeight();

        Input input = gc.getInput();
        Cursor cursor = GUI.CURSOR;

        boolean axisX = Collisions.axisX(this.posX, this.width, cursor.getPosX(), 0);
        boolean axisY = Collisions.axisY(this.posY, this.height, cursor.getPosY(), 0);

        if (axisX && axisY) {
            if (input.isButtonDown(MouseEvent.BUTTON1)) {
                this.pickUp = true;
            } else if (input.isButtonUp(MouseEvent.BUTTON1)) {
                this.pickUp = false;
            }
        }

        if (this.pickUp) {
            this.posX = cursor.getPosX() - (this.width / 2);
            this.posY = cursor.getPosY() - (this.height / 2);
        }

        if (this.tag.split("_")[1].equals("super")) {
            this.sprite = this.spriteSuper;
        }
    }

    public void render(Renderer r) {
        if (this.pickUp) {
            r.drawSprite(this.sprite, this.posX, this.posY).setScale(1.2f);
        } else {
            r.drawSprite(this.sprite, this.posX, this.posY).setScale(1f);
        }
    }

    public int getIdTileBoard() {
        return this.idTileBoard;
    }

    public void setIdTileBoard(int idTileBoard) {
        this.idTileBoard = idTileBoard;
    }

    public boolean isPickUp() {
        return this.pickUp;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public int getHeight() {
        return this.height;
    }

    public void pickUpSFX() {
        if (!pickUpSFX.isRunning()){
            pickUpSFX.play();
        }
    }

    public void deadSFX() {
        if (!deadSFX.isRunning()){
            deadSFX.play();
        }
    }
}
