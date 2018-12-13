package com.cham.feignserver.consumer;

import com.cham.feignserver.domain.Trade;
import com.cham.feignserver.stream.TradeStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaTradeConsumer {

    private static Logger log = LoggerFactory.getLogger(KafkaTradeConsumer.class);

    @StreamListener(TradeStream.INPUT)
    public void consumeTweets(@Payload Trade trade) {
        log.info("Received trade .." + trade);
    }
}
