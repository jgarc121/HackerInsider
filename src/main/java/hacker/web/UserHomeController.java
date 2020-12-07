package hacker.web;


import hacker.SecurityPost;
import hacker.data.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userHome")
public class UserHomeController {

    private final PostRepository postRepository;

    @Autowired
    public UserHomeController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        addAllUserPostsToPage(model);
        return "userHome";
    }

    private void addAllUserPostsToPage(Model model) {
        List<SecurityPost> posts = (List<SecurityPost>) postRepository.findAll();
        model.addAttribute("allPostsForUser", posts);
    }


}
