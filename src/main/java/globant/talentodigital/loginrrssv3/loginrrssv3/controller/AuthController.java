package globant.talentodigital.loginrrssv3.loginrrssv3.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@AllArgsConstructor
@Controller
public class AuthController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            String picture = principal.getAttribute("picture");
            String name = principal.getAttribute("name");
            model.addAttribute("email", email);
            model.addAttribute("picture", picture);
            model.addAttribute("name", name);
        }
        return "profile";
    }
}

