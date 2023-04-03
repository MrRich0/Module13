package org.example;

public class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
