package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.Sprites;
import com.comflip.game.lists.gui.MapBoard;
import com.comflip.game.lists.sprite.Checker;

import java.util.ArrayList;
import java.util.HashMap;

public class Game extends LoaderManager implements Layer, Sprites {
    ArrayList<Sprites> listSprites = new ArrayList<>();

    private String canMove = "white";

    public Game() {
        MapBoard mapBoard = GUI.MAP_BOARD;

        this.sprite = new Sprite("/board.png");

        for (int i = 0; i < 20; i++) {
            Checker blackChecker = new Checker("/checker/black_normal.png");
            blackChecker.setTag("black_normal_" + i);
            listSprites.add(blackChecker);

            Checker whiteChecker = new Checker("/checker/white_normal.png");
            whiteChecker.setTag("white_normal_" + i);
            listSprites.add(whiteChecker);
        }


        for (int i = 0; i < listSprites.size(); i++) {
            Checker checker = (Checker) listSprites.get(i);

            if (i % 2 == 0) {
                if (checker.getTag().equals("black_normal_" + i / 2)) {
                    checker.setPosX(mapBoard.getPosition(i / 2)[0]);
                    checker.setPosY(mapBoard.getPosition(i / 2)[1]);
                    checker.setIdTileBoard(i / 2);
                }
            } else {
                if (checker.getTag().equals("white_normal_" + i / 2)) {
                    checker.setPosX(mapBoard.getPosition(i / 2 + 30)[0]);
                    checker.setPosY(mapBoard.getPosition(i / 2 + 30)[1]);
                    checker.setIdTileBoard(i / 2 + 30);
                }
            }
        }
    }

    @Override
    public void update(GameContainer gc, float dt) {
        for (Sprites sprites : listSprites) {
            HashMap<Integer, String> hashMustAttack = GUI.MAP_BOARD.getHashMustAttack();
            Checker checker = (Checker) sprites;
            if (canMove.equals(checker.getTag().split("_")[0])) {

                if (!hashMustAttack.isEmpty()) {
                    ArrayList<Integer> listKeys = new ArrayList<>(hashMustAttack.keySet());
                    for (int j = 0; j < listKeys.size(); j++) {

                        if (listKeys.size() == 1) {
                            String checkerTag = hashMustAttack.get(listKeys.get(0));
                            if (!canMove.equals(checkerTag.split("_")[0])
                                    && canMove.equals(checker.getTag().split("_")[0])) {
                                checker.update(gc, dt);
                            }

                            if (checker.getTag().equals(checkerTag) && canMove.equals(checkerTag.split("_")[0])) {
                                checker.update(gc, dt);
                            }
                        }

                        if (listKeys.size() > 1) {
                            String checkerTag = hashMustAttack.get(listKeys.get(j));
                            if (checker.getTag().equals(checkerTag) && canMove.equals(checkerTag.split("_")[0])) {
                                checker.update(gc, dt);
                            }
                        }
                    }

                } else {
                    checker.update(gc, dt);
                }
            }
        }
        GUI.MAP_BOARD.update(gc, dt);
        MapBoard mapBoard = GUI.MAP_BOARD;
        mapBoard.setCheckerList(listSprites);
    }

    @Override
    public void render(Renderer r) {
        r.drawSprite(sprite, 0, 0);
        GUI.MAP_BOARD.render(r);

        if (!listSprites.isEmpty()) {
            for (Sprites listSprite : listSprites) {
                Checker checker = (Checker) listSprite;
                checker.render(r);

                try {
                    if (canMove.equals(checker.getTag().split("_")[0])) {
                        r.drawText(Layer.SELECT_NAME.getChoosePlayer().get(canMove) + " (" + canMove + ")", 10, 30,
                                Color.WHITE);
                    }
                } catch (Exception ignored) {
                }

            }

            for (Sprites listSprite : listSprites) {
                Checker checker = (Checker) listSprite;
                if (checker.isPickUp()) {
                    checker.render(r);
                }
            }
        }
    }

    public void setCanMove(String canMove) {
        this.canMove = canMove;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
