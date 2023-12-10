package com.example.iconnect;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
import javafx.util.Duration;

public class MyButtonSkin extends ButtonSkin {
    public MyButtonSkin(Button control) {
        super(control);
        ScaleTransition scalein = new ScaleTransition();
        scalein.setNode(control);
        scalein.setDuration(Duration.millis(100));
        scalein.setCycleCount(1);
        scalein.setInterpolator(Interpolator.EASE_IN);
        scalein.setToX(1.1f);
        scalein.setToY(1.1f);
        control.setOnMouseEntered(e -> scalein.playFromStart());
        ScaleTransition scaleout = new ScaleTransition();
        scaleout.setNode(control);
        scaleout.setDuration(Duration.millis(100));
        scaleout.setCycleCount(1);
        scaleout.setInterpolator(Interpolator.EASE_IN);
        scaleout.setToX(1);
        scaleout.setToY(1);
        control.setOnMouseExited(e -> {
            scaleout.playFromStart();
        });
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100), control);
        translateTransition.setByY(4); // Translate down by 4 pixels
        translateTransition.setAutoReverse(true); // Return to original position
        translateTransition.setCycleCount(2); // Repeat the animation twice

        control.setOnMousePressed(event -> {
            translateTransition.playFromStart(); // Start the animation when pressed
        });
    }
}