package zjk.life.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zjk.life.community.dto.AccessTokenDTO;
import zjk.life.community.dto.GithubUser;
import zjk.life.community.pojo.User;
import zjk.life.community.provider.GithubProvider;
import zjk.life.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;
    @Value("${Github.client.id}")
    private String client_id;
    @Value("${Github.client.secret}")
    private String client_secret;
    @Value("${Github.client.uri}")
    private String client_uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(client_uri);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user= githubProvider.getUser(accessToken);
        System.out.println(user.getId());
        if(user != null){
            User user1=new User();
            user1.setId(null);
            user1.setAccountId(String.valueOf(user.getId()));
            user1.setName(user.getName());
            user1.setPassword("123456");
            user1.setToken(UUID.randomUUID().toString());
            Date date=new Date();
            user1.setGmtLastLoginTime(date.getTime());
            long createtime=date.getTime();
            user1.setGmtCreate(createtime);
            user1.setGmtModified(createtime);
            userService.InsertUser(user1);
            response.addCookie(new Cookie("token",user1.getToken()));
            return "redirect:/";//redirect重定向，跳转的是url不是页面
        }
        else{
            return "redirect:/";//去寻找IndexController中的GetMapping中的/
        }


    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

}
