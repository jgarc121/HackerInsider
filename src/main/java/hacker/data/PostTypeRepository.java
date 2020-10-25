package hacker.data;

import hacker.Post;
import hacker.PostType;
import org.springframework.data.repository.CrudRepository;


public interface PostTypeRepository extends CrudRepository<PostType, String> {


}
