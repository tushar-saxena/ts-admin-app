package com.ts.tsadmin.controller;

import com.ts.tsadmin.domain.User;
import com.ts.tsadmin.dto.UserDTO;
import com.ts.tsadmin.repository.UserRepository;
import com.ts.tsadmin.service.UserEntityService;
import com.ts.tsadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        return "user/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "user/create";
    }

    @PostMapping("/save")
    public String save(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/index";
        }
        userRepository.save(user);
        return "user/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        System.out.println("<><>id : " + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userEntityService.convertToDto(user);
        model.addAttribute("user", userEntityService.convertToDto(user));
        return "user/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid User user,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user/edit";
        }
        User userInstance = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userInstance.setEmailId(user.getEmailId());
        userInstance.setPassword(user.getPassword());
        userRepository.save(userInstance);
        return "redirect:/user/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/user/index";
    }

}
