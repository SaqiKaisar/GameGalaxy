package com.example.gamegalaxy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class ViewGameController {
    @FXML
    Button addGameButton;
    @FXML
    Button modifyGameButton;
    @FXML
    Button removeGameButton;
    @FXML
    Button viewGameButton;
    @FXML
    Button logOutButton;
    @FXML
    Button allGamesButton;
    @FXML
    Button searchButton;
    @FXML
    Button searchGameButton;
    @FXML
    TextField searchField;
    @FXML
    StackPane stackPane;
    @FXML
    ScrollPane scrollPane;
    @FXML
    AnchorPane anchorPane;
    @FXML
    GridPane gridPane;
//    @FXML
//    VBox buttonBox;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    Label label4;
    @FXML
    Label label5;
    @FXML
    Label label6;
    @FXML
    Label label7;
    @FXML
    ImageView gameImage;


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

    public void enterLogOutMouse(MouseEvent mouseEvent) {
        logOutButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitLogOutMouse(MouseEvent mouseEvent) {
        logOutButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterAllGamesMouse(MouseEvent mouseEvent) {
        allGamesButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitAllGamesMouse(MouseEvent mouseEvent) {
        allGamesButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterSearchMouse(MouseEvent mouseEvent) {
        searchButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitSearchMouse(MouseEvent mouseEvent) {
        searchButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterSearchGameMouse(MouseEvent mouseEvent) {
        searchGameButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitSearchGameMouse(MouseEvent mouseEvent) {
        searchGameButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void viewGame(ActionEvent e) throws IOException {

    }

    public void removeGame(ActionEvent e) throws IOException {
        Stage stage = (Stage) stackPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("removeGame.fxml"));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("removeGame.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void addGame(ActionEvent e) throws IOException {
        Stage stage = (Stage) stackPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("addGame.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void modifyGame(ActionEvent e) throws IOException {
        Stage stage = (Stage) stackPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        if (result.get() == yesButton) {
            Stage stage = (Stage) stackPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (result.get() == noButton) {
            alert.close();
        }
    }

    public void showThisGame(Connection conn, String queryTitle) throws SQLException {
        gameImage.setVisible(true);
        scrollPane.setVisible(false);
        gridPane.setVisible(true);
        VBox labelBox = new VBox();
        labelBox.setAlignment(Pos.CENTER_LEFT);
        labelBox.setSpacing(20);
        labelBox.setPadding(new Insets(40, 0, 0, 75));
        PreparedStatement pstmt = conn.prepareStatement("select  * from game where title=?");
        pstmt.setString(1, queryTitle);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            label1.setText("Title: "+rs.getString(1));

            label2.setText("Synopsis: "+rs.getString(2));

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            label3.setText("Release Date: "+formatter.format(rs.getDate(3)));

            label4.setText("Platform: "+rs.getString(4));

            label5.setText("Genre: "+rs.getString(5));

            label6.setText("Developer: "+rs.getString(6));

            label7.setText("Publisher: "+rs.getString(7));
            if(rs.getBytes(8)!=null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(rs.getBytes(8));
                Image image2 = new Image(bis);
                gameImage.setImage(image2);
            }
        }
    }

    public void showAllGames(ActionEvent e) throws SQLException {
        gameImage.setVisible(false);
        scrollPane.setVisible(true);
        gridPane.setVisible(false);
        searchGameButton.setVisible(false);
        searchField.setVisible(false);
        VBox buttonBox = new VBox();
        buttonBox.setStyle("-fx-background-color: #222222;");
        buttonBox.setPrefHeight(scrollPane.getPrefHeight());
        buttonBox.setPrefWidth(scrollPane.getPrefWidth());
        buttonBox.setAlignment(Pos.TOP_LEFT);
        buttonBox.setSpacing(1);
        buttonBox.setPadding(new Insets(0, 0, 0, 0));
        PostgreConnection pc = new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn = pc.conn;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select  * from game");
        int i = 1;
        while (rs.next()) {
            String queryTitle = rs.getString(1);
            Button button = new Button(queryTitle);
            button.setStyle("-fx-background-color: #880ED4;-fx-background-radius:0;-fx-text-fill: white;-fx-font-size: 16;");
            button.setOnMouseEntered(event -> {
                button.setStyle("-fx-background-color: #51087E;-fx-background-radius:0;-fx-text-fill: white;-fx-font-size: 16");
            });

            // Set the mouse exited event handler
            button.setOnMouseExited(event -> {
                button.setStyle("-fx-background-color: #880ED4;-fx-background-radius:0;-fx-text-fill: white;-fx-font-size: 16");
            });
            button.setPrefWidth(buttonBox.getPrefWidth());
            button.setOnAction(event -> {
                try {
                    showThisGame(conn, queryTitle);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            buttonBox.getChildren().add(button);
        }
        buttonBox.setFillWidth(true);
        scrollPane.setContent(buttonBox);

    }

    public void search(ActionEvent e) {
        scrollPane.setVisible(false);
        gridPane.setVisible(false);
        searchField.setVisible(true);
        searchGameButton.setVisible(true);
    }

    public void searchGame(ActionEvent e) throws SQLException, IOException {
        PostgreConnection pc = new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn = pc.conn;
        PreparedStatement pstmt = conn.prepareStatement("select * from game where title=?");
        pstmt.setString(1, searchField.getText());
        ResultSet rs = pstmt.executeQuery();
        int rowCount=0;
        while(rs.next())
            rowCount++;
        if (rowCount==0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("Your spelling is incorrect/the game you are looking for does not exist.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                Stage stage = (Stage) stackPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("viewGame.fxml"));
                Scene scene = new Scene(root);
                String css = this.getClass().getResource("viewGame.css").toExternalForm();
                scene.getStylesheets().add(css);
                stage.setScene(scene);
                stage.show();
            } else if (result.get() == yesButton) {
                alert.close();
            }
        }else
        {
            ResultSet rs2=pstmt.executeQuery();
            while(rs2.next())
            {
                searchField.setVisible(false);
                searchGameButton.setVisible(false);
                gridPane.setVisible(true);
                label1.setText(rs2.getString(1));

                label2.setText(rs2.getString(2));

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                label3.setText(formatter.format(rs2.getDate(3)));

                label4.setText(rs2.getString(4));

                label5.setText(rs2.getString(5));

                label6.setText(rs2.getString(6));

                label7.setText(rs2.getString(7));
            }
        }
    }
}
