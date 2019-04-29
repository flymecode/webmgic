package com.xupt.webmgic.service.impl;

import com.xupt.webmgic.dao.JobSearch;
import com.xupt.webmgic.pojo.JobItem;
import com.xupt.webmgic.service.JobRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author maxu
 * @date 2019/4/29
 */
@Service
public class JobItemRepositoryServcieImpl implements JobRepositoryService {
    @Autowired
    private JobSearch jobSearch;
    @Override
    public void save(JobItem jobItem) {
        jobSearch.save(jobItem);
    }

    @Override
    public void saveAll(List<JobItem> list) {
        jobSearch.saveAll(list);
    }
}
