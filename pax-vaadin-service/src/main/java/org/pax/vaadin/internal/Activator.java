/**
 * 
 */
package org.pax.vaadin.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.pax.vaadin.VaadinResourceService;
import org.pax.vaadin.internal.extender.PaxVaadinBundleTracker;
import org.pax.vaadin.internal.servlet.VaadinResourceServlet;

/**
 * @author achim
 *
 */
public class Activator implements BundleActivator {

	private BundleContext bundleContext;
	private PaxVaadinBundleTracker bundleTracker;
	private ServiceRegistration resourceService;

	public void start(BundleContext context) throws Exception {
		bundleContext = context;
		createAndRegisterVaadinResourceServlet();
		
		bundleTracker = new PaxVaadinBundleTracker(bundleContext);
		
		bundleTracker.open();
		
	}

	public void stop(BundleContext context) throws Exception {
		if (bundleTracker != null)
			bundleTracker.close();
		
		if (resourceService != null)
			resourceService.unregister();
	}
	
	private void createAndRegisterVaadinResourceServlet() {
		Bundle vaadin = null;
		for (Bundle bundle : bundleContext.getBundles()) {
			if ("com.vaadin".equals(bundle.getSymbolicName())) {
				vaadin = bundle;
				break;
			}
		}
		
		Dictionary<String, String> props;

        props = new Hashtable<String, String>();
        props.put("alias", VaadinResourceServlet._VAADIN);
		
        HttpServlet vaadinResourceServlet = new VaadinResourceServlet(vaadin);
        
		resourceService = bundleContext.registerService( Servlet.class.getName(), vaadinResourceServlet, props );
		
		bundleContext.registerService(VaadinResourceService.class.getName(), vaadinResourceServlet, null);
	}

}
