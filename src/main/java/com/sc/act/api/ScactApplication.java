package com.sc.act.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.sc.act.api.constant.ScactConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@MapperScan("com.sc.act.api.mapper")
@EnableTransactionManagement
public class ScactApplication {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、定义convert转换消息对象
        FastJsonHttpMessageConverter fasConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastJson的配置信息，比如：是否要格式化返回json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3、再convert中添加配置信息
        fasConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fasConverter;
        return new HttpMessageConverters(converter);
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        //添加线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(8);
        executor.setThreadNamePrefix(ScactConstant.SCACT_THREAD_NAME_PREFIX);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 对拒绝task的处理策略
        executor.setKeepAliveSeconds(60);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

    @Bean(name = "sendExecutorService")
    public ExecutorService sendThreadPoolTaskExecutor() {
        return Executors.newFixedThreadPool(60);
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(30000);// 单位为ms
        factory.setConnectTimeout(30000);// 单位为ms
        return new RestTemplate(factory);
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ScactApplication.class, args);
        ExecutorService sendExecutorService = (ExecutorService) context.getBean("sendExecutorService");
        // 优雅关闭线程池
        Runtime.getRuntime().addShutdownHook(new Thread(sendExecutorService::shutdown));
    }
}
