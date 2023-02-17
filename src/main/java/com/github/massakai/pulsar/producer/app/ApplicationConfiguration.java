package com.github.massakai.pulsar.producer.app;


import com.github.massakai.pulsar.producer.message.provider.MessageProvider;
import com.github.massakai.pulsar.producer.message.provider.RepeatableMessageProvider;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationConfiguration {

    @Bean
    public RateLimiter rateLimiter(final ApplicationProperties applicationProperties) {
        return RateLimiter.create(applicationProperties.getMaxMessagesPerSecond());
    }

    @Bean
    public MessageProvider<String> messageProvider(
            final ApplicationProperties applicationProperties) throws IOException {
        List<String> lines = Files.readAllLines(applicationProperties.getFilePath());
        return new RepeatableMessageProvider<>(lines);
    }
}
