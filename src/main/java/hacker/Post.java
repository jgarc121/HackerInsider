package hacker;

import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Post {

    private final String full_name;
    private final String id;
    private int num_likes = 0;
    private int num_comments = 0;
    private String date_created;
    private final String description;
    private final PostType type;



    private void add_like() {
        this.num_likes++;
    }
    private void add_comment() {
        this.num_comments++;
    }
    private void date_creation() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.date_created = formatter.format(date);
    }


}
