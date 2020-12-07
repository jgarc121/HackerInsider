package hacker.web;


import hacker.SecurityPost;
import hacker.data.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/deletePost")
public class DeletePostController {

    private PostRepository postRepo;

    @Autowired
    public DeletePostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("/{postID}")
    public String postForm(@PathVariable String postID) {
        return "/deletePost";
    }

    @PostMapping("/delete/{postID}")
    public String deletePost(@PathVariable("postID") Long id, Model model) {
        SecurityPost securityPost = postRepo.findById(id).get();
        postRepo.delete(securityPost);
        log.info("Deleting... " + securityPost);
        return "redirect:/data";
    }
}
