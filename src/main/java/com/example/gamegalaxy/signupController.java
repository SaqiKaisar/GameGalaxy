package com.example.gamegalaxy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signupController {
    @FXML
    TextField firstNameField;
    @FXML
    TextField secondNameField;
    @FXML
    TextField ageField;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button signupButton;
    @FXML
    Button goBackButton;
    @FXML
    Label message;
    @FXML
    AnchorPane anchorPane;
    private class NameException extends Throwable
    {}

    ;
    private class AgeException extends Throwable
    {}
    public static  String doHashing(String password) throws NoSuchAlgorithmException
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb= new StringBuilder();

            for(byte b: resultByteArray)
            {
                sb.append(String.format("%02x",b));
            }
            return  sb.toString();

        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return   "";

    }

    public void signup(ActionEvent e) throws IOException {
        PostgreConnection pc = new PostgreConnection();
        pc.createPSQLConnection();
        Connection conn = pc.conn;
        try {
            if(firstNameField.getText().length()<=3 || firstNameField.getText().length()>20 || secondNameField.getText().length()<=3 || firstNameField.getText().length()>20){
                NameException ne=new NameException();
                throw ne;
            }
            if(Integer.valueOf(ageField.getText())<=0 || Integer.valueOf(ageField.getText())>=150)
            {
                AgeException ae=new AgeException();
                throw ae;
            }
            PreparedStatement pstmt = conn.prepareStatement("insert into userinfo values(?,?,?,?,?)");
            pstmt.setString(1, firstNameField.getText());
            pstmt.setString(2, secondNameField.getText());
            pstmt.setInt(3, Integer.valueOf(ageField.getText()));
            pstmt.setString(4, usernameField.getText());
            pstmt.setString(5, doHashing(passwordField.getText()));
            pstmt.executeUpdate();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashBoard.fxml"));
            Parent root = loader.load();
            DashBoardController controller = loader.getController();
            controller.userName=usernameField.getText();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("dashBoard.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (SQLException ex) {
            System.out.println(ex);
            message.setText("There is someone else with the same username.Try again.");
            System.out.println(ex.getMessage());
        } catch (NameException ex) {
            message.setText("Firstname and secondname should be between 3 and 20 letters.");
        } catch (AgeException ex) {
            message.setText("Your age is not valid.");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void goBack(ActionEvent e) throws IOException
    {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void mouseEnter(MouseEvent m) {
        signupButton.setStyle("-fx-background-color:#51087E");
    }

    public void mouseExit(MouseEvent m) {
        signupButton.setStyle("-fx-background-color: #880ED4");
    }
    public void goBackMouseEnter(MouseEvent m) {
        goBackButton.setStyle("-fx-background-color:#51087E");
    }

    public void goBackMouseExit(MouseEvent m) {
        goBackButton.setStyle("-fx-background-color: #880ED4");
    }


}
