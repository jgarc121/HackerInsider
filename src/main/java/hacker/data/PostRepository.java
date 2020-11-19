package hacker.data;

import hacker.SecurityPost;
import hacker.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;



public interface PostRepository extends CrudRepository<SecurityPost, Long> {


    List<SecurityPost> findAllByUser(User user);

    List<SecurityPost> findAllByUser(User user, Pageable pageable);



}
