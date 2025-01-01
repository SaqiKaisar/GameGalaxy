package com.example.gamegalaxy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.io.File;

public class AddGameController {
    @FXML
    TextField titleField;
    @FXML
    TextField synopsisField;
    @FXML
    DatePicker releaseDateField;
    @FXML
    TextField platformField;
    @FXML
    CheckBox actionCB;
    @FXML
    CheckBox rpgCB;
    @FXML
    CheckBox puzzlerCB;
    @FXML
    CheckBox strategyCB;
    @FXML
    CheckBox shootersCB;
    @FXML
    CheckBox simulationCB;
    @FXML
    CheckBox sandboxCB;
    @FXML
    CheckBox mobaCB;
    @FXML
    TextField developerField;
    @FXML
    TextField publisherField;
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
    Button addGameButton1;
    @FXML
    Button addImageButton;
    @FXML
    StackPane stackPane;
    @FXML
    ImageView previewImageView;

    private File imageFile;
    private String gameTitle;
    private byte[] byteArray;

    public void addGame(ActionEvent actionEvent) throws IOException {
        if(titleField.getText().equals("") || titleField.getText().length()>30 || titleField.getText().length()<5)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Title.");
            alert.setHeaderText("Title needs to be between 5 to 30 letters.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                titleField.clear();
                alert.close();
            } else if (result.get() == noButton) {
                Stage stage = (Stage) stackPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(synopsisField.getText().equals("") || synopsisField.getText().length()>500 || synopsisField.getText().length()<10)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Synopsis.");
            alert.setHeaderText("Synopsis needs to be between 10 to 500 letters.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                synopsisField.clear();
                alert.close();
            } else if (result.get() == noButton) {
                Stage stage = (Stage) stackPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(releaseDateField.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Date.");
            alert.setHeaderText("You need to choose a proper date.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                alert.close();
            } else if (result.get() == noButton) {
                Stage stage = (Stage) stackPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(platformField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid platfrom.");
            alert.setHeaderText("Provide proper information on platform.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                platformField.clear();
                alert.close();
            } else if (result.get() == noButton) {
                Stage stage = (Stage) stackPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(!actionCB.isSelected() && !rpgCB.isSelected() && !puzzlerCB.isSelected() && !strategyCB.isSelected() && !shootersCB.isSelected() && !simulationCB.isSelected() && !sandboxCB.isSelected() && !mobaCB.isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Genre.");
            alert.setHeaderText("Choose atleast one of the genre.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                alert.close();
            } else if (result.get() == noButton) {
                Stage stage = (Stage) stackPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(developerField.getText().equals("") || developerField.getText().length()>20 || developerField.getText().length()<3)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Developer Info.");
            alert.setHeaderText("Developer Name needs to be between 3 to 20 letters.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                developerField.clear();
                alert.close();
            } else if (result.get() == noButton) {
                Stage stage = (Stage) stackPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(publisherField.getText().equals("") || publisherField.getText().length()>20 || publisherField.getText().length()<5)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid Publisher Name.");
            alert.setHeaderText("Publisher name needs to be between 5 to 20 letters.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                publisherField.clear();
                alert.close();
            } else if (result.get() == noButton) {
                Stage stage = (Stage) stackPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(byteArray==null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("No image selected.");
            alert.setHeaderText("Select an image.");
            alert.setContentText("Do you want to continue?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                alert.close();
            } else if (result.get() == noButton) {
                Stage stage = (Stage) stackPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else {
            gameTitle = titleField.getText();
            PostgreConnection pc = new PostgreConnection();
            pc.createPSQLConnection();
            Connection conn = pc.conn;
            try {
                PreparedStatement pstmt = conn.prepareStatement("insert into game values(?,?,?,?,?,?,?,?)");
                pstmt.setString(1, titleField.getText());
                pstmt.setString(2, synopsisField.getText());
                pstmt.setDate(3, Date.valueOf(releaseDateField.getValue()));
                String str = platformField.getText();
                String[] platforms = str.split(",");
                pstmt.setArray(4, conn.createArrayOf("text", platforms));
                String[] temp = new String[8];
                int i = 0;
                if (actionCB.isSelected())
                    temp[i++] = actionCB.getText();
                if (rpgCB.isSelected())
                    temp[i++] = rpgCB.getText();
                if (puzzlerCB.isSelected())
                    temp[i++] = puzzlerCB.getText();
                if (strategyCB.isSelected())
                    temp[i++] = strategyCB.getText();
                if (shootersCB.isSelected())
                    temp[i++] = shootersCB.getText();
                if (simulationCB.isSelected())
                    temp[i++] = simulationCB.getText();
                if (sandboxCB.isSelected())
                    temp[i++] = sandboxCB.getText();
                if (mobaCB.isSelected())
                    temp[i++] = mobaCB.getText();
                String[] genre = new String[i];
                for (int j = 0; j < i; j++)
                    genre[j] = temp[j];
                pstmt.setArray(5, conn.createArrayOf("text", genre));
                pstmt.setString(6, developerField.getText());
                pstmt.setString(7, publisherField.getText());
                pstmt.setBytes(8, byteArray);
                pstmt.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Game Added.");
                alert.setHeaderText("You have successfully added a game.");
                alert.setContentText("Do you want to continue?");
                ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(yesButton, noButton);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == yesButton) {
                    Stage stage = (Stage) stackPane.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("addGame.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else if (result.get() == noButton) {
                    Stage stage = (Stage) stackPane.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

    public void enterLogOutMouse(MouseEvent mouseEvent) {
        logOutButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitLogOutMouse(MouseEvent mouseEvent) {
        logOutButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void enterAddImage(MouseEvent mouseEvent) {
        addImageButton.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitAddImage(MouseEvent mouseEvent) {
        addImageButton.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void viewGame(ActionEvent e) throws IOException {
        Stage stage = (Stage) stackPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("viewGame.fxml"));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("viewGame.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
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


    public void enterAddGame(MouseEvent mouseEvent) {
        addGameButton1.setStyle("-fx-background-color: #51087E;-fx-background-radius: 12");
    }

    public void exitAddGame(MouseEvent mouseEvent) {
        addGameButton1.setStyle("-fx-background-color: #880ED4;-fx-background-radius: 12");
    }

    public void addImage(ActionEvent event) {

        // Taking pic as input from local pc by Browse option:

        FileChooser fc = new FileChooser();
        fc.setTitle("Choose an image");


        // Function type to take different extension of pictures as input:
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png")
        );


        File file = fc.showOpenDialog(null);
        imageFile = file;

        // PIC to Byte Array Conversion:
        try {
            // Load the image file
            BufferedImage image = ImageIO.read(file);

            // Create a byte array output stream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Write the image to the byte array output stream
            ImageIO.write(image, "png", baos);

            // Get the byte array
            byteArray = baos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
            Image image2 = new Image(bis );
            previewImageView.setImage(image2);
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewGame() {
        ;
    }
}
