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
package org.ops4j.pax.vaadin.internal.extender;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.ops4j.pax.vaadin.VaadinResourceService;
import org.ops4j.pax.vaadin.internal.servlet.VaadinApplicationServlet;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.BundleTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;

public class PaxVaadinBundleTracker extends BundleTracker  {

	private final Logger logger = LoggerFactory
			.getLogger(PaxVaadinBundleTracker.class.getName());

	private final Map<Bundle, ServiceRegistration> registeredServlets = new HashMap<Bundle, ServiceRegistration>();

	public PaxVaadinBundleTracker(BundleContext context) {
		super(context, Bundle.ACTIVE, null);
	}

	@Override
	public Object addingBundle(Bundle bundle, BundleEvent event) {

		if (Util.isApplicationBundle(bundle)) {
			logger.debug("found a vaadin-app bundle: {}", bundle);
			String applicationClass = (String) bundle.getHeaders().get(
					org.ops4j.pax.vaadin.Constants.VAADIN_APPLICATION);
			String alias = (String) bundle.getHeaders().get("Vaadin-Alias");
			Application application = null;
			try {
				Class appClazz = bundle.loadClass(applicationClass);

				Constructor[] ctors = appClazz.getDeclaredConstructors();
				Constructor ctor = null;
				for (int i = 0; i < ctors.length; i++) {
					ctor = ctors[i];
					if (ctor.getGenericParameterTypes().length == 0)
						break;
				}
				ctor.setAccessible(true);
				application = (Application) ctor.newInstance();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			final String widgetset = Util.findWidgetset(bundle);

			if (application != null) {
				VaadinApplicationServlet servlet = new VaadinApplicationServlet(application);

				Map<String, Object> props = new Hashtable<String, Object>();
				props.put(org.ops4j.pax.vaadin.Constants.ALIAS, alias);

				if (widgetset != null) {
					props.put("init-prefix", "init.");
					props.put("init.widgetset", widgetset);
				}

				ServiceRegistration registeredServlet = bundle
						.getBundleContext().registerService(
								HttpServlet.class.getName(), servlet,
								(Dictionary) props);

				registeredServlets.put(bundle, registeredServlet);
			}

		}

		if (Util.isResourceBundle(bundle)) {
			logger.debug("found a vaadin-resource bundle: {}", bundle);
			// TODO do VAADIN Themes handling
			ServiceReference serviceReference = bundle.getBundleContext()
					.getServiceReference(VaadinResourceService.class.getName());
			VaadinResourceService service = (VaadinResourceService) bundle
					.getBundleContext().getService(serviceReference);
			service.addResources(bundle);
		}

		return super.addingBundle(bundle, event);
	}

	@Override
	public void removedBundle(Bundle bundle, BundleEvent event, Object object) {

		ServiceRegistration registeredServlet = registeredServlets.get(bundle);
		if (registeredServlet != null)
			registeredServlet.unregister();

		super.removedBundle(bundle, event, object);
	}

}
