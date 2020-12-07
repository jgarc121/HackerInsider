package hacker.web;


import hacker.SecurityPost;
import hacker.data.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/editPost")
public class EditPostController {

    private PostRepository postRepo;

    @Autowired
    public EditPostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }


    @GetMapping("/{postID}")
    public String editPost(@PathVariable("postID") Long id, Model model)  {

        SecurityPost securityPost =  postRepo.findById(id).get();
        model.addAttribute("securityPost", securityPost);

        return "editPost";
    }

    @PostMapping("/update/{postID}")
    public String processEditPost(@PathVariable("postID") Long id, @Valid @ModelAttribute("securityPost") SecurityPost securityPost, Errors errors) {

        if (errors.hasFieldErrors())
            return "editPost";
        SecurityPost newSecurityPost = postRepo.findById(id).get();
        newSecurityPost.setDescription(securityPost.getDescription());
        newSecurityPost.setTitle(securityPost.getTitle());
        postRepo.save(newSecurityPost);
        log.info("Processing... " + newSecurityPost);
        return "redirect:/data";
    }

}
