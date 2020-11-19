package hacker.data;

import hacker.SecurityPost;
import hacker.PostType;
import org.springframework.data.repository.CrudRepository;


public interface PostTypeRepository extends CrudRepository<PostType, String> {


}
