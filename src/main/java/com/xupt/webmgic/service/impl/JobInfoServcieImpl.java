package com.xupt.webmgic.service.impl;

import com.xupt.webmgic.dao.JobInfoDao;
import com.xupt.webmgic.pojo.JobInfo;
import com.xupt.webmgic.service.JobInfoServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author maxu
 * @date 2019/4/29
 */
@Service
public class JobInfoServcieImpl implements JobInfoServcie {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(JobInfo jobInfo) {
        JobInfo params = new JobInfo();
        params.setUrl(jobInfo.getUrl());
        params.setTime(jobInfo.getTime());
        List<JobInfo> all = this.findAll(params);
        if (all.size() == 0) {
            jobInfoDao.save(jobInfo);
        }
    }

    @Override
    public List<JobInfo> findAll(JobInfo jobInfo) {
        Example<JobInfo> example = Example.of(jobInfo);
        return jobInfoDao.findAll(example);
    }
}
