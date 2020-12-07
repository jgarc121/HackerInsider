package hacker.web;

import hacker.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accountInformation")
public class AccountInformationController {

    @GetMapping
    public String showEditAccount(Model model, @AuthenticationPrincipal User user) {
        return "accountInformation";
    }

    @ModelAttribute
    public void addUsersInfoToPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("userInfo", user);
    }



}
