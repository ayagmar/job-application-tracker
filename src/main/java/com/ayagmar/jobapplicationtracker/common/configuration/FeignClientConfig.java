package com.ayagmar.jobapplicationtracker.common.configuration;

import com.ayagmar.jobscraper.api.JobsApi;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableFeignClients(clients = {JobsApi.class})
@RequiredArgsConstructor
public class FeignClientConfig {
    @Value("${job.api.connection-timeout}")
    private int apiConnectTimeout;
    @Value("${job.api.read-timeout}")
    private int apiReadTimeout;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(
                apiConnectTimeout, TimeUnit.SECONDS,
                apiReadTimeout, TimeUnit.SECONDS,
                true
        );

    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 3);
    }


}
