package com.xupt.webmgic.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author maxu
 * @date 2019/4/29
 */
@Setter
@Getter
@Document(indexName = "jobInfo", type = "jobInfoFiled")
public class JobItem {
    @Id
    @Field(index = true, store = true, type = FieldType.Long)
    private Long id;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Long)
    private String companyName;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Long)
    private String companyAddr;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Long)
    private String companyInfo;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Long)
    private String jobName;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Long)
    private String jobAddr;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "zik_smart", type = FieldType.Long)
    private String jobInfo;
    @Field(index = true, store = true, type = FieldType.Long)
    private String salaryMax;
    @Field(index = true, store = true, type = FieldType.Long)
    private String salaryMin;
    @Field(index = true, store = true, type = FieldType.Long)
    private String url;
    @Field(index = true, store = true, type = FieldType.Long)
    private String time;
}
