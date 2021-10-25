package com.Dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertDialog {
    public static void display(String title,String message){
        final Stage stage=new Stage();
        stage.setTitle(title);
       stage.setMinWidth(400);
        stage.setMaxHeight(350);

        Label label=new Label();
        label.setText(message);
        label.setStyle("-fx-font-family:'Verdana'; -fx-font-size:15");
        Button button=new Button("OK");
        button.setStyle("-fx-background-color:#e6b0ff;-fx-font-family:'Verdana';-fx-min-width:70;-fx-min-height:9;-fx-font-color:#FFFFFF;-fx-font-weight:bold;-fx-background-radius:32;");

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                stage.close();
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        VBox box=new VBox();

        box.getChildren().addAll(label,button);
        box.setAlignment(Pos.CENTER);

        Scene scene=new Scene(box,280,100);

        stage.setScene(scene);
        stage.showAndWait();

    }
}
