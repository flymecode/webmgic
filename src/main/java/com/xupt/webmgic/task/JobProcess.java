package com.xupt.webmgic.task;

import com.xupt.webmgic.pojo.JobInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author maxu
 * @date 2019/4/29
 */
@Slf4j
@Component
public class JobProcess implements PageProcessor {

    private static String URL = "https://search.51job.com/list/200200%252C00,000000,0000,00,9,99," +
            "java,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99" +
            "&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9" +
            "&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    private Site site = Site.me()
            .setCharset("gbk")
            .setSleepTime(1000)
            .setTimeOut(10 * 1000)
            .setRetrySleepTime(1000)
            .setRetryTimes(3)
            .setUserAgent(" Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");

    @Override
    public void process(Page page) {
        List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
        if (list.size() == 0) {
            // 详情页
            saveJobInfo(page);
        } else {
            // 列表页
            for (Selectable selectable : list) {
                page.addTargetRequest(selectable.links().toString());
            }
            // 获取下一页的url
            page.addTargetRequest(page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString());
        }
    }

    private void saveJobInfo(Page page) {
        Html html = page.getHtml();
        // 创建招聘详情
        JobInfo jobInfo = new JobInfo();
        jobInfo.setCompanyName(html.css("div.cn p.cname a", "text").toString());
        jobInfo.setCompanyAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
       // jobInfo.setCompanyInfo(html.css("div.tmsg", "text").toString());
        String text = html.css("div.cn p.msg", "text").toString();
        int start = text.lastIndexOf("人");
        int end = text.lastIndexOf("发");
        jobInfo.setTime(text.substring(start + 5, end).replace(" ",""));
        jobInfo.setUrl(page.getUrl().toString());
        // jobInfo.setJobAddr(html.css("div.tmsg", "text").toString());
       // jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        jobInfo.setJobName(html.css("div.cn h1", "text").toString());
        String s = html.css("div.cn strong", "text").toString();
        String[] salary = s.substring(0, s.lastIndexOf("万")).split("-");
        jobInfo.setSalaryMax(salary[1]);
        jobInfo.setSalaryMin(salary[0]);
        page.putField("jobInfo",jobInfo);
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    private SpringDataPipleline springDataPipleline;

    @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    public void process() {
        // 创建一个下载器
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("60.182.188.139",32901)));
        // 给下载器设置代理服务器信息
        Spider.create(new JobProcess())
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)))
                .addPipeline(springDataPipleline)
                .setDownloader(downloader)
                .addUrl(URL)
                .thread(10)
                .run();
    }

}
