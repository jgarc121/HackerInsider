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
@RequestMapping("/recentBreachesPost")
public class RecentBreachesPostController {

    private PostRepository postRepository;

    @Autowired
    public RecentBreachesPostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        addAllUserPostsToPage(model);
        return "recentBreachesPost";
    }

    private void addAllUserPostsToPage(Model model) {
        List<SecurityPost> posts = (List<SecurityPost>) postRepository.findByPosttype("RB");
        model.addAttribute("allPostsForUser", posts);
    }

}
