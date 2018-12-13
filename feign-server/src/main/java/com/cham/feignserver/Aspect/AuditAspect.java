package com.cham.feignserver.Aspect;

import com.cham.eventsourcecomponent.auditproducer.AuditMessageProducer;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    private static Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    @Autowired
    private AuditMessageProducer auditMessageProducer;

    @Autowired
    Gson gson;

    @After("execution(* com..*KafkaTradeProducer.*(..))")
    public void auditPublishTrade(JoinPoint joinPoint) {
        logger.info("Inside AuditAspect.auditPublishTrade()");
        publishAuditMessage(joinPoint);
    }

   @After("execution(* com..*KafkaTradeConsumer.*(..))")
    public void auditConsumeTrade(JoinPoint joinPoint) {
        logger.info("Inside AuditAspect.auditConsumeTrade()");
        publishAuditMessage(joinPoint);
    }

    private void publishAuditMessage(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object retVal = args[0];
        String tradeStr = gson.toJson(retVal);
        auditMessageProducer.publishAuditMessages(tradeStr);
    }

}
