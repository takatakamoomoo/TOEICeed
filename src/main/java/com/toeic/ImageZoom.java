package com.toeic;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ImageZoom extends Application {
    private final static double MIN_SCALE = 0.2;
    private final static double MAX_SCALE = 2.0;

    private double scale = 1.0;

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(600, 600);

        ImageView view = new ImageView(new Image(getClass().getResourceAsStream("/redBook_7072.png")));
        root.getChildren().add(view);

        view.setOnScroll(e -> {
            scale += e.getDeltaY()/1_000;
            if (scale <= MIN_SCALE) {
                scale = MIN_SCALE;
            } else if (scale >= MAX_SCALE) {
                scale = MAX_SCALE;
            }

            view.setScaleX(scale);
            view.setScaleY(scale);
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
