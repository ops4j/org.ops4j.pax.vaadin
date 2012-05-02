package org.pax.vaadin.internal.extender;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.BundleTracker;
import org.pax.vaadin.VaadinResourceService;
import org.pax.vaadin.internal.servlet.VaadinApplicationServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;

public class PaxVaadinBundleTracker extends BundleTracker {
	
	private static final String ALIAS = "alias";


	private static final String VAADIN_PATH = "/VAADIN";
	
	
	private final Logger logger = LoggerFactory.getLogger(PaxVaadinBundleTracker.class.getName());


	private final Map<Bundle, ServiceRegistration> registeredServlets = new HashMap<Bundle, ServiceRegistration>();

	public PaxVaadinBundleTracker(BundleContext context) {
		super(context, Bundle.ACTIVE, null);
	}

	@Override
	public Object addingBundle(Bundle bundle, BundleEvent event) {
		
		
		if (isApplicationBundle(bundle)) {
			logger.debug("found a vaadin-app bundle: {}", bundle);
			String applicationClass = (String) bundle.getHeaders().get(org.pax.vaadin.Constants.VAADIN_APPLICATION);
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
			
			if (application != null) {
				VaadinApplicationServlet servlet = new VaadinApplicationServlet(application);
				
				
				Map<String, Object> props = new Hashtable<String, Object>();
		        props.put( ALIAS, alias );
				
		        ServiceRegistration registeredServlet = bundle.getBundleContext().registerService( HttpServlet.class.getName(), servlet, (Dictionary) props );
		        
		        registeredServlets.put(bundle, registeredServlet);
			}
				
		}
		
		if (isThemeBundle(bundle)) {
			logger.debug("found a vaadin-resource bundle: {}", bundle);
			//TODO do VAADIN Themese handling
			ServiceReference serviceReference = bundle.getBundleContext().getServiceReference(VaadinResourceService.class.getName());
			VaadinResourceService service = (VaadinResourceService) bundle.getBundleContext().getService(serviceReference);
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
	
	private boolean isApplicationBundle(Bundle bundle) {
		if (!isVaadinBundle(bundle))
			return false;
		
		String applicationClass = (String) bundle.getHeaders().get(org.pax.vaadin.Constants.VAADIN_APPLICATION);
		
		if (applicationClass != null && !applicationClass.isEmpty())
			return true;
		
		return false;
	}
	
	private boolean isThemeBundle(Bundle bundle) {
		if ("com.vaadin".equals(bundle.getSymbolicName()))
			return false;
		
		@SuppressWarnings("rawtypes")
		Enumeration vaadinPaths = bundle.getEntryPaths(VAADIN_PATH);
		if (vaadinPaths == null || !vaadinPaths.hasMoreElements())
			return false;
		
		return true;
	}
	
	private boolean isVaadinBundle(Bundle bundle) {
		String importedPackages = (String) bundle.getHeaders().get(
				Constants.IMPORT_PACKAGE);
		if (importedPackages == null) {
			return false;
		}

		if (importedPackages.contains("com.vaadin")) {
			return true;
		}

		return false;
	}
	
}
