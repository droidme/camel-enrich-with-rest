package io.droidme.camelrest;

import io.droidme.camelrest.tds.Security;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.INFO;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    private static final String START_ENDPOINT = "{{StartEndpoint}}";
    private static final String STOP_ENDPOINT = "{{StopEndpoint}}";

    @Override
    public void configure() {

        // REST Config
        restConfiguration()
                .component("servlet")
                .host("localhost")
                .port(8080);

        from(START_ENDPOINT)
                .routeId(MySpringBootRouter.class.getName())
                .log(INFO, "Route ${routeId} startet.")
                .log(INFO, "Original Message: ${body}.")
                // enrich properties with security data
                .enrich("direct:security-data", (oldExchange, newExchange) -> {
                    oldExchange.setProperty("Security", newExchange.getIn().getBody(Security.class));
                    return oldExchange;
                })
                .log(INFO, "Enriched Message: ${body}")
                .log(INFO, "Enriched Message Property('Security'): ${exchangeProperty.Security.isin}")
                .to(STOP_ENDPOINT);

        from("direct:security-data")
                .toD("rest:get:/api/securities/0815")
                .unmarshal().json(Security.class);
    }



}
