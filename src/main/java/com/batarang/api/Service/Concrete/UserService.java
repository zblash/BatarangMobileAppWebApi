package com.batarang.api.Service.Concrete;

import com.batarang.api.Model.User;
import com.batarang.api.Repository.UserRepository;
import com.batarang.api.Service.Abstract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(RuntimeException::new);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    @Override
    public User Add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void Delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User Update(User user, User updatedUser) {
        return null;
    }

}
