package hu.yokudlela.table.utils.logging;


import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import hu.yokudlela.media.spring.ApplicationContextProvider;
import hu.yokudlela.table.utils.request.RequestBean;
import net.logstash.logback.marker.ObjectAppendingMarker;


import static hu.yokudlela.table.utils.logging.CustomRequestLoggingFilter.REQUEST_ID;
import static hu.yokudlela.table.utils.logging.CustomRequestLoggingFilter.NOT_DEF;
import static hu.yokudlela.table.utils.logging.CustomRequestLoggingFilter.USER_ID;

/**
 *
 * @author oe
 */
public class RequestIdMessageConverter extends ClassicConverter {

    ApplicationContextProvider appContext = new ApplicationContextProvider();

    @Override
    @AspectLogger
    public String convert(ILoggingEvent event) {

        try {
            RequestBean request = appContext.getApplicationContext().getBean("requestScopedBean", RequestBean.class);
            if (request != null) {
                return ("" + request.getRequestId());
            }
        } catch (Exception e) {
            if (event != null && event.getArgumentArray() != null) {
                ObjectAppendingMarker tmp;
                for (Object bean : event.getArgumentArray()) {
                    if (bean instanceof ObjectAppendingMarker) {
                        tmp = (ObjectAppendingMarker) bean;
                        if (REQUEST_ID.equals(tmp.getFieldName())) {
                            return "" + tmp.getFieldValue();
                        }
                    }
                }
            }
        }
        if(event.getMDCPropertyMap().get(REQUEST_ID)!=null){return event.getMDCPropertyMap().get(REQUEST_ID);}
        return NOT_DEF;
    }
}
