package com.bionic.database.dao;

import com.bionic.database.ConnectionManager;
import com.bionic.database.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vovan
 */
public class UserDaoJdbc implements UserDao {

    @Override
    public List<User> findAll() {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        
        List<User> resultList = new ArrayList<>();

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM USERS");
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("ID"));
                u.setName(rs.getString("NAME"));
                u.setEmail(rs.getString("EMAIL"));
                u.setPassword(rs.getString("PASSWORD"));
                resultList.add(u);
            }

        } catch (SQLException ex) {
            //error
            throw new RuntimeException("Database exception", ex);
            
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        return resultList;
    }

    @Override
    public User findById(int id) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        
        User selectedUser = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM USERS WHERE ID=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                selectedUser = new User();
                selectedUser.setId(rs.getInt("ID"));
                selectedUser.setName(rs.getString("NAME"));
                selectedUser.setEmail(rs.getString("EMAIL"));
                selectedUser.setPassword(rs.getString("PASSWORD"));
            }

        } catch (SQLException ex) {
            //error
            throw new RuntimeException("Database exception", ex);
            
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        return selectedUser;
    }

    @Override
    public int deleteById(int id) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        
        int rowsChanged = 0;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement("DELETE FROM USERS WHERE ID=?;");
            stmt.setInt(1, id);

            rowsChanged = stmt.executeUpdate();

        } catch (SQLException ex) {
            //error
            throw new RuntimeException("Database exception", ex);
            
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }

        return rowsChanged;
    }

    @Override
    public int persist(User u) {

        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        
        int rowsChanged = 0;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement("INSERT INTO USERS(ID,NAME,EMAIL,PASSWORD) VALUES (?,?,?,?);");
            stmt.setInt(1, u.getId());
            stmt.setString(2, u.getName());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPassword());
            rowsChanged = stmt.executeUpdate();

        } catch (SQLException ex) {
            //error
            throw new RuntimeException("Database exception", ex);
            
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }


        return rowsChanged;
    }

}
