package pe.isil.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpSession;

public class SessionController {

    public static Boolean getIsUserAuthenticated(HttpSession session) {
        Boolean isUserAuthenticated = false;
        try {
            SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null) {
                isUserAuthenticated = authentication.isAuthenticated();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            return isUserAuthenticated;
        }
    }

    public static String getUsername(HttpSession session) {
        String username = null;
        try {
            SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null) {
                User user = (User) authentication.getPrincipal();
                if (user != null) {
                    username = user.getUsername();
                }
            }
        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            return username;
        }
    }

    /*
    public static Model setUserSession(Model model, HttpSession session) {
        System.out.println("session: " + session.toString());
        System.out.println("attributes: " + session.getAttributeNames().toString());
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String param = enumeration.nextElement();
            System.out.println("param name: " + param);
            System.out.println("param value: " + session.getAttribute(param));
        }
        System.out.println("username: " + session.getAttribute("username"));
        model.addAttribute("isUserAuthenticated", false);
        model.addAttribute("username", null);
        try {
            SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null) {
                System.out.println("authentication isAuthenticated: " + authentication.isAuthenticated());
                model.addAttribute("isUserAuthenticated", authentication.isAuthenticated());
                User user = (User) authentication.getPrincipal();
                if (user != null) {
                    System.out.println("authentication principal: " + user);
                    model.addAttribute("username", user.getUsername());
                }
            }
        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            return model;
        }
    }
    */
}
