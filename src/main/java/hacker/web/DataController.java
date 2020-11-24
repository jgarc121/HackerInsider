package hacker.web;

import hacker.SecurityPost;
import hacker.PostType;
import hacker.User;
import hacker.data.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.Data;
import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {

    private final PostRepository postRepo;

    private DataProperties dataProperties;

    @Autowired
    public DataController(PostRepository postRepo, DataProperties dataProperties) {
        this.postRepo = postRepo;
        this.dataProperties = dataProperties;
    }

    @GetMapping
    public String showDesignForm(Model model, @AuthenticationPrincipal User user) {
        addUserPostsToPage(model, user);
        return "data";
    }

    @ModelAttribute
    public void addUserPostsToPage(Model model, @AuthenticationPrincipal User user) {
        Pageable pageable = PageRequest.of(0, dataProperties.getPageSize());
        List<SecurityPost> posts = (List<SecurityPost>) postRepo.findAllByUser(user, pageable);
        model.addAttribute("allPostsForUser", posts);
    }

    @ModelAttribute
    public void addUsersFullNameToPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("fullName", user.getFullName());
    }

}
