package com.cham.feignserver.stream;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface TradeStream {

    String OUTPUT = "trade-out";

    @Output(OUTPUT)
    MessageChannel outboundTrades();

    String INPUT = "trade-in";

    @Input(INPUT)
    SubscribableChannel inboundTrades();
}
