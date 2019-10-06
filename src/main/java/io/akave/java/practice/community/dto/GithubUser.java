package io.akave.java.practice.community.dto;

import lombok.Data;

/**
 * @author akave
 */
@Data
public class GithubUser {
    private String name;
    private String id;
    private String bio;
    private String avatarUrl;
}
