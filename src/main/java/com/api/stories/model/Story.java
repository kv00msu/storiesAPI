package com.api.stories.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Component
@Data
@Entity(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagePath;
    private String backgroundColor;
    private String subtitle;
    private String heading;
    @OneToOne(cascade = CascadeType.ALL)
    private Button button;
    @OneToMany(mappedBy="story",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<StoryUser> storyUsers;
}
