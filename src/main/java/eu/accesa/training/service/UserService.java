package eu.accesa.training.service;

import eu.accesa.training.db.UserMapper;
import eu.accesa.training.model.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    public TestUser findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
