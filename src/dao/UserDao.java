package dao;

import core.Database;

import java.sql.Connection;

public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection= Database.getInstance();
    }

}
