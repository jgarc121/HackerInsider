package hacker;

import lombok.Data;

@Data
public class PostType {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        zero_day, recentBreaches, bestSecurityPractices
    }
}
