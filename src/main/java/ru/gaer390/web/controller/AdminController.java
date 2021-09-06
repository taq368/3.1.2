package ru.gaer390.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gaer390.web.user.User;
import ru.gaer390.web.user.UserServiceImpl;


@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserServiceImpl userService;

    @GetMapping
    public String getAllUsers(Model model,
                              Authentication authentication) {
        model.addAttribute("admin", userService.getUserByEmail(authentication.getName()));
        model.addAttribute("users", userService.getAllUsers());
        return "admins/admin";
    }

    @GetMapping(path = "/new")
    public String newUser(@ModelAttribute("user") User user,
                          Model model,
                          Authentication authentication) {
        model.addAttribute("admin", userService.getUserByEmail(authentication.getName()));
        return "admins/new";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute User user,
                             @RequestParam(required = false) String[] role) {
        log.info("role {}", role);
        userService.addNewUser(user);
        if (role != null) {
            for (String rl: role) {
                userService.addRoleToUser(user.getEmail(), rl);
            }
        }
        return "redirect:/admin";
    }

    @PutMapping
    public String updateUser(@ModelAttribute User user,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false) String[] role) {
        log.info("user: {}", user);
        log.info("roles: {}", role);
        userService.updateUser(user, password);
        if (role != null) {
            for (String rl: role) {
                userService.addRoleToUser(user.getEmail(), rl);
            }
        }
        return "redirect:/admin";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
