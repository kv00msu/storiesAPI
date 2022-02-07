package com.api.stories.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Data
@Entity
@Table(name="story_user")
public class StoryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ColumnDefault("no")
    private String isChecked;
    @ColumnDefault("yes")
    private String isAvailable;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="story_id")
    private Story story;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="t_user_id")
    private User user;
    public StoryUser(){}
    public StoryUser(Story story, User user){
        this.story = story;
        this.user = user;
    }

}
