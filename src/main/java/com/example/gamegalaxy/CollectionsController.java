package com.example.gamegalaxy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CollectionsController implements Initializable {
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
    Button allGamesButton;
    @FXML
    Button myGamesButton;
    @FXML
    Button searchButton;
    @FXML
    Button recommendationButton;
    @FXML
    Button searchGameButton;
    @FXML
    Button filterButton;
    @FXML
    Button genreButton;
    @FXML
    Button completionTimeButton;
    @FXML
    Button ratingButton;
    @FXML
    Button completedGamesButton;
    @FXML
    Button backloggedGamesButton;
    @FXML
    Button currentButton;
    @FXML
    Button findButton;
    @FXML
    Button findButton1;
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
    Label label8;
    @FXML
    Label label9;
    @FXML
    TextField searchField;
    @FXML
    TextField lowerField;
    @FXML
    TextField upperField;
    @FXML
    Label completionLabel;
    @FXML
    Label infoLabel;
    @FXML
    Label welcomeLabel;
    @FXML
    Line line;
    @FXML
    GridPane gridPane;
    @FXML
    ScrollPane scrollPane;
    @FXML
    ScrollPane scrollPane2;
    @FXML
    Label reviewLabel;
    @FXML
    ComboBox genreBox;
    @FXML
    ImageView gameImage;
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
        PostgreConnection pc = new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn = pc.conn;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("select * from userinfo where username=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            pstmt.setString(1, controller.userName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                controller.label1.setText(rs.getString(1) + " " + rs.getString(2));
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

    public void enterLogOutMouse(MouseEvent mouseEvent) {
        logOutButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitLogOutMouse(MouseEvent mouseEvent) {
        logOutButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }


    public void enterAllGames(MouseEvent mouseEvent) {
        allGamesButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitAllGames(MouseEvent mouseEvent) {
        allGamesButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterCompletedGames(MouseEvent mouseEvent) {
        completedGamesButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitCompletedGames(MouseEvent mouseEvent) {
        completedGamesButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterBackloggedGames(MouseEvent mouseEvent) {
        backloggedGamesButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitBackloggedGames(MouseEvent mouseEvent) {
        backloggedGamesButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterCurrent(MouseEvent mouseEvent) {
        currentButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitCurrent(MouseEvent mouseEvent) {
        currentButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterMyGames(MouseEvent mouseEvent) {
        myGamesButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitMyGames(MouseEvent mouseEvent) {
        myGamesButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterSearch(MouseEvent mouseEvent) {
        searchButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitSearch(MouseEvent mouseEvent) {
        searchButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterSearch1(MouseEvent mouseEvent) {
        searchGameButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitSearch1(MouseEvent mouseEvent) {
        searchGameButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterFilter(MouseEvent mouseEvent) {
        filterButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitFilter(MouseEvent mouseEvent) {
        filterButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterGenre(MouseEvent mouseEvent) {
        genreButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitGenre(MouseEvent mouseEvent) {
        genreButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterRecommendation(MouseEvent mouseEvent) {
        recommendationButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitRecommendation(MouseEvent mouseEvent) {
        recommendationButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterCompletionTime(MouseEvent mouseEvent) {
        completionTimeButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitCompletionTime(MouseEvent mouseEvent) {
        completionTimeButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterRating(MouseEvent mouseEvent) {
        ratingButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitRating(MouseEvent mouseEvent) {
        ratingButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterFind(MouseEvent mouseEvent) {
        findButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitFind(MouseEvent mouseEvent) {
        findButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterFind1(MouseEvent mouseEvent) {
        findButton1.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitFind1(MouseEvent mouseEvent) {
        findButton1.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterShow(MouseEvent mouseEvent) {
    }

    public void exitShow(MouseEvent mouseEvent) {
    }

    public void showThisGame(Connection conn, String queryTitle) throws SQLException {
        //gameImage.setImage(null);
        gameImage.setVisible(true);
        scrollPane.setVisible(false);
        gridPane.setVisible(true);
        scrollPane2.setVisible(true);
        reviewLabel.setVisible(true);
        VBox labelBox = new VBox();
        labelBox.setAlignment(Pos.CENTER_LEFT);
        labelBox.setSpacing(20);
        labelBox.setPadding(new Insets(40, 0, 0, 75));
        PreparedStatement pstmt = conn.prepareStatement("select  * from game where title=?");
        pstmt.setString(1, queryTitle);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            label1.setText("Title: " + rs.getString(1));
            label2.setText("Synopsis: " + rs.getString(2));
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            label3.setText("Release Date: " + formatter.format(rs.getDate(3)));
            label4.setText("Platform: " + rs.getString(4));
            label5.setText("Genre: " + rs.getString(5));
            label6.setText("Developer: " + rs.getString(6));
            label7.setText("Publisher: " + rs.getString(7));
            if (rs.getBytes(8) != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(rs.getBytes(8));
                Image image2 = new Image(bis);
                gameImage.setImage(image2);
            }
            PreparedStatement pstmt2 = conn.prepareStatement("SELECT AVG(playthrough.rating) AS avg_rating FROM game JOIN playthrough ON game.title = playthrough.gameTitle where game.title=? GROUP BY game.title");
            pstmt2.setString(1, queryTitle);
            ResultSet rs2 = pstmt2.executeQuery();
            int flag = 0;
            while (rs2.next()) {
                flag++;
            }
            if (flag == 0) {
                label8.setText("Rating: Unknown");
            } else {
                rs2 = pstmt2.executeQuery();
                while (rs2.next())
                    label8.setText("Rating: " + rs2.getInt(1));
            }
            rs2 = pstmt2.executeQuery();

            PreparedStatement pstmt3 = conn.prepareStatement("select avg(playthrough.completionTime) as avg_completionTime FROM game JOIN playthrough ON game.title = playthrough.gameTitle where game.title=? and playthrough.status='Completed' GROUP BY game.title");
            pstmt3.setString(1, queryTitle);
            ResultSet rs3 = pstmt3.executeQuery();
            flag = 0;
            while (rs3.next())
                flag++;
            if (flag == 0)
                label9.setText("Completion Time: " + "Unknown");
            else {
                rs3 = pstmt3.executeQuery();
                while (rs3.next())
                    label9.setText("Completion Time: " + rs3.getInt(1));
            }
            VBox labelBox2 = new VBox();
            labelBox2.setPrefHeight(scrollPane.getPrefHeight());
            labelBox2.setPrefWidth(scrollPane.getPrefWidth());
            labelBox2.setStyle("-fx-background-color: #222222;");
            labelBox2.setAlignment(Pos.TOP_LEFT);
            labelBox2.setSpacing(1);
            labelBox2.setPadding(new Insets(0, 0, 0, 0));
            PreparedStatement pstmt4 = conn.prepareStatement("select review from playthrough where gameTitle=?");
            pstmt4.setString(1, queryTitle);
            ResultSet rs4 = pstmt4.executeQuery();
            while (rs4.next()) {
                Label label = new Label();
                label.setText(rs4.getString(1));
                label.setStyle("-fx-background-color: #880ED4;-fx-text-fill: white");
                labelBox2.getChildren().add(label);
                label.setPrefWidth(labelBox2.getPrefWidth());
                label.setAlignment(Pos.TOP_LEFT);
            }
            scrollPane2.setContent(labelBox2);
        }
    }

    public void showAllGames(ActionEvent actionEvent) throws SQLException {
        gameImage.setImage(null);
        genreBox.setVisible(false);
        findButton1.setVisible(false);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        backloggedGamesButton.setVisible(false);
        currentButton.setVisible(false);
        filterButton.setVisible(false);
        completedGamesButton.setVisible(false);
        ratingButton.setVisible(false);
        genreButton.setVisible(false);
        completionTimeButton.setVisible(false);
        scrollPane.setVisible(true);
        gridPane.setVisible(false);
        searchField.setVisible(false);
        searchGameButton.setVisible(false);
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
        filterButton.setVisible(true);
        ;
    }

    public void showCompletedGames(ActionEvent actionEvent) throws SQLException {
        gameImage.setImage(null);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        scrollPane.setVisible(true);
        gridPane.setVisible(false);
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
        PreparedStatement pstmt = conn.prepareStatement("select  game.title,game.synopsis,game,releasedate,game.platform,game.developer,game.publisher from game join playthrough on game.title=playthrough.gameTitle where playthrough.userName=? and playthrough.status=?");
        pstmt.setString(1, userName);
        pstmt.setString(2, "Completed");
        ResultSet rs = pstmt.executeQuery();
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
        completedGamesButton.setVisible(true);
        backloggedGamesButton.setVisible(true);
        currentButton.setVisible(true);
    }

    public void showBackloggedGames(ActionEvent actionEvent) throws SQLException {
        gameImage.setImage(null);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        lowerField.setVisible(false);
        upperField.setVisible(false);
        findButton.setVisible(false);
        scrollPane.setVisible(true);
        gridPane.setVisible(false);
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
        PreparedStatement pstmt = conn.prepareStatement("select  game.title,game.synopsis,game,releasedate,game.platform,game.developer,game.publisher from game join playthrough on game.title=playthrough.gameTitle where playthrough.userName=? and playthrough.status=?");
        pstmt.setString(1, userName);
        pstmt.setString(2, "Backlogged");
        ResultSet rs = pstmt.executeQuery();
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
        completedGamesButton.setVisible(true);
        backloggedGamesButton.setVisible(true);
        currentButton.setVisible(true);
    }

    public void showCurrentGames(ActionEvent actionEvent) throws SQLException {
        gameImage.setImage(null);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        scrollPane.setVisible(true);
        gridPane.setVisible(false);
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
        PreparedStatement pstmt = conn.prepareStatement("select  game.title,game.synopsis,game,releasedate,game.platform,game.developer,game.publisher from game join playthrough on game.title=playthrough.gameTitle where playthrough.userName=? and playthrough.status=?");
        pstmt.setString(1, userName);
        pstmt.setString(2, "Currently Playing");
        ResultSet rs = pstmt.executeQuery();
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
        completedGamesButton.setVisible(true);
        backloggedGamesButton.setVisible(true);
        currentButton.setVisible(true);
    }

    public void showMyGames(ActionEvent actionEvent) throws SQLException {
        searchField.clear();
        gameImage.setImage(null);
        genreBox.setVisible(false);
        findButton1.setVisible(false);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        lowerField.setVisible(false);
        upperField.setVisible(false);
        findButton.setVisible(false);
        completionTimeButton.setVisible(false);
        backloggedGamesButton.setVisible(false);
        currentButton.setVisible(false);
        filterButton.setVisible(false);
        completedGamesButton.setVisible(false);
        ratingButton.setVisible(false);
        genreButton.setVisible(false);
        scrollPane.setVisible(true);
        gridPane.setVisible(false);
        searchField.setVisible(false);
        searchGameButton.setVisible(false);
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
        PreparedStatement pstmt = conn.prepareStatement("select  game.title,game.synopsis,game,releasedate,game.platform,game.developer,game.publisher from game join playthrough on game.title=playthrough.gameTitle where playthrough.userName=? ");
        pstmt.setString(1, userName);
        ResultSet rs = pstmt.executeQuery();
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
        completedGamesButton.setVisible(true);
        backloggedGamesButton.setVisible(true);
        currentButton.setVisible(true);
    }

    public void search(ActionEvent e) {
        gameImage.setImage(null);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        scrollPane.setVisible(false);
        gridPane.setVisible(false);
        searchField.setVisible(true);
        searchGameButton.setVisible(true);
        filterButton.setVisible(false);
        completedGamesButton.setVisible(false);
        ratingButton.setVisible(false);
        genreButton.setVisible(false);
        completionTimeButton.setVisible(false);
        backloggedGamesButton.setVisible(false);
        currentButton.setVisible(false);
    }

    public void searchGame(ActionEvent e) throws SQLException, IOException {
        gameImage.setImage(null);
        PostgreConnection pc = new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn = pc.conn;
        PreparedStatement pstmt = conn.prepareStatement("select * from game where title=?");
        pstmt.setString(1, searchField.getText());
        ResultSet rs = pstmt.executeQuery();
        int rowCount = 0;
        while (rs.next())
            rowCount++;
        if (rowCount == 0) {
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
        } else {
            showThisGame(conn, searchField.getText());
        }

    }

    public void filter(ActionEvent actionEvent) {
        ratingButton.setVisible(true);
        genreButton.setVisible(true);
        completionTimeButton.setVisible(true);
    }

    public void chooseGenre(ActionEvent actionEvent) {
        gameImage.setImage(null);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        lowerField.setVisible(false);
        upperField.setVisible(false);
        findButton.setVisible(false);
        completionLabel.setVisible(false);
        genreBox.setVisible(true);
        findButton1.setVisible(true);
        gridPane.setVisible(false);
    }

    public void find1(ActionEvent actionEvent) throws SQLException {
        reviewLabel.setVisible(false);
        scrollPane2.setVisible(false);
        gameImage.setImage(null);
        scrollPane.setVisible(true);
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
        PreparedStatement pstmt = conn.prepareStatement("select  game.title,game.synopsis,game,releasedate,game.platform,game.developer,game.publisher from game where ?=any(genre)");
        pstmt.setString(1, String.valueOf(genreBox.getSelectionModel().getSelectedItem()));
        ResultSet rs = pstmt.executeQuery();
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

    public void showCompletionTime(ActionEvent actionEvent) throws SQLException {
        gameImage.setVisible(false);
        genreBox.setVisible(false);
        findButton1.setVisible(false);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        lowerField.setVisible(true);
        upperField.setVisible(true);
        completionLabel.setVisible(true);
        findButton.setVisible(true);
        gridPane.setVisible(false);
    }

    public void find(ActionEvent actionEvent) throws SQLException {
        gameImage.setVisible(true);
        scrollPane2.setVisible(false);
        gameImage.setImage(null);
        scrollPane.setVisible(true);
        VBox buttonBox = new VBox();
        buttonBox.setStyle("-fx-background-color: #222222;");
        buttonBox.setPrefHeight(scrollPane.getPrefHeight());
        buttonBox.setPrefWidth(scrollPane.getPrefWidth());
        buttonBox.setAlignment(Pos.TOP_LEFT);
        buttonBox.setSpacing(1);
        buttonBox.setPadding(new Insets(0, 0, 0, 0));
        if (!lowerField.getText().matches("\\d+") || !upperField.getText().matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Constraints");
            alert.setHeaderText("The completion time you have provided is invalid.It should be valid numbers in hours.");
            alert.setContentText("Do you want to try again?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                optionsButton.fire();
            } else if (result.get() == yesButton) {
                lowerField.clear();
                upperField.clear();
                alert.close();
            }
        } else {
            PostgreConnection pc = new PostgreConnection();
            pc.createPSQLConnection();
            Connection conn = pc.conn;
            PreparedStatement pstmt = conn.prepareStatement("SELECT game.title, game.synopsis, game.releasedate, game.platform, game.developer, game.publisher, AVG(completionTime) AS avg_cp FROM game JOIN playthrough ON game.title = playthrough.gameTitle GROUP BY  game.title having AVG(completionTime) between ? and ? ORDER BY avg_cp desc");
            pstmt.setInt(1, Integer.valueOf(lowerField.getText()));
            pstmt.setInt(2, Integer.valueOf(upperField.getText()));
            ResultSet rs = pstmt.executeQuery();
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
    }

    public void showRating(ActionEvent actionEvent) throws SQLException {
        gameImage.setImage(null);
        genreBox.setVisible(false);
        findButton1.setVisible(false);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        lowerField.setVisible(false);
        upperField.setVisible(false);
        findButton.setVisible(false);
        completionLabel.setVisible(false);
        gridPane.setVisible(false);
        scrollPane.setVisible(true);
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
        ResultSet rs = stmt.executeQuery("SELECT game.title, game.synopsis, game.releasedate, game.platform, game.developer, game.publisher, AVG(playthrough.rating) AS avg_rating FROM game JOIN playthrough ON game.title = playthrough.gameTitle GROUP BY game.title ORDER BY avg_rating desc");
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

    public void showRecommendation(ActionEvent actionEvent) throws SQLException {
        gameImage.setImage(null);
        genreBox.setVisible(false);
        findButton1.setVisible(false);
        scrollPane2.setVisible(false);
        reviewLabel.setVisible(false);
        backloggedGamesButton.setVisible(false);
        currentButton.setVisible(false);
        filterButton.setVisible(false);
        completedGamesButton.setVisible(false);
        ratingButton.setVisible(false);
        genreButton.setVisible(false);
        completionTimeButton.setVisible(false);
        scrollPane.setVisible(true);
        gridPane.setVisible(false);
        searchField.setVisible(false);
        searchGameButton.setVisible(false);
        int ratingHash = 0;
        Map<String, Integer> ratingMap = new HashMap<>();
        ratingMap.put("Action-Adventure", 0);
        ratingMap.put("Role-Playing", 1);
        ratingMap.put("Puzzlers", 2);
        ratingMap.put("Strategy", 3);
        ratingMap.put("Shooters", 4);
        ratingMap.put("Simulation", 5);
        ratingMap.put("Sandbox", 6);
        ratingMap.put("MOBA", 7);
        List<Integer> genreCount = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genreCount.add(0);
        }
        PostgreConnection pc = new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn = pc.conn;
        PreparedStatement pstmt = conn.prepareStatement("select genre from game join playthrough on game.title=playthrough.gameTitle where userName=?");
        pstmt.setString(1, userName);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Array arr = rs.getArray(1);
            Object[] objArr = (Object[]) arr.getArray();
            for (int i = 0; i < objArr.length; i++) {
                int newValue = genreCount.get(ratingMap.get(String.valueOf(objArr[i]))) + 1; // Increment the value
                genreCount.set(ratingMap.get(String.valueOf(objArr[i])), newValue);
            }
        }
        List<Pair<String, Integer>> gameGenreValue = new ArrayList<>();
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery("select title,genre from game");
        while ((rs.next())) {
            Array arr = rs.getArray(2);
            Object[] objArr = (Object[]) arr.getArray();
            int genreValue = 0;
            for (int i = 0; i < objArr.length; i++) {
                genreValue += genreCount.get(ratingMap.get(String.valueOf(objArr[i])));
            }
            gameGenreValue.add(new Pair<>(rs.getString(1), genreValue));
        }
        gameGenreValue.sort(Comparator.comparing(Pair::getValue));
        List<Pair<String, Pair<Integer, Integer>>> gameGenreRatingValue = new ArrayList<>();
        for (int i = gameGenreValue.size() - 1; i >= 0; i--) {
            pstmt = conn.prepareStatement("select gameTitle,avg(rating) from playthrough where gameTitle=? group by gameTitle");
            pstmt.setString(1, gameGenreValue.get(i).getKey());
            rs = pstmt.executeQuery();
            while ((rs.next())) {
                gameGenreRatingValue.add(new Pair<>(rs.getString(1), new Pair<>(gameGenreValue.get(i).getValue(), rs.getInt(2))));
            }
        }
        Comparator<Pair<String, Pair<Integer, Integer>>> comparator = (p1, p2) -> {
            int cmp = p1.getValue().getKey().compareTo(p2.getValue().getKey());
            if (cmp == 0) {
                cmp = p1.getValue().getValue().compareTo(p2.getValue().getValue());
            }
            return cmp;
        };
        gameGenreRatingValue.sort(comparator);
        VBox buttonBox = new VBox();
        buttonBox.setStyle("-fx-background-color: #222222;");
        buttonBox.setPrefHeight(scrollPane.getPrefHeight());
        buttonBox.setPrefWidth(scrollPane.getPrefWidth());
        buttonBox.setAlignment(Pos.TOP_LEFT);
        buttonBox.setSpacing(1);
        buttonBox.setPadding(new Insets(0, 0, 0, 0));
        buttonBox.setFillWidth(true);
        int count=1;
        for (int i = gameGenreRatingValue.size() - 1; i >= 0; i--) {
            if(count==5)
                break;
            pstmt = conn.prepareStatement("select * from playthrough where userName=? and gameTitle=?");
            pstmt.setString(1, userName);
            pstmt.setString(2, gameGenreRatingValue.get(i).getKey());
            rs = pstmt.executeQuery();
            int flag = 0;
            while (rs.next()) {
                flag++;
            }
            if (flag == 0) {
                Button button = new Button(gameGenreRatingValue.get(i).getKey());
                button.setStyle("-fx-background-color: #880ED4;-fx-background-radius:0;-fx-text-fill: white;-fx-font-size: 16;");
                button.setOnMouseEntered(event -> {
                    button.setStyle("-fx-background-color: #51087E;-fx-background-radius:0;-fx-text-fill: white;-fx-font-size: 16");
                });

                // Set the mouse exited event handler
                button.setOnMouseExited(event -> {
                    button.setStyle("-fx-background-color: #880ED4;-fx-background-radius:0;-fx-text-fill: white;-fx-font-size: 16");
                });
                button.setPrefWidth(buttonBox.getPrefWidth());
                int finalI = i;
                button.setOnAction(event -> {
                    try {
                        showThisGame(conn, gameGenreRatingValue.get(finalI).getKey());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBox.getChildren().add(button);
            }
            scrollPane.setContent(buttonBox);
        }
    }


    public void showGames(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genreBox.getItems().clear();
        genreBox.getItems().addAll("Action-Adventure", "Role-Playing", "Puzzlers", "Strategy", "Shooters", "Simulation", "Sandbox", "MOBA");
    }
}
