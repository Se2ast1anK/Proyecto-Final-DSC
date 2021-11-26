package pe.isil.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.isil.service.RecipeService;
import pe.isil.service.TipService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AppController {

    private final TipService tipService;
    private final RecipeService recipeService;

    public AppController(TipService tipService, RecipeService recipeService) {
        this.tipService = tipService;
        this.recipeService = recipeService;
    }

    @GetMapping
    public String defaultPage(){
        return  "redirect:/home";
    }

    @GetMapping("home")
    public  String home(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        tipService.findAll().ifPresent(tips -> finalModel.addAttribute("tips", tips));
        recipeService.findAll().ifPresent(recipes -> finalModel.addAttribute("recipes", recipes));
        return "index";
    }

    @GetMapping("login")
    public  String login() { return "login"; }

    @GetMapping("menu")
    public String menu(){
        return "menu";
    }

}
