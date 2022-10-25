package com.likelion.dao;

import com.likelion.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class UserDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString
                    ("id"),rs.getString("name"),rs.getString("password"));
            return user;
        }
    };

    public void add(User user) {
        this.jdbcTemplate.update
                ("INSERT INTO users(id, name, password)VALUES(?,?,?);",
                        user.getId(),user.getName(),user.getPassword());
    }

    public User findById(String id){
        String sql = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    public void deleteAll(){
       this.jdbcTemplate.update("delete from users");
    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject("select count(*)from users",
                Integer.class);
    }

    public List<User> getAll(){
        String sql = "select * from users";
        return this.jdbcTemplate.query(sql, rowMapper);
    }
}

