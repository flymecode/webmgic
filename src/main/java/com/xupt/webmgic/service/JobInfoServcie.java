package com.xupt.webmgic.service;

import com.xupt.webmgic.pojo.JobInfo;

import java.util.List;

/**
 * @author maxu
 * @date 2019/4/29
 */
public interface JobInfoServcie {

    void save(JobInfo jobInfo);

    List<JobInfo> findAll(JobInfo jobInfo);
}

