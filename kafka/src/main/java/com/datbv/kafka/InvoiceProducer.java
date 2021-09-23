package com.datbv.kafka;

import com.datbv.kafka.message.Invoice;
import com.datbv.kafka.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;

public class InvoiceProducer {

    public static void main(String[] args) {

        final var random = new Random();
        final var props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        try (var producer = new KafkaProducer<String, Invoice>(props)) {
            IntStream.range(0, 100_000)
                    .parallel()
                    .forEach(i -> {
                        final var invoice = Invoice.builder()
                                .invoiceNumber(String.format("%05d", i))
                                .storeId(i % 5 + "")
                                .created(System.currentTimeMillis())
                                .valid(random.nextBoolean())
                                .build();
                        producer.send(new ProducerRecord<>("invoice-topic", invoice));
                    });
        }
    }

}
