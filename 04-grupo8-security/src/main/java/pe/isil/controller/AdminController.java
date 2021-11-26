package pe.isil.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.Admin;
import pe.isil.service.AdminService;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/admins")
    public String admins(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        adminService.findAll()
                .ifPresent(admins -> finalModel.addAttribute("admins", admins));
        return "admin";

    }

    @GetMapping("/admin/admins/add")
    public String adminsAdd(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        finalModel.addAttribute("admin",new Admin());
        return "admin-add";
    }

    @PostMapping("/admin/admins/save")
    public String adminsSave(Admin admin) {
            adminService.saveOrUpdate(admin);
            return "redirect:/admin/admins";
    }

    @GetMapping("/admin/admins/edit/{id}")
    public String adminsEdit(@PathVariable String id, Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        adminService.findById(id).ifPresent(admin -> finalModel.addAttribute("admin",admin));
        return "admin-add";
    }
    @GetMapping("/admin/admins/delete/{id}")
    public String adminsDelete(@PathVariable String id) {
        adminService.deleteById(id);
        return "redirect:/admin/admins";
    }
}
