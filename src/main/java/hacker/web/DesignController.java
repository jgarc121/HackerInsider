package hacker.web;
import hacker.Post;
import hacker.PostType;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Post design, Errors errors) {
        if (errors.hasErrors())
            return "design";

        log.info("Processing..." + design);
        return "redirect:/posts/current";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<PostType> posts = createPostList();
        model.addAttribute("allPosts", posts);
        model.addAttribute("design", new Post());
    }

    private List<PostType> createPostList() {
        List<PostType> posts = Arrays.asList(
                new PostType("RB", "Recent Breaches", PostType.Type.recentBreaches),
                new PostType("BSP", "Best Security Practices", PostType.Type.bestSecurityPractices),
                new PostType("ZD", "Zero Day Vulnerabilities", PostType.Type.zero_day)
        );

        return posts;
    }
}
