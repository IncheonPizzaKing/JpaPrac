package com.example.self.controller;

import com.example.self.config.auth.LoginUser;
import com.example.self.config.auth.dto.SessionUser;
import com.example.self.dto.posts.PostsResponseDto;
import com.example.self.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null) {
            model.addAttribute("guestName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("guestName", user.getName());
        }
        return "postsSave";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "postsUpdate";
    }
}
