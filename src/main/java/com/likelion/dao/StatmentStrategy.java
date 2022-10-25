package com.likelion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatmentStrategy {
    PreparedStatement makePreparedStatement (Connection conn)throws SQLException;
}
