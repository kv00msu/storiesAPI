package com.api.stories.repositories;

import com.api.stories.model.Story;
import com.api.stories.model.StoryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryUserRepository extends JpaRepository<StoryUser, Long> {
    @Query(value = "select * from story_user where story_id = :story_id and t_user_id = :user_id", nativeQuery = true)
    public StoryUser getByParam(Long user_id, Long story_id);
    public List<StoryUser> getByStoryId(Long id);
}
