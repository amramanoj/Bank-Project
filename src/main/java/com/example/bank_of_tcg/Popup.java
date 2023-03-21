package com.example.bank_of_tcg;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;


public class Popup {


    public static void display(String msg) {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Box of Notification");


        Label label1 = new Label(msg);


        Button button1 = new Button("Ok");


        button1.setOnAction(e ->{
            popupwindow.close();
                }


        );


        VBox layout = new VBox(10);


        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 200);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }
}