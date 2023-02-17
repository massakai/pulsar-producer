package com.github.massakai.pulsar.producer.app;

import com.github.massakai.pulsar.producer.message.provider.MessageProvider;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


@Component
@Slf4j
public class ProducerRunner implements ApplicationRunner {

    private final ApplicationProperties applicationProperties;
    private final ProducerTask producerTask;

    public ProducerRunner(final ApplicationProperties applicationProperties,
                          final MessageProvider<String> messageProvider,
                          final Producer<String> producer,
                          final RateLimiter rateLimiter) {
        this.applicationProperties = applicationProperties;
        producerTask = new ProducerTask(messageProvider, producer, rateLimiter);
    }

    @Override
    public void run(ApplicationArguments args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<?> future = executor.submit(producerTask);
            future.get(applicationProperties.getExecutionPeriod().toMillis(), TimeUnit.MILLISECONDS);
            log.info("messages were produced.");
        } catch (final TimeoutException e) {
            log.info("messages were produced in {} ms.",
                    applicationProperties.getExecutionPeriod().toMillis());
        } catch (final Exception e) {
            log.error("An error occurred.", e);
        } finally {
            executor.shutdownNow();
        }
    }
}
