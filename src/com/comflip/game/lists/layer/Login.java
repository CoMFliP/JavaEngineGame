package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.gui.Button;
import com.comflip.game.lists.gui.Form;

import java.util.ArrayList;

public class Login extends LoaderManager implements Layer {

    ArrayList<GUI> listGUI = new ArrayList<>();

    private String username;
    private String password;

    private boolean buttonRegisterExecute;

    public Login() {
        this.sprite = new Sprite("/menu.png");

        Form formUsername = new Form();
        formUsername.setTag("form_username");
        listGUI.add(formUsername);

        Form formPassword = new Form();
        formPassword.setTag("form_password");
        listGUI.add(formPassword);

        Button buttonLogin = new Button();
        buttonLogin.setTag("button_login");
        listGUI.add(buttonLogin);

        Button buttonRegister = new Button();
        buttonRegister.setTag("button_register");
        listGUI.add(buttonRegister);
    }

    @Override
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
                    formUsername.setPosY(heightWindow / 2 - 20);

                    formUsername.setText("Username");
                    formUsername.setMaxSize(20);

                    this.username = formUsername.getValue();

                    if (buttonRegisterExecute){
                        formUsername.clearContent();
                    }
//                    formUsername.setMessageError("* Too Many Charters! Max 15");
                }

                case "form_password" -> {
                    Form formPassword = (Form) elementGui;
                    formPassword.setWidth(widthWindow / 3);
                    formPassword.setHeight(25);

                    formPassword.setPosX(formPassword.getWidth());
                    formPassword.setPosY(heightWindow / 2 + 20);

                    formPassword.setText("Password");
                    formPassword.setMaxSize(20);

                    this.password = formPassword.getValue();

                    if (buttonRegisterExecute){
                        formPassword.clearContent();
                    }
//                    formPassword.setMessageError("* Too Many Charters! Max 15");
                }

                case "button_login" -> {
                    Button buttonLogin = (Button) elementGui;
                    buttonLogin.setWidth(widthWindow / 6);
                    buttonLogin.setHeight(25);

                    buttonLogin.setPosX(buttonLogin.getWidth() * 2);
                    buttonLogin.setPosY(heightWindow / 2 + 60);

                    buttonLogin.setText("Login");
                }

                case "button_register" -> {
                    Button buttonRegister = (Button) elementGui;
                    buttonRegister.setWidth(widthWindow / 6);
                    buttonRegister.setHeight(25);

                    buttonRegister.setPosX(buttonRegister.getWidth() * 3 + 3);
                    buttonRegister.setPosY(heightWindow / 2 + 60);

                    buttonRegister.setText("Sign Up");

                    buttonRegisterExecute = buttonRegister.isExecute();
                    if (buttonRegisterExecute){
                        Layer.LOGIN.setActive(false);
                        Layer.REGISTER.setActive(true);
                    }
                }
            }
            elementGui.update(gc, dt);
        }
    }

    @Override
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