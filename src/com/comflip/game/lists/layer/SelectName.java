package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.gui.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectName extends LoaderManager implements Layer, GUI {

    private int widthWindow, heightWindow;
    ArrayList<GUI> listGUI = new ArrayList<>();

    private String namePlayer1;
    private String namePlayer2;

    HashMap<String, String> choosePlayer = new HashMap<>();

    public SelectName() {
        for (int i = 0; i < 3; i++) {
            Button button = new Button();
            button.setTag("button_player1_" + i);
            listGUI.add(button);
        }

        for (int i = 0; i < 3; i++) {
            Button button = new Button();
            button.setTag("button_player2_" + i);
            listGUI.add(button);
        }

        Button button = new Button();
        button.setTag("button_next");
        listGUI.add(button);
    }

    public void update(GameContainer gc, float dt) {
        widthWindow = gc.getWidth();
        heightWindow = gc.getHeigth();

        if (!listGUI.isEmpty()) {
            for (GUI elementGui : listGUI) {
                elementGui.update(gc, dt);
                if (((Button) elementGui).getTag().contains("button")) {
                    Button button = (Button) elementGui;
                    if (button.isExecute()) {
                        switch (button.getTag()) {
                            case "button_player1_0":
                                this.namePlayer1 = "Jorge";
                                break;

                            case "button_player1_1":
                                this.namePlayer1 = "Philipp";
                                break;

                            case "button_player1_2":
                                this.namePlayer1 = "Vitaly";
                                break;

                            case "button_player2_0":
                                this.namePlayer2 = "Jorge";
                                break;

                            case "button_player2_1":
                                this.namePlayer2 = "Philipp";
                                break;

                            case "button_player2_2":
                                this.namePlayer2 = "Vitaly";
                                break;

                            case "button_next":
                                choosePlayer.put("white", namePlayer1);
                                choosePlayer.put("black", namePlayer2);

                                this.isActive = false;

                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    public void render(Renderer r) {
        r.drawFillRect(0, 0, widthWindow, heightWindow, 0x88000000);

        r.drawText("Select your name", widthWindow / 2 - (r.drawText("Select your name", 0, 0, 0).getWidth() / 2), 25,
                Color.WHITE);
        r.drawText("Player1: " + namePlayer1, widthWindow / 2 - (r.drawText("Player1: " + namePlayer1, 0, 0, 0).getWidth() / 2), 45,
                Color.WHITE);

        r.drawText("Player2: " + namePlayer2, widthWindow / 2 - (r.drawText("Player2: " + namePlayer2, 0, 0, 0).getWidth() / 2), 175,
                Color.WHITE);

        for (GUI elementGui : listGUI) {
            if (((Button) elementGui).getTag().contains("button_player1")) {
                Button button = (Button) elementGui;
                button.setWidth(widthWindow / 3);
                button.setHeight(25);

                button.setPosX(button.getWidth());
                button.setPosY(65);

                switch (button.getTag()) {
                    case "button_player1_0" -> button.setText("Jorge");
                    case "button_player1_1" -> {
                        button.setText("Philipp");
                        button.setPosY(button.getPosY() + 30);
                    }
                    case "button_player1_2" -> {
                        button.setText("Vitaly");
                        button.setPosY(button.getPosY() + 60);
                    }

                    default -> {
                    }
                }
                elementGui.render(r);
            }

            if (((Button) elementGui).getTag().contains("button_player2")) {
                Button button = (Button) elementGui;
                button.setWidth(widthWindow / 3);
                button.setHeight(25);

                button.setPosX(button.getWidth());
                button.setPosY(195);

                switch (button.getTag()) {
                    case "button_player2_0" -> button.setText("Jorge");
                    case "button_player2_1" -> {
                        button.setText("Philipp");
                        button.setPosY(button.getPosY() + 30);
                    }
                    case "button_player2_2" -> {
                        button.setText("Vitaly");
                        button.setPosY(button.getPosY() + 60);
                    }
                    default -> {
                    }
                }
                elementGui.render(r);
            }

            if (((Button) elementGui).getTag().contains("button_next") && namePlayer1 != null && namePlayer2 != null) {
                Button button = (Button) elementGui;
                button.setWidth(widthWindow / 3);
                button.setHeight(25);

                button.setPosX(button.getWidth());
                button.setPosY(315);

                button.setText("Fight!");

                elementGui.render(r);
            }
        }
    }

    public HashMap<String, String> getChoosePlayer() {
        return choosePlayer;
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
