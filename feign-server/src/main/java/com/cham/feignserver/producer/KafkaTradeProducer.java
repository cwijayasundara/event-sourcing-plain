package com.cham.feignserver.producer;

import com.cham.feignserver.domain.Trade;
import com.cham.feignserver.stream.TradeStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class KafkaTradeProducer {

    @Autowired
    private TradeStream tweetStreams;

    private static Logger log = LoggerFactory.getLogger(KafkaTradeProducer.class);

    public void publishTrades(final Trade trade) {
        log.info("Sending trade to kafka topic" + trade);
        MessageChannel messageChannel = tweetStreams.outboundTrades();

        messageChannel.send(MessageBuilder
                .withPayload(trade)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}