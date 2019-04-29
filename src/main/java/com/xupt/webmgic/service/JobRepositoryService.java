package com.xupt.webmgic.service;

import com.xupt.webmgic.pojo.JobItem;

import java.util.List;

/**
 * @author maxu
 * @date 2019/4/29
 */
public interface JobRepositoryService {
    void save(JobItem jobItem);

    void saveAll(List<JobItem> list);

    String search(String salary, String jobAddr, String keyword, Integer page);
}
