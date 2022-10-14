package prog3fp.llom.forum.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import prog3fp.llom.forum.Domain.Post;
import prog3fp.llom.forum.Domain.User;
import prog3fp.llom.forum.Services.PostService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/post/newpost/{topicId}/{userId}")
    public String add(@PathVariable("topicId") Long topicId, Model model, @PathVariable Long userId) {
        Post post = new Post();
        post.setTopicId(topicId);
        User user = new User();
        user.setUserId(userId);
        post.setUser(user);
        model.addAttribute("post", post);
        return "newpost";
    }

    @RequestMapping(value = "/post/savepost", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult, Model model) {
        //if post is present

        if(bindingResult.hasErrors()){
            return "newpost";
        }
        LocalDate now = LocalDate.now();
        post.setCreationDate(String.valueOf(now));
        String topicId = String.valueOf(post.getTopicId());
        postService.save(post);
        //return to the topic via topicId
        return "redirect:/topic/"+topicId;
    }

    @RequestMapping("/post/editpost/{postId}")
    public ModelAndView showEditPostPage(@PathVariable("postId") Long postId) {
        ModelAndView mav = new ModelAndView("newpost");
        Post post = postService.findPostByPostId(postId).get();
        mav.addObject("post", post);
        return mav;

    }
    @RequestMapping("/post/deletepost/{postId}")
    public String deletePostPage(@PathVariable(name = "postId") Long postId) {
        Post post = postService.findPostByPostId(postId).get();
        Long topicId = post.getTopicId();
        postService.delete(postId);
        return "redirect:/topic/" + topicId;
    }

}
