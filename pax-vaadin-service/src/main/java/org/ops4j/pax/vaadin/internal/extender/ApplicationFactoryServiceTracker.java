package org.ops4j.pax.vaadin.internal.extender;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.ops4j.pax.vaadin.ApplicationFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

public class ApplicationFactoryServiceTracker extends ServiceTracker {
    
    private Map<ApplicationFactory, ServiceRegistration> m_serviceRegistration = new HashMap<ApplicationFactory, ServiceRegistration>();
    private final Logger logger = LoggerFactory
            .getLogger(ApplicationFactoryServiceTracker.class.getName());
    
    public ApplicationFactoryServiceTracker(BundleContext context) {
        super(context, ApplicationFactory.class.getName(), null);
        
    }
    
    @Override
    public Object addingService(ServiceReference reference) {
        ApplicationFactory factory = (ApplicationFactory) super.addingService(reference);
        FactoryServlet servlet = new FactoryServlet(factory);
        Dictionary props = new Properties();
        
        
        for(String key : reference.getPropertyKeys()) {
            props.put(key, reference.getProperty(key));
        }
        
        if(props.get(PaxVaadinBundleTracker.ALIAS) == null) {
            logger.warn("You have not set the alias property for ApplicationFactory: " + factory);
        }
        m_serviceRegistration.put(factory, context.registerService(Servlet.class.getName(), servlet, props));
        
        return factory;
    }

    @Override
    public void modifiedService(ServiceReference reference, Object service) {
        //TODO: When does this get called
        super.modifiedService(reference, service);
    }

    @Override
    public void removedService(ServiceReference reference, Object service) {
        
        ApplicationFactory factory = (ApplicationFactory) context.getService(reference);
        m_serviceRegistration.remove(factory);
        
        super.removedService(reference, service);
    }
    
    private class FactoryServlet extends AbstractApplicationServlet{
        
        private ApplicationFactory m_factory;

        public FactoryServlet(ApplicationFactory factory) {
            m_factory = factory;
        }
        
        @Override
        protected Application getNewApplication(HttpServletRequest request) throws ServletException {
            return m_factory.createApplication(request);
        }

        @Override
        protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
            return m_factory.getApplicationClass();
        }
        
    }

}
