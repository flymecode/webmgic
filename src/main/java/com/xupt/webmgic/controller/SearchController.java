package com.xupt.webmgic.controller;

import com.xupt.webmgic.service.JobRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maxu
 * @date 2019/4/29
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private JobRepositoryService jobRepositoryService;


    @PostMapping
    public String search(String salary, String jobAddr, String keyword, Integer page) {
        return jobRepositoryService.search(salary, jobAddr, keyword, page);
    }
}
