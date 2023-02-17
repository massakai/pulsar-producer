package com.github.massakai.pulsar.producer.app;

import com.github.massakai.pulsar.producer.message.provider.MessageProvider;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;

public class ProducerTask implements Runnable {
    private final MessageProvider<String> messageProvider;
    private final Producer<String> producer;
    private final RateLimiter rateLimiter;

    public ProducerTask(final MessageProvider<String> messageProvider,
                        final Producer<String> producer,
                        final RateLimiter rateLimiter) {
        this.messageProvider = messageProvider;
        this.producer = producer;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void run() {
        // タスクをキャンセルされたときにループを終了させる
        while (messageProvider.hasNext() && !Thread.currentThread().isInterrupted()) {
            rateLimiter.acquire();
            try {
                producer.send(messageProvider.next());
            } catch (final PulsarClientException e) {
                // pass
            }
        }
    }
}
