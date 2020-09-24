package hacker.web;

import hacker.Post;
import hacker.Post.Type;
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
        List<Post> posts = createPostList();
        Type[] types = Post.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(posts, type));
        }
    }

    private List<Post> filterByType(List<Post> posts, Type type) {
        return posts
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    private List<Post> createPostList() {
        List<Post> posts = Arrays.asList(
                new Post("Jose", "1" , "This vulnerability was able to breach users webcams.", Type.recentBreaches),
                new Post("Mary", "1", "This vulnerability was able to breach users phones.", Type.recentBreaches),

                new Post("John Doe", "2", "In order to secure your personal computer, these steps are recommended.", Type.bestSecurityPractices),
                new Post("Elmer", "2", "Moving to cloud requires these security practices to be placed.", Type.bestSecurityPractices),

                new Post("Edgar", "3" ,"This new type of malware has infected our systems.", Type.zero_day),
                new Post("Johnny", "3", "Thousandths of theses viruses have infected many systems across the world..", Type.zero_day)

        );
        return posts;
    }
}
