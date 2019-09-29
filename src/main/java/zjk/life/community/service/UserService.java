package zjk.life.community.service;

import zjk.life.community.pojo.User;


public interface UserService {
    void InsertUser(User user);
    User selectByToken(String token);
}
