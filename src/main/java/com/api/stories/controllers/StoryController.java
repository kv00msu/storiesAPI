package com.api.stories.controllers;

import com.api.stories.model.Button;
import com.api.stories.model.Story;
import com.api.stories.model.StoryUser;
import com.api.stories.model.User;
import com.api.stories.repositories.StoryRepository;
import com.api.stories.repositories.StoryUserRepository;
import com.api.stories.repositories.UserRepository;
import com.api.stories.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stories")
public class StoryController {
    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private StoryUserRepository storyUserRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public void newStory(@RequestBody Story story, @RequestBody Button button){
        story.setButton(button);
        storyRepository.save(story);
        for (User i : userService.allUsers()) {
            StoryUser storyUser = new StoryUser(story, i);
            storyUserRepository.save(storyUser);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        storyRepository.delete(storyRepository.getById(id));
    }
    @PatchMapping("/{id}")
    public Story update(@PathVariable("id") Long id, @RequestBody(required = false) Story story){
        Story story1 = storyRepository.getById(id);
        story1.setStoryUsers(story.getStoryUsers());
        story1.setButton(story.getButton());
        story1.setHeading(story.getHeading());
        story1.setBackgroundColor(story.getBackgroundColor());
        story1.setImagePath(story.getImagePath());
        story1.setSubtitle(story.getSubtitle());
        return story1;
    }
    @GetMapping("/{id}")
    public Story showById(@PathVariable("id") Long id) {
        StoryUser storyUser = storyUserRepository.getByParam(userService.loadUserByUsername(getCurrentUsername()).getId(), id);
        storyUser.setIsChecked("yes");
        return storyRepository.getById(id);
    }
    //получение списка доступных историй для пользователя
    @GetMapping
    public List<Story> getAll() {
        return storyRepository.getAll(getCurrentUsername());
    }

    //получение новых историй для пользователя
    @GetMapping("/unchecked")
    public List<Story> newStories() {
        return storyRepository.newStories(getCurrentUsername());
    }


    //получение списка просмотренных историй для пользователя
    @GetMapping("/checked")
    public List<Story> checkedStories() {
        return storyRepository.checkedStories(getCurrentUsername());
    }
}
