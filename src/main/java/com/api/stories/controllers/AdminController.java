package com.api.stories.controllers;

import com.api.stories.model.Story;
import com.api.stories.model.StoryUser;
import com.api.stories.repositories.StoryRepository;
import com.api.stories.repositories.StoryUserRepository;
import com.api.stories.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private StoryUserRepository storyUserRepository;

    @GetMapping
    public List<Story> getAll() {
        return storyRepository.getAllForAdmin();
    }
    @PostMapping("/{id}")
    public void setAvailable(@PathVariable("id") Long id, @RequestBody String available) {
        List<StoryUser> storyUser = storyUserRepository.getByStoryId(id);
        for (StoryUser i : storyUser) {
            if (!Objects.equals(i.getUser().getUsername(), "admin"))
                i.setIsAvailable(available);
        }
    }
    @GetMapping("/{id}")
    public Story getStoryById(@PathVariable("id") Long id) {
        return storyRepository.findById(id).get();
    }
}
