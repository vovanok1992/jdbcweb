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
class MySQLDaoFactory extends DaoFactory {

    public MySQLDaoFactory() {
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoJdbc();
    }
    
}
