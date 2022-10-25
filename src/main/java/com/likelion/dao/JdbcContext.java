package com.likelion.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatmentStrategy(StatmentStrategy statmentStrategy){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = dataSource.getConnection();
            pstmt = statmentStrategy.makePreparedStatement(conn);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new Error(e.getMessage());
        }finally {
            if(pstmt != null){
                try {
                    pstmt.close();
                }catch (SQLException e){
                }
            }
            if (conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                }
            }
        }
    }

}
