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
import javafx.scene.input.MouseEvent;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {
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
    Label infoLabel;
    @FXML
    Line line;
    @FXML
    Button addPlaythroughButton;
    @FXML
    Button submitButton;
    @FXML
    Button modifyPlaythroughButton;
    @FXML
    Button modifyPlayTimeButton;
    @FXML
    Button modifyStatusButton;
    @FXML
    TextField gameTitleField;
    @FXML
    TextField ratingField;
    @FXML
    TextField reviewField;
    @FXML
    TextField modifyPlayTimeField;
    @FXML
    TextField completionTimeField;
    @FXML
    GridPane gridPane;
    @FXML
    ComboBox statusBox;
    @FXML
    ComboBox modifyStatusBox;
    @FXML
    TextField modifyTitleField;
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

    public void enterAddPlaythrough(MouseEvent mouseEvent) {
        addPlaythroughButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitAddPlaythrough(MouseEvent mouseEvent) {
        addPlaythroughButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterSubmit(MouseEvent mouseEvent) {
        submitButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitSubmit(MouseEvent mouseEvent) {
        submitButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterModifyPlaythrough(MouseEvent mouseEvent) {
        modifyPlaythroughButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitModifyPlaythrough(MouseEvent mouseEvent) {
        modifyPlaythroughButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterModifyPlayTime(MouseEvent mouseEvent) {
        modifyPlayTimeButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitModifyPlayTime(MouseEvent mouseEvent) {
        modifyPlayTimeButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterModifyStatus(MouseEvent mouseEvent) {
        modifyStatusButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitModifyStatus(MouseEvent mouseEvent) {
        modifyStatusButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void addPlaythrough(ActionEvent e) {
        gridPane.setVisible(true);
        submitButton.setVisible(true);
        statusBox.setVisible(true);
        modifyStatusButton.setVisible(false);
        modifyPlayTimeButton.setVisible(false);
        modifyStatusBox.setVisible(false);
        modifyTitleField.setVisible(false);
        modifyPlayTimeField.setVisible(false);
    }

    public void submit(ActionEvent e) throws SQLException, IOException {
        int flag = 0;
        PostgreConnection pc = new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn = pc.conn;
        PreparedStatement pstmt = conn.prepareStatement("select * from game where title =?");
        pstmt.setString(1, gameTitleField.getText());
        ResultSet rs = pstmt.executeQuery();
        PreparedStatement pstmt2 = conn.prepareStatement("select * from playthrough where gameTitle =? and userName=?");
        pstmt2.setString(1, gameTitleField.getText());
        pstmt2.setString(2, userName);
        ResultSet rs2 = pstmt2.executeQuery();
        int count = 0;
        while (rs.next()) {
            count++;
        }
        int count2 = 0;
        while (rs2.next()) {
            count2++;
        }
        if (count2 > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Already added");
            alert.setHeaderText("The game you are trying to add has already been added.");
            alert.setContentText("Do you want to add another game?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                optionsButton.fire();
            } else if (result.get() == yesButton) {
                gameTitleField.clear();
                ratingField.clear();
                reviewField.clear();
                completionTimeField.clear();
                alert.close();

            }
        } else if (count == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("The game you are looking for is not in the database.");
            alert.setContentText("Do you want to try again?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                optionsButton.fire();
            } else if (result.get() == yesButton) {
                gameTitleField.clear();
                ratingField.clear();
                reviewField.clear();
                completionTimeField.clear();
                alert.close();

            }
        } else if (!ratingField.getText().matches("\\d+") || !(Integer.valueOf(ratingField.getText()) >= 0 && Integer.valueOf(ratingField.getText()) <= 10)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Rating");
            alert.setHeaderText("The rating you have provided is invalid.It should be between 0 to 10.");
            alert.setContentText("Do you want to try again?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                optionsButton.fire();
            } else if (result.get() == yesButton) {
                gameTitleField.clear();
                ratingField.clear();
                reviewField.clear();
                completionTimeField.clear();
                alert.close();
            }
        } else {
            if (!completionTimeField.getText().matches("\\d+")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Invalid Completion Time");
                alert.setHeaderText("The completion time you have provided is invalid.It should be valid numbers in hours.");
                alert.setContentText("Do you want to try again?");
                ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(yesButton, noButton);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == noButton) {
                    optionsButton.fire();
                } else if (result.get() == yesButton) {
                    gameTitleField.clear();
                    ratingField.clear();
                    reviewField.clear();
                    completionTimeField.clear();
                    alert.close();
                }
            } else {
                pstmt = conn.prepareStatement("insert into playthrough  values (?,?,?,?,?,?);");
                pstmt.setString(1, userName);
                pstmt.setString(2, gameTitleField.getText());
                pstmt.setInt(3, Integer.valueOf(ratingField.getText()));
                pstmt.setString(4, reviewField.getText());
                pstmt.setInt(5, Integer.valueOf(completionTimeField.getText()));
                pstmt.setString(6, String.valueOf(statusBox.getSelectionModel().getSelectedItem()));
                pstmt.executeUpdate();
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Playthrough added");
            alert.setHeaderText("You have successfully added your info about your playthrough.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                optionsButton.fire();
            } else if (result.get() == yesButton) {
                gameTitleField.clear();
                ratingField.clear();
                reviewField.clear();
                completionTimeField.clear();
                alert.close();
            }
        }
    }

    public void modifyPlaythrough(ActionEvent actionEvent) {
        modifyStatusButton.setVisible(true);
        modifyPlayTimeButton.setVisible(true);
        modifyStatusBox.setVisible(true);
        modifyTitleField.setVisible(true);
        modifyPlayTimeField.setVisible(true);
        gridPane.setVisible(false);
        submitButton.setVisible(false);
        statusBox.setVisible(false);
    }

    public void modifyPlayTime(ActionEvent actionEvent) throws SQLException {
        PostgreConnection pc = new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn = pc.conn;
        PreparedStatement pstmt = conn.prepareStatement("select title from game join playthrough on game.title=playthrough.gametitle where title =? and username=?");
        pstmt.setString(1, modifyTitleField.getText());
        pstmt.setString(2,userName);
        ResultSet rs = pstmt.executeQuery();
        int flag = 0;
        while (rs.next())
            flag++;
        if (flag == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("The game you are looking for is not in the database.");
            alert.setContentText("Do you want to try again?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            modifyTitleField.clear();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                optionsButton.fire();
            } else if (result.get() == yesButton) {
                alert.close();

            }
        } else if (modifyPlayTimeField.getText().equals("") || completionTimeField.getText().matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("You have provided invalid play time.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            modifyPlayTimeField.clear();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                optionsButton.fire();
            } else if (result.get() == yesButton) {
                modifyPlayTimeField.clear();
                alert.close();
            }
        } else {
            pstmt = conn.prepareStatement("update playthrough set completionTime=? where gametitle=?");
            pstmt.setInt(1, Integer.valueOf(modifyPlayTimeField.getText()));
            pstmt.setString(2, modifyTitleField.getText());
            int rowcount = pstmt.executeUpdate();
            if (rowcount > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification Successful");
                alert.setHeaderText("You have successfully modified playtime.");
                alert.setContentText("Do you want to continue?");
                ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(yesButton, noButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == noButton) {
                    optionsButton.fire();
                } else if (result.get() == yesButton) {
                    alert.close();
                }
            }
        }
    }

    public void modifyStatus(ActionEvent actionEvent) throws SQLException {
        PostgreConnection pc = new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn = pc.conn;
        PreparedStatement pstmt = conn.prepareStatement("select title from game where title =?");
        pstmt.setString(1, modifyTitleField.getText());
        ResultSet rs = pstmt.executeQuery();
        int flag = 0;
        while (rs.next())
            flag++;
        if (flag == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("NOT FOUND");
            alert.setHeaderText("The game you are looking for is not in the database.");
            alert.setContentText("Do you want to try again?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            modifyTitleField.clear();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                optionsButton.fire();
            } else if (result.get() == yesButton) {
                alert.close();

            }
        } else if (modifyStatusBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("You have provided invalid status.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == noButton) {
                optionsButton.fire();
            } else if (result.get() == yesButton) {
                alert.close();
            }
        } else {
            pstmt = conn.prepareStatement("update playthrough set status=? where gametitle=?");
            pstmt.setString(1, String.valueOf(modifyStatusBox.getSelectionModel().getSelectedItem()));
            pstmt.setString(2, modifyTitleField.getText());
            int rowcount = pstmt.executeUpdate();
            if (rowcount > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification Successful");
                alert.setHeaderText("You have successfully modified status.");
                alert.setContentText("Do you want to continue?");
                ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(yesButton, noButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == noButton) {
                    optionsButton.fire();
                } else if (result.get() == yesButton) {
                    alert.close();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Completed", "Backlogged", "Currently Playing");
        statusBox.getItems().clear();
        statusBox.getItems().addAll("Completed", "Backlogged", "Currently Playing");
        modifyStatusBox.getItems().clear();
        modifyStatusBox.getItems().addAll("Completed", "Backlogged", "Currently Playing");
    }

}

