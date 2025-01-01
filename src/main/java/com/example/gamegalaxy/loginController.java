package com.example.gamegalaxy;

import javafx.event.*;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EventObject;

public class loginController {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;
    @FXML
    Button signupButton;
    @FXML
    Label message;
    @FXML
    AnchorPane anchorPane;

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

    public void login(ActionEvent e) throws IOException, SQLException, NoSuchAlgorithmException {
        String name = usernameField.getText();
        String password = doHashing(passwordField.getText());
        if (name.equals("admin") && passwordField.getText().equals("admin21")) {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            int flag = 0;
            PostgreConnection pc = new PostgreConnection();
            pc.createPSQLConnection();
            Connection conn= pc.conn;
            PreparedStatement pstmt= conn.prepareStatement("select * from userInfo where username=? and password=?");
            pstmt.setString(1,usernameField.getText());
            pstmt.setString(2,password);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
                flag++;
            if(flag==0)
                message.setText("Your username or password is incorrect.");
            else
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashBoard.fxml"));
                        Parent root = loader.load();
                        DashBoardController controller = loader.getController();
                        controller.userName=name;
                        Scene scene = new Scene(root);
                        String css = this.getClass().getResource("dashBoard.css").toExternalForm();
                        scene.getStylesheets().add(css);
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
            }
        }
    }

    public void signup(ActionEvent e) throws IOException {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loginMouseEnter(MouseEvent m) {
        loginButton.setStyle("-fx-background-color:#51087E");
    }

    public void loginMouseExit(MouseEvent m) {
        loginButton.setStyle("-fx-background-color: #880ED4");
    }

    public void signupMouseEnter(MouseEvent m) {
        signupButton.setStyle("-fx-background-color:#51087E");
    }

    public void signupMouseExit(MouseEvent m) {
        signupButton.setStyle("-fx-background-color: #880ED4");
    }


}
