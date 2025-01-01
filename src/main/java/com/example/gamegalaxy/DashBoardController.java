package com.example.gamegalaxy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashBoardController{
    @FXML
    StackPane stackPane;
    @FXML
    Button logOutButton;
    @FXML
    Button profileButton;
    @FXML
    Button collectionsButton;
    @FXML
    Button optionsButton;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    Label infoLabel;
    @FXML
    Label welcomeLabel;
    @FXML
    Line line;
    @FXML
    GridPane gridPane;
    public String userName;

    public void profile(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashBoard.fxml"));
        Parent root = loader.load();
        DashBoardController controller = loader.getController();
        controller.userName = userName;
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("dashBoard.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        controller.gridPane.setVisible(true);
        controller.line.setVisible(true);
        controller.infoLabel.setVisible(true);
        controller.welcomeLabel.setVisible(false);
        PostgreConnection pc=new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn=pc.conn;
        PreparedStatement pstmt= null;
        try {
            pstmt = conn.prepareStatement("select * from userinfo where username=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            pstmt.setString(1, controller.userName);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {
                controller.label1.setText(rs.getString(1)+" "+rs.getString(2));
                controller.label2.setText(String.valueOf(rs.getInt(3)));
                controller.label3.setText(rs.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void enterProfileMouse(MouseEvent mouseEvent) {
        profileButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitProfileMouse(MouseEvent mouseEvent) {
        profileButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void collectiions(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("collections.fxml"));
        Parent root = loader.load();
        CollectionsController controller = loader.getController();
        controller.userName = userName;
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("collections.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void enterCollectionsMouse(MouseEvent mouseEvent) {
        collectionsButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitCollectionsMouse(MouseEvent mouseEvent) {
        collectionsButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");

    }

    public void options(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("options.fxml"));
        Parent root = loader.load();
        OptionsController controller = loader.getController();
        controller.userName = userName;
        controller.gridPane.setVisible(false);
        controller.submitButton.setVisible(false);
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("options.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void enterOptionsMouse(MouseEvent mouseEvent) {
        optionsButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitOptionsMouse(MouseEvent mouseEvent) {
        optionsButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
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
