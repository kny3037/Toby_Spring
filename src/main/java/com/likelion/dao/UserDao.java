package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao() {
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.getConncetion();
        PreparedStatement pstmt = conn.prepareStatement
                ("insert into users(id,name,password)values(?,?,?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public User findById(String id) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.getConncetion();
        PreparedStatement pstmt = conn.prepareStatement
                ("select*from users where id = ?");

        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User
                (rs.getString("id"), rs.getNString("name"),rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.getConncetion();
        PreparedStatement pstmt = conn.prepareStatement
                ("delete from users");

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.getConncetion();
        PreparedStatement pstmt = conn.prepareStatement
                ("select count(*) from users");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        pstmt.close();
        conn.close();

        return count;
    }


    public static void main(String[] args) {

    }


}

