package com.example.gamegalaxy;

import javafx.geometry.Pos;

import java.sql.*;

public class DBInitialization {
    public Connection conn;
    public void createUserInfoTable()
    {
        PostgreConnection pc=new PostgreConnection();
        pc.createPSQLConnection();
        conn= pc.conn;
        try
        {
            String sql="create table userInfo(firstName varchar(20),secondName varchar(20), age int,username varchar(30),password varchar(50), primary key(username))";
            Statement stmt= conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createGameTable()
    {
        PostgreConnection pc=new PostgreConnection();
        pc.createPSQLConnection();
        conn= pc.conn;
        try
        {
            String sql="create table game(title varchar(40),tsynopsis varchar(500),releaseDate date,platform text[],genre text[],developer varchar(30),publisher varchar(30),ameimage bytea,constraint pk_game primary key(title))" ;
            Statement stmt= conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createPlaythroughTable()
    {
        PostgreConnection pc=new PostgreConnection();
        pc.createPSQLConnection();
        conn= pc.conn;
        try
        {
            String sql="create table playthrough(username varchar(30),gametitle varchar(30),rating int," +
                    "review varchar(500),completiontime int,status varchar(20)," +
                    "constraint pk_playthrough primary key(username,gametitle)," +
                    "constraint fk_playthrough_userinfo foreign key(username) references userinfo(username)," +
                    "constraint fk_playthrough_game foreign key(gametitle) references game(title))";
            Statement stmt= conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
