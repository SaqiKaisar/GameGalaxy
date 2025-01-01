package com.example.gamegalaxy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminPageController {
    @FXML
    StackPane stackPane;
    @FXML
    Button addGameButton;
    @FXML
    Button removeGameButton;
    @FXML
    Button modifyGameButton;
    @FXML
    Button logOutButton;
    @FXML
    Button viewGameButton;
    @FXML
    AnchorPane anchorPane;
    public void addGame(ActionEvent e) throws IOException
    {
        Stage stage = (Stage) stackPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("addGame.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void removeGame(ActionEvent e) throws IOException
    {
        Stage stage = (Stage) stackPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("removeGame.fxml"));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("removeGame.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void modifyGame(ActionEvent e) throws IOException
    {

    }

    public void viewGame(ActionEvent e) throws IOException
    {
        Stage stage = (Stage) stackPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("viewGame.fxml"));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("viewGame.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    public void enterViewGameMouse(MouseEvent mouseEvent) {
        viewGameButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitViewGameMouse(MouseEvent mouseEvent) {
        viewGameButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterAddGameMouse(MouseEvent mouseEvent) {
        addGameButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitAddGameMouse(MouseEvent mouseEvent) {
        addGameButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterModifyGamesMouse(MouseEvent mouseEvent) {
        modifyGameButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitModifyGamesMouse(MouseEvent mouseEvent) {
        modifyGameButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterRemoveGamesMouse(MouseEvent mouseEvent) {
        removeGameButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitRemoveGamesMouse(MouseEvent mouseEvent) {
        removeGameButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText("You are about to log out");
        alert.setContentText("Do you really want to log out?");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == yesButton)
        {
            Stage stage = (Stage) stackPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (result.get() == noButton) {
            alert.close();
        }
    }

    public void enterLogOutMouse(MouseEvent mouseEvent) {
        logOutButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitLogOutMouse(MouseEvent mouseEvent) {
        logOutButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }
}
