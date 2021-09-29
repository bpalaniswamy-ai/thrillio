package com.aie.thrillio.dao;

import com.aie.thrillio.DataStore;
import com.aie.thrillio.entity.User;

public class UserDao {
    public User[] getUsers(){
        return DataStore.getUsers();
    }
}
