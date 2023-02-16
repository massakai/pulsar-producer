package com.github.massakai.pulsar.producer.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.time.Duration;

@ConfigurationProperties("app")
@Data
public class ApplicationProperties {
    /**
     * 最大スループット
     */
    private int maxMessagesPerSecond = 1;
    /**
     * メッセージのファイルパス
     */
    private Path filePath;
    /**
     * Producerの実行時間
     */
    private Duration executionPeriod = Duration.ofSeconds(10);
}
