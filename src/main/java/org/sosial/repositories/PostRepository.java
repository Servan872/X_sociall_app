package org.sosial.repositories;

import org.sosial.entities.Post;
import org.sosial.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findPostByUser(User user);

}
