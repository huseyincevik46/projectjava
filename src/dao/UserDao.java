package dao;

import core.Database;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection= Database.getInstance();
    }
    // email ve passwor dolu mu boş mu kontrol
    public User findByLogin(String mail, String password){
        User user = null;

        String query = "SELECT * FROM `user` WHERE `mail`=? AND `password`=?";
      try {
          PreparedStatement  pr = this.connection.prepareStatement(query);
          // soru işaretlerinin ne olduğunu ataması.
          pr.setString(1, mail);
          pr.setString(2, password);
          ResultSet rs = pr.executeQuery();

          if(rs.next()){
              user= this.match(rs);
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      return user;
    }
 // userların hepsini liste halinde
    public ArrayList<User> findAll(){
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery("SELECT * from user");
            while(rs.next()){
                users.add(this.match(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public User match(ResultSet rs) throws SQLException { // veri tabanından sonuçları user nesnesine dönüşür
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("mail"));
        return user;
    }
}
