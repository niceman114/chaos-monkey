package how.to.chaosmonkey.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Order(1) // README: This is important caused by execution priority.
@Component
@Slf4j
@ConditionalOnExpression("${util.timer.enabled:false}")
public class TimerAdvice {

    @Around("@annotation(Timer)")
    public Object timer(final ProceedingJoinPoint point) throws Throwable {

        final var stopWatch = new StopWatch();
        stopWatch.start();

        Object object = point.proceed();

        stopWatch.stop();

        log.info("target={}, elapsed(ms)={}",
                point.getSignature(),
                stopWatch.getLastTaskTimeMillis());

        return object;
    }
}
