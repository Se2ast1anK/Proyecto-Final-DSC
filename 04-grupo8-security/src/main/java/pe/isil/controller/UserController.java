package pe.isil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.User;
import pe.isil.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String users(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        userService.findAll()
                .ifPresent(users -> finalModel.addAttribute("users",users));
        return "user";
    }

    @GetMapping("/admin/users/add")
    public String usersAdd(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        finalModel.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/admin/users/save")
    public String usersSave(User user) {
        userService.saveOrUpdate(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/edit/{id}")
    public String usersEdit(@PathVariable Long id, Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        userService.findById(id).ifPresent(user -> finalModel.addAttribute("user",user));
        return "user-add";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String usersDelete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
