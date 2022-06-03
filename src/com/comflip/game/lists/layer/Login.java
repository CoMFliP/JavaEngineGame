package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.engine.net.ClientSession;
import com.comflip.engine.net.ClientSocket;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.gui.Button;
import com.comflip.game.lists.gui.Form;

import java.io.IOException;
import java.util.ArrayList;

public class Login extends LoaderManager implements Layer {

    ArrayList<GUI> listGUI = new ArrayList<>();

    private String username = "";
    private String password = "";

    private boolean buttonRegisterExecute;
    private String message = "";

    private float timer = 0;

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

        if (timer > 100) {
            message = "";
        } else {
            timer += dt * 20;
        }

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

                    if (buttonRegisterExecute) {
                        formUsername.clearContent();
                    }
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

                    if (buttonRegisterExecute) {
                        formPassword.clearContent();
                    }
                }

                case "button_login" -> {
                    Button buttonLogin = (Button) elementGui;
                    buttonLogin.setWidth(widthWindow / 6);
                    buttonLogin.setHeight(25);

                    buttonLogin.setPosX(buttonLogin.getWidth() * 2);
                    buttonLogin.setPosY(heightWindow / 2 + 60);

                    buttonLogin.setText("Login");

                    buttonLogin.setBlock(this.username.length() == 0 || this.password.length() == 0);

                    if (buttonLogin.isExecute()) {
                        this.timer = 0;

                        try {
                            clientSocket.startConnection("127.0.0.1", 5555);
                            String rep = clientSocket.sendMessage("login-account=" + this.username + ":" + this.password);
                            message = ClientSocket.decodeResponse(rep).get("msg");

                            String session = ClientSocket.decodeResponse(rep).get("data");
                            ClientSession.setSession(session);

                            if (ClientSocket.decodeResponse(rep).get("isAuth").equals("true")) {
                                Layer.LOGIN.setActive(false);
                                Layer.MENU.setActive(true);
                                message = "";
                            }

                            clientSocket.stopConnection();
                        } catch (IOException e) {
                            message = "Unable to connect to server. Please try again later";
                        }
                    }
                }

                case "button_register" -> {
                    Button buttonSignUp = (Button) elementGui;
                    buttonSignUp.setWidth(widthWindow / 6);
                    buttonSignUp.setHeight(25);

                    buttonSignUp.setPosX(buttonSignUp.getWidth() * 3 + 3);
                    buttonSignUp.setPosY(heightWindow / 2 + 60);

                    buttonSignUp.setText("Sign Up");

                    buttonRegisterExecute = buttonSignUp.isExecute();
                    if (buttonRegisterExecute) {
                        Layer.LOGIN.setActive(false);
                        Layer.SIGN_IN.setActive(true);
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

        GameObject repText = r.drawText(message, 0, 0, 0);
        r.drawText(message, (widthWindow - repText.getWidth()) / 2, heightWindow / 5 * 4, Color.WHITE);
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
