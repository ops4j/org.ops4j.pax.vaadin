/*
 * Copyright 2012 Achim Nierbeck.
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
package org.ops4j.pax.vaadin.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

import org.ops4j.pax.vaadin.VaadinResourceService;
import org.ops4j.pax.vaadin.internal.extender.ApplicationFactoryServiceTracker;
import org.ops4j.pax.vaadin.internal.extender.PaxVaadinBundleTracker;
import org.ops4j.pax.vaadin.internal.servlet.VaadinResourceServlet;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author achim
 *
 */
public class Activator implements BundleActivator {

	private BundleContext bundleContext;
	private PaxVaadinBundleTracker bundleTracker;
	private ServiceRegistration resourceService;
    private ApplicationFactoryServiceTracker serviceTracker;

	public void start(BundleContext context) throws Exception {
		bundleContext = context;
		createAndRegisterVaadinResourceServlet();

		bundleTracker = new PaxVaadinBundleTracker(bundleContext);
		serviceTracker = new ApplicationFactoryServiceTracker(bundleContext);

		bundleTracker.open();
		serviceTracker.open();

	}

	public void stop(BundleContext context) throws Exception {
		if (bundleTracker != null)
			bundleTracker.close();
		
		if (serviceTracker != null)
		    serviceTracker.close();

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
