/*
 * Copyright 2012 Lukas Roedl.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.vaadin.internal.extender;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Servlet;

import org.ops4j.pax.vaadin.VaadinResourceService;
import org.ops4j.pax.vaadin.internal.servlet.VaadinApplicationServlet;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;

public class ApplicationServiceTracker extends ServiceTracker  {
    
    private Map<Application, ServiceRegistration> m_serviceRegistration = new HashMap<Application, ServiceRegistration>();
	private final Logger logger = LoggerFactory
			.getLogger(ApplicationServiceTracker.class.getName());

	public ApplicationServiceTracker(BundleContext context) {
		super(context, Application.class.getName(), null);
	}

	@Override
	public Object addingService(ServiceReference reference) {
        Application application = (Application) super.addingService(reference);
        Servlet servlet = new VaadinApplicationServlet(application.getClass());
        Dictionary props = new Properties();
        
        for(String key : reference.getPropertyKeys()) {
            props.put(key, reference.getProperty(key));
        }
        
        if (props.get(org.ops4j.pax.vaadin.Constants.ALIAS) == null) {
            logger.warn("You have not set the alias property for Application: " + application);
        }
        
        Bundle bundle = reference.getBundle();
        
        final String widgetset = Util.findWidgetset(bundle);
        
        if (widgetset != null) {
			props.put("init-prefix", "init.");
			props.put("init.widgetset", widgetset);
		}
        
        if (Util.isResourceBundle(bundle)) {
			logger.debug("found a vaadin-resource bundle: {}", bundle);
			// TODO do VAADIN Resources handling
			ServiceReference serviceReference = bundle.getBundleContext()
					.getServiceReference(VaadinResourceService.class.getName());
			VaadinResourceService service = (VaadinResourceService) bundle
					.getBundleContext().getService(serviceReference);
			service.addResources(bundle);
		}
        
        m_serviceRegistration.put(application, context.registerService(Servlet.class.getName(), servlet, (Dictionary) props));
        
        return application;
	}

    @Override
    public void modifiedService(ServiceReference reference, Object service) {
        //TODO: When does this get called
        super.modifiedService(reference, service);
    }

    @Override
    public void removedService(ServiceReference reference, Object service) {
        
        Application application = (Application) context.getService(reference);
        if (application != null)
        	m_serviceRegistration.remove(application);
        
        super.removedService(reference, service);
    }
	
}
