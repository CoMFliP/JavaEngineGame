package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.gui.Button;
import com.comflip.game.lists.gui.Form;

import java.util.ArrayList;
import java.util.Objects;

public class Register extends LoaderManager implements Layer {
    ArrayList<GUI> listGUI = new ArrayList<>();

    private String username;
    private String password;
    private String passwordRepeat;

    boolean buttonBackExecute = false;
    public Register() {
        this.sprite = new Sprite("/menu.png");

        Form formUsername = new Form();
        formUsername.setTag("form_username");
        listGUI.add(formUsername);

        Form formPassword = new Form();
        formPassword.setTag("form_password");
        listGUI.add(formPassword);

        Form formPasswordRepeat = new Form();
        formPasswordRepeat.setTag("form_password_repeat");
        listGUI.add(formPasswordRepeat);

        Button buttonRegister = new Button();
        buttonRegister.setTag("button_register");
        listGUI.add(buttonRegister);

        Button buttonBack = new Button();
        buttonBack.setTag("button_back");
        listGUI.add(buttonBack);
    }

    public void update(GameContainer gc, float dt) {

        this.widthWindow = gc.getWidth();
        this.heightWindow = gc.getHeigth();


        for (GUI elementGui : listGUI) {
            switch (elementGui.getTag()) {
                case "form_username" -> {
                    Form formUsername = (Form) elementGui;
                    formUsername.setWidth(widthWindow / 3);
                    formUsername.setHeight(25);

                    formUsername.setPosX(formUsername.getWidth());
                    formUsername.setPosY(heightWindow / 2 - 60);

                    formUsername.setText("Username");
                    formUsername.setMaxSize(20);

                    this.username = formUsername.getValue();

                    if (buttonBackExecute) {
                        formUsername.clearContent();
                    }
                }

                case "form_password" -> {
                    Form formPassword = (Form) elementGui;
                    formPassword.setWidth(widthWindow / 3);
                    formPassword.setHeight(25);

                    formPassword.setPosX(formPassword.getWidth());
                    formPassword.setPosY(heightWindow / 2 - 20);

                    formPassword.setText("Password");
                    formPassword.setMaxSize(20);

                    this.password = formPassword.getValue();

                    if (buttonBackExecute) {
                        formPassword.clearContent();
                    }
                }

                case "form_password_repeat" -> {
                    Form formPasswordRepeat = (Form) elementGui;
                    formPasswordRepeat.setWidth(widthWindow / 3);
                    formPasswordRepeat.setHeight(25);

                    formPasswordRepeat.setPosX(formPasswordRepeat.getWidth());
                    formPasswordRepeat.setPosY(heightWindow / 2 + 20);

                    formPasswordRepeat.setText("Repeat password");
                    formPasswordRepeat.setMaxSize(20);

                    this.passwordRepeat = formPasswordRepeat.getValue();

                    if (!Objects.equals(this.password, this.passwordRepeat)) {
                        formPasswordRepeat.setMessageError("* Is not same password!");
                    } else {
                        formPasswordRepeat.setMessageError("");
                    }

                    if (buttonBackExecute) {
                        formPasswordRepeat.clearContent();
                    }
                }

                case "button_register" -> {
                    Button buttonRegister = (Button) elementGui;
                    buttonRegister.setWidth(widthWindow / 6);
                    buttonRegister.setHeight(25);

                    buttonRegister.setPosX(buttonRegister.getWidth() * 3 + 3);
                    buttonRegister.setPosY(heightWindow / 2 + 60);

                    buttonRegister.setText("Create account");
                }

                case "button_back" -> {
                    Button buttonBack = (Button) elementGui;
                    buttonBack.setWidth(widthWindow / 6);
                    buttonBack.setHeight(25);

                    buttonBack.setPosX(buttonBack.getWidth() * 2);
                    buttonBack.setPosY(heightWindow / 2 + 60);

                    buttonBack.setText("Back");

                    buttonBackExecute = buttonBack.isExecute();
                    if (buttonBackExecute) {
                        Layer.REGISTER.setActive(false);
                        Layer.LOGIN.setActive(true);
                    }
                }
            }
            elementGui.update(gc, dt);
        }
    }

    public void render(Renderer r) {
        r.drawSprite(this.sprite, 0, 0);
        for (GUI elementGui : listGUI) {
            elementGui.render(r);
        }
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
