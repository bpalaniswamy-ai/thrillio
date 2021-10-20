package com.aie.thrillio.service;

import com.aie.thrillio.constants.Gender;
import com.aie.thrillio.dao.UserDao;
import com.aie.thrillio.entity.User;

public class UserService {

    private static UserService instance = new UserService();
    private static UserDao dao = new UserDao();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance;
    }

    public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender, String userType){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setUserType(userType);

        return user;
    }

    public User[] getUsers(){
        return dao.getUsers();
    }
}
