package com.xupt.webmgic.dao;

import com.xupt.webmgic.pojo.JobItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author maxu
 * @date 2019/4/29
 */
@Component
public interface JobSearch extends ElasticsearchRepository<JobItem,Long> {
}
