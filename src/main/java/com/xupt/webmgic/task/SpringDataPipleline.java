package com.xupt.webmgic.task;

import com.xupt.webmgic.pojo.JobInfo;
import com.xupt.webmgic.service.JobInfoServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author maxu
 * @date 2019/4/29
 */
@Component
public class SpringDataPipleline implements Pipeline {

    @Autowired
    private JobInfoServcie jobInfoServcie;
    @Override
    public void process(ResultItems resultItems, Task task) {
        JobInfo jobInfo = resultItems.get("jobInfo");

        if (jobInfo != null) {
            jobInfoServcie.save(jobInfo);
        }
    }
}
