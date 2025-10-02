package game;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;


public class InputHandler {

    private Set<KeyCode> keysPressed;

    public InputHandler(){
        keysPressed = new HashSet<>();
    }

    public InputHandler(Scene scene) {
        this();
        attach(scene);
    }

    public void attach(Scene scene) {
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
    }

    private void onKeyPressed(KeyEvent event) {
        KeyCode code = event.getCode();
        keysPressed.add(code);
    }

    private void onKeyReleased(KeyEvent event) {
        KeyCode code = event.getCode();
        keysPressed.remove(code);
    }

    public boolean isKeyPressed(KeyCode code){
        return keysPressed.contains(code);
    }

    public void handleInput(){

    }
}
