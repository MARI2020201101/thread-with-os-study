package com.mari.linux.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public void hello() throws InterruptedException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(0);
        executor.initialize();

        AtomicInteger idx = new AtomicInteger();
        for (int i = 0; i < 1000; i++) {
            executor.execute(()-> log.info("hello thread!! "+ idx.addAndGet(1)));
        }
        executor.shutdown();
    }
}
