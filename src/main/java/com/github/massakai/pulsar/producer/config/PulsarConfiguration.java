package com.github.massakai.pulsar.producer.config;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({PulsarClientProperties.class, ProducerProperties.class})
public class PulsarConfiguration {

    @Bean
    public PulsarClient pulsarClient(final PulsarClientProperties pulsarClientProperties) throws PulsarClientException, IllegalAccessException {
        return PulsarClient.builder()
                .loadConf(pulsarClientProperties.toMap())
                .build();
    }

    @Bean
    public Producer<String> pulsarProducer(final PulsarClient pulsarClient, final ProducerProperties producerProperties) throws PulsarClientException {
        return pulsarClient.newProducer(Schema.STRING)
                .loadConf(producerProperties.toMap())
                .create();
    }
}
