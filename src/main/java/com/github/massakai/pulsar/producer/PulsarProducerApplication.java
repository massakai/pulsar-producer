package com.github.massakai.pulsar.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PulsarProducerApplication {

    public static void main(final String[] args) {
        try (var context = SpringApplication.run(PulsarProducerApplication.class, args)) {
            SpringApplication.exit(context);
        }
    }

}
