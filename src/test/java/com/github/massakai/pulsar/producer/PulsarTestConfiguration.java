package com.github.massakai.pulsar.producer;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * PulsarのMockをBean定義するクラス
 *
 * <p>単体テストではPulsarに接続しにいかないようにしたい。</p>
 */
@TestConfiguration
public class PulsarTestConfiguration {

    @MockBean
    PulsarClient pulsarClient;

    @MockBean
    Producer<String> producer;
}
