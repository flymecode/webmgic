package com.xupt.webmgic.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author maxu
 * @date 2019/4/29
 */
@Entity
@Setter
@Getter
@Table(name = "dp_job_info")
public class JobInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyAddr;
    private String companyInfo;
    private String jobName;
    private String jobAddr;
    private String jobInfo;
    private String salaryMax;
    private String salaryMin;
    private String url;
    private String time;
}
