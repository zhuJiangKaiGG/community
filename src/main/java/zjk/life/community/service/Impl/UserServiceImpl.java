package zjk.life.community.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zjk.life.community.mapper.UserMapper;
import zjk.life.community.pojo.User;
import zjk.life.community.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User selectByToken(String token) {
       return userMapper.selectByToken(token);
    }

    @Override
    public void InsertUser(User user) {
         userMapper.insert(user);
    }
}
