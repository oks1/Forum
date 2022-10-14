package prog3fp.llom.forum.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import prog3fp.llom.forum.Domain.User;
import prog3fp.llom.forum.Services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register/user", method = RequestMethod.POST)
    public String registerEmployee(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        //if user is present and create user exist message
        if(userService.findUserByUsername(user.getUsername()).isPresent()){
            FieldError error = new FieldError("user", "username", "Username already exists");
            bindingResult.addError(error);
        } else {
            //if user isn't present check if password validates
            if (user.getPassword().length() < 8 || user.getPassword().length() > 15) {
                FieldError error = new FieldError("user", "password", "Password must be 8 to 15 characters.");
                bindingResult.addError(error);
            }
        }

        if(bindingResult.hasErrors()){
            return "register";
        }
        userService.save(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/save/user", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        //if user is present
        if(!user.getPassword().isEmpty() || !user.getPassword2().isEmpty()) {
            if (user.getPassword().length() < 8 || user.getPassword().length() > 15) {
                FieldError error = new FieldError("user", "password", "Password must be 8 to 15 characters.");
                bindingResult.addError(error);
            }
//            if (user.getPassword() != user.getPassword2()) {
            if (!user.getPassword().equals(user.getPassword2())){
                FieldError error = new FieldError("user", "password2", "The passwords are not the same.");
                bindingResult.addError(error);
            }
        }

//        if(userService.findUserByUsername(user.getUsername()).isPresent()){
//            if(!user.getPassword().isEmpty()) {
//                if (user.getPassword().length() < 8 || user.getPassword().length() > 15) {
//                    FieldError error = new FieldError("user", "password", "Password must be 8 to 15 characters.");
//                    bindingResult.addError(error);
//                }
//            }
//        } else {
//            //if user isn't present / is new
//            if (user.getPassword().length() < 8 || user.getPassword().length() > 15) {
//                FieldError error = new FieldError("user", "password", "Password must be 8 to 15 characters.");
//                bindingResult.addError(error);
//            }
//        }

        if(bindingResult.hasErrors()){
            return "userpanel";
        }
        userService.save(user);
        return "redirect:/";
//        return "redirect:/user/edit/" + user.getUserId();
    }

    @RequestMapping("/user/edit/{userId}")
    public ModelAndView showEditUserPage(@PathVariable("userId") Long userId) {
        ModelAndView mav = new ModelAndView("userpanel");
        Optional<User> user = userService.findUserById(userId);
        mav.addObject("user", user);
        return mav;

    }
    @RequestMapping("/user/delete/{userId}")
    public String deleteUserPage(@PathVariable(name = "userId") Long userId) {
        userService.delete(userId);
        return "redirect:/";
    }

}
