package ru.ilka.wgforge.cats.service.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.ilka.wgforge.cats.service.exception.RestException;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Qualifier("requestCountService")
@Service
public class RequestCountService {
    private static Logger logger = LogManager.getLogger(RequestCountService.class);
    private static final int MINUTE_IN_MILLIS = 60000;

    @Value("${limit.minute}")
    private long limitPerMinute;

    private AtomicLong count;
    private AtomicLong countStartTimestamp;

    public RequestCountService() {
        count = new AtomicLong(0);
        countStartTimestamp = new AtomicLong(currentTimestamp());
    }

    public void checkForTooManyRequests() {
        long now = currentTimestamp();
        if (now - countStartTimestamp.get() > MINUTE_IN_MILLIS) {
            count = new AtomicLong(0);
            countStartTimestamp = new AtomicLong(now);
        }

        count.incrementAndGet();

        if (count.get() > limitPerMinute) {
            throw new RestException("Too Many Requests!", HttpStatus.TOO_MANY_REQUESTS);
        }
    }

    private long currentTimestamp() {
        Date now = new Date();
        return now.getTime();
    }

}
