package globant.talentodigital.loginrrssv3.loginrrssv3.controller;

import globant.talentodigital.loginrrssv3.loginrrssv3.service.serviceImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@AllArgsConstructor
@Controller
public class RegisterController {

    private final UserServiceImpl userService;

    @GetMapping("/registerForm")
    public String registerForm(){
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(String email, String password, String name, Model model){

        try{
            userService.saveUser(email, password, name);
            return "redirect:/login?registered=true";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "registration";
        }
    }
}
