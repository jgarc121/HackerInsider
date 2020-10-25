package hacker.web;

import hacker.Post;
import hacker.PostType;
import hacker.data.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {

    private final PostRepository postRepo;

    @Autowired
    public DataController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping
    public String showDesignForm() {
        return "data";
    }

    /*
    @ModelAttribute(name = "post")
    public Post addPostToModel() {
        return new Post();
    }
     */

    @ModelAttribute
    public void addAttributes(Model model) {
        List<Post> posts = (List<Post>) postRepo.findAll();
        model.addAttribute("allPosts", posts);

        // model.addAttribute("design", new Post());
    }
}
