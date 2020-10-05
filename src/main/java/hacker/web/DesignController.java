package hacker.web;
import hacker.Post;
import hacker.PostType.Type;
import hacker.PostType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/design")
public class DesignController {

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<PostType> posts = createPostList();
        Type[] types = PostType.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(posts, type));
        }
    }

    private List<PostType> filterByType(List<PostType> posts, Type type) {
        return posts
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
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
