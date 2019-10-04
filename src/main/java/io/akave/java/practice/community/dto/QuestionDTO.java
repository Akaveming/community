package io.akave.java.practice.community.dto;

import io.akave.java.practice.community.model.User;
import lombok.Data;

/**
 * @author akave
 */
@Data
public class QuestionDTO {
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
    private User user;
}
