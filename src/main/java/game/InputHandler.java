package game;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;


public class InputHandler {
    private Set<KeyCode> keysPressed = new HashSet<>();

    public InputHandler(Scene scene){
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
    }

    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D)
        keysPressed.add(event.getCode());
    }

    private void onKeyReleased(KeyEvent event) {
        keysPressed.remove(event.getCode());
    }

    public boolean isKeyPressed(KeyCode code) {
        return keysPressed.contains(code);
    }

    public Set<KeyCode> getKeysPressed() {
        return keysPressed;
    }
}
