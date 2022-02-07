package com.api.stories.repositories;

import com.api.stories.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    @Query(value = "SELECT * FROM story", nativeQuery = true)
    public List<Story> getAllForAdmin();
    @Query(value = "SELECT c.* FROM story_user a join t_user b on a.t_user_id = b.id join story c on a.story_id = c.id where b.username = :name and is_available = 'yes'", nativeQuery = true)
    public List<Story> getAll(String name);
    @Query(value = "SELECT c.* FROM story_user a join t_user b on a.t_user_id = b.id join story c on a.story_id = c.id  where b.username = :name and is_checked = 'no' and is_available = 'yes'", nativeQuery = true)
    public List<Story> newStories(String name);
    @Query(value = "SELECT c.* FROM story_user a join t_user b on a.t_user_id = b.id join story c on a.story_id = c.id  where b.username = :name and is_checked = 'yes' and is_available = 'yes'", nativeQuery = true)
    public List<Story> checkedStories(String name);
}
