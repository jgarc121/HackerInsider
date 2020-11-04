package hacker.data;

import hacker.SecurityPost;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<SecurityPost, Long> {



}
