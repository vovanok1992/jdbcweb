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
        ResultSet res = null;
        List<User> resultList = new ArrayList<>();

        try {
            Connection conn = ConnectionManager.getConnection();
            Statement st = conn.createStatement();
            res = st.executeQuery("SELECT * FROM USERS");
            while (res.next()) {
                User u = new User();
                u.setId(res.getInt("ID"));
                u.setName(res.getString("NAME"));
                u.setEmail(res.getString("EMAIL"));
                u.setPassword(res.getString("PASSWORD"));
                resultList.add(u);
            }

        } catch (SQLException ex) {
            try {
                res.close();
            } catch (SQLException ex1) {
                throw new RuntimeException("Can not close RS", ex1);
            }
        } finally {
            try {
                res.close();
            } catch (SQLException ex1) {
                throw new RuntimeException("Can not close RS", ex1);
            }
        }

        return resultList;
    }

    @Override
    public User findById(int id) {
        ResultSet res = null;
        User selectedUser = null;

        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM USERS WHERE ID=?");
            st.setInt(1, id);
            res = st.executeQuery();

            if (res.next()) {
                selectedUser = new User();
                selectedUser.setId(res.getInt("ID"));
                selectedUser.setName(res.getString("NAME"));
                selectedUser.setEmail(res.getString("EMAIL"));
                selectedUser.setPassword(res.getString("PASSWORD"));
            }

        } catch (SQLException ex) {
            try {
                res.close();
            } catch (SQLException ex1) {
                throw new RuntimeException("Can not close RS", ex1);
            }
        } finally {
            try {
                res.close();
            } catch (SQLException ex1) {
                throw new RuntimeException("Can not close RS", ex1);
            }
        }

        return selectedUser;
    }

    @Override
    public int deleteById(int id) {
        ResultSet res = null;
        int rowsChanged = 0;

        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM USERS WHERE ID=?;");
            st.setInt(1, id);

            rowsChanged = st.executeUpdate();

        } catch (SQLException ex) {
            try {
                res.close();
            } catch (SQLException ex1) {
                throw new RuntimeException("Can not close RS", ex1);
            }
        } finally {
            try {
                res.close();
            } catch (SQLException ex1) {
                throw new RuntimeException("Can not close RS", ex1);
            }
        }

        return rowsChanged;
    }

    @Override
    public int persist(User u) {

        ResultSet res = null;
        int rowsChanged = 0;

        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement("INSERT INTO USERS(ID,NAME,EMAIL,PASSWORD) VALUES (?,?,?,?);");
            st.setInt(1, u.getId());
            st.setString(2, u.getName());
            st.setString(3, u.getEmail());
            st.setString(4, u.getPassword());
            rowsChanged = st.executeUpdate();

        } catch (SQLException ex) {
            try {
                res.close();
            } catch (SQLException ex1) {
                throw new RuntimeException("Can not close RS", ex1);
            }
        } finally {
            try {
                res.close();
            } catch (SQLException ex1) {
                throw new RuntimeException("Can not close RS", ex1);
            }
        }

        return rowsChanged;
    }

}
