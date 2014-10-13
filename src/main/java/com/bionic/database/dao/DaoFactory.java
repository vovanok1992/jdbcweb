/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.database.dao;

/**
 *
 * @author vovan
 */
public abstract class DaoFactory {
    
    public static final int MY_SQL=1;
    
    public abstract UserDao getUserDao();

    public static DaoFactory getDefaultDAOFactory(){
        return getDAOFactory(MY_SQL);
    }
    
    public static DaoFactory getDAOFactory(int whichFactory){
        
        switch(whichFactory){
            case MY_SQL:
                return new MySQLDaoFactory();
            default:
                return null;
        }

    }
    
}
