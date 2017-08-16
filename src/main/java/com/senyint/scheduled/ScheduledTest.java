package com.senyint.scheduled;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

/***
 * 设置定时任务
 * 
 * @author ly
 *
 */
@Log4j
@Component
public class ScheduledTest {
	@Scheduled(cron = "0 0/1 8-20 * * ?")
	public void executeFileDownLoadTask() {

		System.out.println("111");
	}
}
