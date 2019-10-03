package io.akave.java.practice.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author akave
 */
@Controller
public class PublishController {
    @RequestMapping("/publish")
    public String publish() {
        return "publish";
    }
}