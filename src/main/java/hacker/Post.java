package hacker;

import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Post {

    @NotNull(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String fullName;

    private int num_likes = 0;
    private int num_comments = 0;
    private String date_created;

    @NotNull(message = "Description must be at least 50 characters.")
    @Size(min = 50, message = "Description must be at least 50 characters.")
    private String description;

    @NotNull(message = "You must choose one type of post.")
    private String posttype;


    private void add_like() {
        this.num_likes++;
    }
    private void add_comment() {
        this.num_comments++;
    }

    private void date_creation() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        this.date_created = formatter.format(date);
    }



}
