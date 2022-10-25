package com.likelion.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    Connection getConncetion() throws ClassNotFoundException, SQLException;
}
