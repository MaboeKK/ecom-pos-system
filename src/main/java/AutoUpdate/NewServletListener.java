/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoUpdate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Tsheno
 */
public class NewServletListener implements ServletContextListener {
    
    private ScheduledExecutorService executor;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        executor = Executors.newScheduledThreadPool(1);
        
        // Schedule a task to run every 1 second with an initial delay of 0 seconds
        executor.scheduleAtFixedRate(new MyTask(), 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (executor != null) {
            executor.shutdown(); // Shutdown the executor service gracefully
        }
    }
}
