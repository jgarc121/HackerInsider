package hacker.web;
import hacker.SecurityPost;
import hacker.PostType;
import hacker.User;
import hacker.data.PostRepository;
import hacker.data.PostTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    public String showDesignForm(Model model, @AuthenticationPrincipal User user) {
        addUserInfoToModel(model, user);
        return "design";
    }

    private void addUserInfoToModel(Model model, User user) {
        model.addAttribute("fullName", user.getFullName());
        model.addAttribute("jobRole", user.getJobRole());
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("post") SecurityPost post, Errors errors,
                                @AuthenticationPrincipal User user) {
        if (errors.hasErrors())
            return "design";
        post.setUser(user);
        SecurityPost savedPost = postRepo.save(post);
        log.info("Processing..." + post);
        return "redirect:/posts/current";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<PostType> posts = (List<PostType>) postTypeRepo.findAll();
        model.addAttribute("allPosts", posts);
    }

    @ModelAttribute(name = "post")
    public SecurityPost addPostToModel() {
        return new SecurityPost();
    }

    public void addUser(Model model, @AuthenticationPrincipal User user) {
        List<SecurityPost> post = postRepo.findAllByUser(user);

        model.addAttribute("fullUserName", post);
    }


}
