package com.likelion.dao;

import com.likelion.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

public class UserDao {

    private DataSource dataSource;
    private JdbcContext jdbcContext;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    public void jdbcContextWithStatmentStrategy(StatmentStrategy statmentStrategy) throws ClassNotFoundException {

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

    public void add(User user) throws SQLException, ClassNotFoundException {
        jdbcContextWithStatmentStrategy(new AddAllStrategy(user));
        this.jdbcContext.workWithStatmentStrategy(new StatmentStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                PreparedStatement pstmt = conn.prepareStatement
                        ("insert into users(id,name,password)values(?,?,?)");
                return pstmt;
            }
        });
    }

    public User findById(String id) throws SQLException, ClassNotFoundException {
        /*Connection conn = connectionMaker.getConncetion();
        PreparedStatement pstmt = conn.prepareStatement
                ("select*from users where id = ?");
        pstmt.setString(1,id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User
                (rs.getString("id"), rs.getNString("name"),rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;*/
        return null;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        jdbcContextWithStatmentStrategy(new DeleteAllStrategy());
        this.jdbcContext.workWithStatmentStrategy(new StatmentStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                PreparedStatement pstmt = conn.prepareStatement
                        ("delete from users");
                return pstmt;
            }
        });
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        /*Connection conn = connectionMaker.getConncetion();
        PreparedStatement pstmt = conn.prepareStatement
                ("select count(*) from users");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        pstmt.close();
        conn.close();
*/
        return 0;
    }


    public static void main(String[] args) {

    }


}

