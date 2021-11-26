package pe.isil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.Tip;
import pe.isil.service.AdminService;
import pe.isil.service.TipService;

import javax.servlet.http.HttpSession;

@Controller
public class TipController {
    private final TipService tipService;
    private final AdminService adminService;

    public TipController(TipService tipService, AdminService adminService) {
        this.tipService = tipService;
        this.adminService = adminService;
    }

    @GetMapping("/tips")
    public String tipsPublic(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        tipService.findAll().ifPresent(tips -> finalModel.addAttribute("tips",tips));
        return "tip-public";
    }

    @GetMapping("/admin/tips")
    public String tips(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        tipService.findAll().ifPresent(tips -> finalModel.addAttribute("tips",tips));
        return "tip";
    }

    @GetMapping("/admin/tips/add")
    public String tipAdd(Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        finalModel.addAttribute("tip", new Tip());
        adminService.findAll()
                .ifPresent(admins -> finalModel.addAttribute("admins", admins));
        return "tip-add";
    }

    @PostMapping("/admin/tips/save")
    public String tipsSave(Tip tip) {
        tipService.saveOrUpdate(tip);
        return "redirect:/admin/tips";
    }

    @GetMapping("/admin/tips/edit/{id}")
    public String tipEdit(@PathVariable String id, Model model, final HttpSession session) {
        Model finalModel = model;
        finalModel.addAttribute("isUserAuthenticated", SessionController.getIsUserAuthenticated(session));
        finalModel.addAttribute("username", SessionController.getUsername(session));
        tipService.findById(id)
                .ifPresent(tip -> finalModel.addAttribute("tip", tip));
        adminService.findAll()
                .ifPresent(admins ->  finalModel.addAttribute("admins",admins));
        return "tip-add";
    }

    @GetMapping("/admin/tips/delete/{id}")
    public String tipDelete(@PathVariable String id) {
        tipService.deleteById(id);
        return "redirect:/admin/tips";
    }
}
