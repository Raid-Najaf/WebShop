package com.webshop.service;

import com.webshop.model.User;
import com.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private BCryptPasswordEncoder encrypt;

    @Autowired
    private UserRepository repo;

    @Override
    public void save(User user) {

        repo.save(user);
    }

    @Override
    public String enCryptedPassword(User user) {
        
        return encrypt.encode(user.getPassword());
    }

}
