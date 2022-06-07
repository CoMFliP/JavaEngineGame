package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.engine.net.ClientSession;
import com.comflip.engine.net.ClientSocket;
import com.comflip.engine.net.MatchSession;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.Sprites;
import com.comflip.game.lists.gui.Button;
import com.comflip.game.lists.gui.MapBoard;
import com.comflip.game.lists.sprite.Checker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends LoaderManager implements Layer {
    ArrayList<Sprites> listSprites = new ArrayList<>();

    private String canMove = "white";
    private float timer;

    private final Button buttonBack;

    public Game() {
        MapBoard mapBoard = GUI.MAP_BOARD;

        this.buttonBack = new Button();

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
        this.widthWindow = gc.getWidth();
        this.heightWindow = gc.getHeigth();

        if (MatchSession.getGuest().equals("")) {
            if (timer < 30) {
                timer += dt * 30;
            } else if (timer == 30) {
                try {
                    clientSocket.startConnection("127.0.0.1", 5555);
                    String rep = clientSocket.sendMessage("isGuest=" + MatchSession.getIdMatch());
                    clientSocket.stopConnection();

                    System.out.println(MatchSession.getIdMatch());

                    System.out.println(rep);

                    if (!ClientSocket.decodeResponse(rep).get("guestName").equals("null")){
                        MatchSession.setGuest(ClientSocket.decodeResponse(rep).get("guestName"));
                    }

                } catch (IOException ignored) {
                }
                timer = 0;
            }

            buttonBack.setWidth(widthWindow / 3);
            buttonBack.setHeight(25);

            buttonBack.setPosX(buttonBack.getWidth());
            buttonBack.setPosY(heightWindow / 10 * 9);

            buttonBack.setTag("button_back");
            buttonBack.setText("Back");

            if (buttonBack.isExecute()){
                Layer.GAME.setActive(false);
                Layer.MENU.setActive(true);

                try {
                    clientSocket.startConnection("127.0.0.1", 5555);
                    clientSocket.sendMessage("cancel-match=" + MatchSession.getIdMatch());
                    clientSocket.stopConnection();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            buttonBack.update(gc, dt);
            return;
        }

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

        for (Sprites listSprite : listSprites) {
            Checker checker = (Checker) listSprite;
            checker.render(r);

            try {
                if (canMove.equals(checker.getTag().split("_")[0])) {
                    r.drawText(MatchSession.getPlayers().get(canMove) + " (" + canMove + ")", 10, 30,
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

        if (MatchSession.getGuest().equals("")) {
            r.drawFillRect(0, 0, this.widthWindow, this.heightWindow, 0x88000000);
            GameObject text = r.drawText("Waiting for another player...", 0, 0, 0);
            r.drawText("Waiting for another player...", (this.widthWindow - text.getWidth()) / 2, this.heightWindow / 2, Color.WHITE);
            buttonBack.render(r);
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
