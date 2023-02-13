package com.github.massakai.pulsar.producer.config;

import lombok.Data;
import org.apache.pulsar.client.api.CompressionType;
import org.apache.pulsar.client.api.HashingScheme;
import org.apache.pulsar.client.api.MessageRoutingMode;
import org.apache.pulsar.client.api.ProducerCryptoFailureAction;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties("pulsar.producer")
@Data
public class ProducerProperties {

    private String topicName;
    private String producerName;
    private Duration sendTimeout = Duration.ofMillis(30_000);
    private boolean blockIfQueueFull = false;
    private int maxPendingMessages = 1_000;
    private int maxPendingMessagesAcrossPartitions = 50_000;
    private MessageRoutingMode messageRoutingMode = MessageRoutingMode.RoundRobinPartition;
    private HashingScheme hashingScheme = HashingScheme.JavaStringHash;
    private ProducerCryptoFailureAction cryptoFailureAction = ProducerCryptoFailureAction.FAIL;
    private Duration batchingMaxPublishDelay = Duration.ofMillis(1);
    private int batchingMaxMessages = 1_000;
    private boolean batchingEnabled = true;
    private boolean chunkingEnabled = false;
    private CompressionType compressionType;
    private String initialSubscriptionName;

    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>();
        map.put("topicName", topicName);
        map.put("producerName", producerName);
        if (sendTimeout != null) {
            map.put("sendTimeoutMs", sendTimeout.toMillis());
        }
        map.put("blockIfQueueFull", blockIfQueueFull);
        map.put("maxPendingMessages", maxPendingMessages);
        map.put("maxPendingMessagesAcrossPartitions", maxPendingMessagesAcrossPartitions);
        map.put("messageRoutingMode", messageRoutingMode);
        map.put("hashingScheme", hashingScheme);
        map.put("cryptoFailureAction", cryptoFailureAction);
        if (batchingMaxPublishDelay != null) {
            map.put("batchingMaxPublishDelayMicros", batchingMaxPublishDelay.toNanos() / 1000);
        }
        map.put("batchingMaxMessages", batchingMaxMessages);
        map.put("batchingEnabled", batchingEnabled);
        map.put("chunkingEnabled", chunkingEnabled);
        map.put("compressionType", compressionType);
        map.put("initialSubscriptionName", initialSubscriptionName);

        return map;
    }
}
