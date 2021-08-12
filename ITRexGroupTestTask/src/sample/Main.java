package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main{


    public static void main(String[] args) {
        ClassToFirstTask firstTask = new ClassToFirstTask();
        String s = "cacao and coffee success";
        System.out.println(firstTask.simplifiesEnglishText(s));
        ClassToSecondTask game = new ClassToSecondTask();
        game.printArea();
        System.out.println(game.startGame());
    }
}
