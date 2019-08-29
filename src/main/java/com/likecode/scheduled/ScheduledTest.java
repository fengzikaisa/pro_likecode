package com.likecode.scheduled;

import com.likecode.service.BarrageService;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	BarrageService barrageServiceImpl;

	/**
	 * 每日清除弹幕
	 */
	@Scheduled(cron = "0 0 0 1/1 * ? ")
	public void delDanMu() {
		barrageServiceImpl.updateBarrage();
//		System.out.println("111");
	}
}
