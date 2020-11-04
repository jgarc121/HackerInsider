package hacker.web;
import hacker.SecurityPost;
import hacker.PostType;
import hacker.data.PostRepository;
import hacker.data.PostTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/design")
public class DesignController {

    private final PostTypeRepository postTypeRepo;
    private final PostRepository postRepo;

    @Autowired
    public DesignController(PostTypeRepository postTypeRepo, PostRepository postRepo) {
        this.postTypeRepo = postTypeRepo;
        this.postRepo = postRepo;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("post") SecurityPost post, Errors errors) {
        if (errors.hasErrors())
            return "design";

        SecurityPost savedPost = postRepo.save(post);
        log.info("Processing..." + post);
        return "redirect:/posts/current";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<PostType> posts = (List<PostType>) postTypeRepo.findAll();
        model.addAttribute("allPosts", posts);

       // model.addAttribute("design", new Post());
    }

    @ModelAttribute(name = "post")
    public SecurityPost addPostToModel() {
        return new SecurityPost();
    }

}
