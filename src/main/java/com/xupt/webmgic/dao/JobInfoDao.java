package com.xupt.webmgic.dao;

import com.xupt.webmgic.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author maxu
 * @date 2019/4/29
 */
public interface JobInfoDao extends JpaRepository<JobInfo,Long> , JpaSpecificationExecutor<JobInfo> {
}
