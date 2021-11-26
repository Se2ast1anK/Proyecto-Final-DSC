package pe.isil.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.Recipe;
import pe.isil.service.AdminService;
import pe.isil.service.RecipeService;
import pe.isil.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final AdminService adminService;
    private final UserService userService;

    public RecipeController(RecipeService recipeService, AdminService adminService, UserService userService) {
        this.recipeService = recipeService;
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping("/recipes")
    public String recipesPublic(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        recipeService.findAll().ifPresent(recipes -> finalModel.addAttribute("recipes",recipes));
        return "recipe-public";
    }

    @GetMapping("/admin/recipes")
    public String recipes(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        recipeService.findAll().ifPresent(recipes -> finalModel.addAttribute("recipes",recipes));
        return "recipe";
    }

    @GetMapping("/admin/recipes/add")
    public String recipeAdd(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        finalModel.addAttribute("recipe", new Recipe());
        adminService.findAll()
                .ifPresent(admins -> finalModel.addAttribute("admins",admins));
        userService.findAll()
                .ifPresent(users -> finalModel.addAttribute("users",users));
        return "recipe-add";
    }
    @PostMapping("/admin/recipes/save")
    public String recipesSave(Recipe recipe) {
        recipeService.saveOrUpdate(recipe);
        return "redirect:/admin/recipes";
    }
    @GetMapping("/admin/recipes/edit/{id}")
    public String recipeEdit(@PathVariable String id, Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        recipeService.findById(id)
                .ifPresent(recipe -> finalModel.addAttribute("recipe",recipe));
        adminService.findAll()
                .ifPresent(admins ->  finalModel.addAttribute("admins",admins));
        userService.findAll()
                .ifPresent(users -> finalModel.addAttribute("users",users));
        return "recipe-add";
    }

    @GetMapping("/admin/recipes/delete/{id]")
    public String recipeDelete(@PathVariable String id) {
        recipeService.deleteById(id);
        return "redirect:/admin/recipes";
    }
}
