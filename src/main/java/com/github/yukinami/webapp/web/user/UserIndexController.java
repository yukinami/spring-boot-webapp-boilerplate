package com.github.yukinami.webapp.web.user;

import com.github.yukinami.webapp.domain.user.User;
import com.github.yukinami.webapp.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author snowblink on 16/5/25.
 */
@RequestMapping("/users")
@Controller
public class UserIndexController {

    private UserRepository userRepository;

    @RequestMapping
    public String index(@PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<User> users = userRepository.findAll(pageable);

        model.addAttribute("users", users);
        return "users/index";
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
