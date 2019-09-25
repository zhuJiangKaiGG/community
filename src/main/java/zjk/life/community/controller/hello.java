package zjk.life.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class hello {
    @RequestMapping("/comeon")
    public String hello(@RequestParam(name="name")String name, Model model){
        model.addAttribute("name",name);
        System.out.println("zzzz");
        return "hello";
    }
}
