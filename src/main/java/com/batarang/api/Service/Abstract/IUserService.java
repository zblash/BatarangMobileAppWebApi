package com.batarang.api.Service.Abstract;

import com.batarang.api.Model.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findByUserName(String userName);

    User findById(Long userId);

    User Add(User user);

    void Delete(User user);

    User Update(User user, User updatedUser);

}
