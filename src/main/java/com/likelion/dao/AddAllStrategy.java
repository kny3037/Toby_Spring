package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAllStrategy implements StatementStrategy{

    User user;

    public AddAllStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement
                ("INSERT INTO users(id,name,password) VALUES(?,?,?);");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        return pstmt;
    }
}
