package com.siddhidiscovery;

import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.util.transport.InMemoryBroker;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SiddhiService {

    @PostConstruct
    void init () throws Exception{

        // Create Siddhi Manager
        SiddhiManager siddhiManager = new SiddhiManager();

        //Siddhi Application
        String siddhiApp = "" +
                "@source(type='inMemory', topic='StocksIn', @map(type='json'))" +
                "define stream StockStream (symbol string, price float, volume long); " +
                "@sink(type='inMemory', topic='StocksOut', @map(type='json')) " +
                "define stream OutputStream (symbol string, price float);" +
                "@info(name = 'query1') " +
                "from StockStream[volume < 150] " +
                "select symbol, price " +
                "insert into OutputStream;";

        //Generate runtime
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp);

        InMemoryBroker.subscribe(new InMemoryBroker.Subscriber() {
            @Override
            public void onMessage(Object msg) {
                Logger.getGlobal().info("Message from topic:" + msg);
            }

            @Override
            public String getTopic() {
                return "StocksOut";
            }
        });

        //Start processing
        siddhiAppRuntime.start();

        InMemoryBroker.publish("StocksIn", "{\"symbol\":\"IBM\", \"price\":700, \"volume\":100}");

        Thread.sleep(500);

        //Shutdown runtime
        siddhiAppRuntime.shutdown();

        //Shutdown Siddhi Manager
        siddhiManager.shutdown();

    }

}
