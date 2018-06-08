package com.ecoo.dtx.admin.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ecoo.dtx.admin.msg.KryoSerializer;
import com.ecoo.dtx.admin.service.DtxTransactionService;
import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.DtxTransactionStatusEnum;

@Component
public class RedisQueenTask {

	public Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisTemplate<String, DtxTransaction> redisTemplate;

	@Autowired
	DtxTransactionService dtxTransactionService;

	@PostConstruct
	public void ListenRedisQuen() {
		int threadNum = 4;
		ExecutorService fixedEmaiThreadPool = Executors.newFixedThreadPool(threadNum);
		for (int i = 0; i <= threadNum - 1; i++) {
			fixedEmaiThreadPool.execute(new DtxTransactionThread("REDIS_MESSAGE_QUEUE:dtx"));
		}
	}

	public class DtxTransactionThread implements Runnable {

		public String redisKey;

		public DtxTransactionThread(String redisKey) {
			this.redisKey = redisKey;
		}

		@Override
		public void run() {
			while (true) {
				try {
					DtxTransaction dtxTr = (DtxTransaction) redisTemplate.opsForList().rightPop(redisKey, 0,
							TimeUnit.SECONDS);
					if (DtxTransactionStatusEnum.BEGIN.getCode() == dtxTr.getStatus()) {
						dtxTransactionService.begin(dtxTr);
					} else {
						dtxTransactionService.finish(dtxTr);
					}
				} catch (Exception e) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e1) {
						log.error("sleep错误", e);
					}
					log.error("redis消息错误", e);
				}
			}

		}

	}

}
