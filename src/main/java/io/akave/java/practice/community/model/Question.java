package io.akave.java.practice.community.model;

import lombok.Data;

/**
 * @author akave
 */
@Data
public class Question {
    private Integer id;
    private Integer creator;
    private String title;
    private String description;
    private String tag;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;
}
