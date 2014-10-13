package com.bionic.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {

    private DataSource datasource;

    private static ConnectionManager conMgr = null;

    private ConnectionManager() {
        try {
            InitialContext cxt = new InitialContext();
            if (cxt == null) {
                throw new Exception("Uh oh -- no context!");
            }

            datasource = (DataSource) cxt.lookup("java:/comp/env/jdbc/TestDB");

            if (datasource == null) {
                throw new Exception("Data source not found!");
            }
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ConnectionManager getInstance() {
        if (conMgr == null) {
            conMgr = new ConnectionManager();
        }
        return conMgr;
    }

    public static Connection getConnection() {
        Connection result = null;
        try {
            result = getInstance().datasource.getConnection();
        } catch (SQLException ex) {

        }
        return result;
    }

}
