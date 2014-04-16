package fr.rbillard.spring_websocket_angularjs.aspects;

import java.util.Date;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Aspect
public class NotifyAspect {

    @Autowired
    private SimpMessagingTemplate template;

    private static final String WEBSOCKET_TOPIC = "/topic/notify";
    
    @Pointcut("@annotation(fr.rbillard.spring_websocket_angularjs.aspects.NotifyClients)")
    public void notifyPointcut() {}

    @After("notifyPointcut()") 
    public void notifyClients() throws Throwable {
        template.convertAndSend(WEBSOCKET_TOPIC, new Date());
    }

}
