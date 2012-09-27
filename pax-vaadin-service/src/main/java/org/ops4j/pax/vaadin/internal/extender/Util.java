package org.ops4j.pax.vaadin.internal.extender;

import java.net.URL;
import java.util.Enumeration;

import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

class Util {

	public static boolean isApplicationBundle(Bundle bundle) {
		if (bundle == null)
			return false;
		
		if (!isVaadinBundle(bundle))
			return false;

		String applicationClass = (String) bundle.getHeaders().get(
				org.ops4j.pax.vaadin.Constants.VAADIN_APPLICATION);

		if (applicationClass != null && !applicationClass.isEmpty())
			return true;

		return false;
	}

	public static boolean isVaadinBundle(Bundle bundle) {
		if (bundle == null)
			return false;
		
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

	public static boolean isResourceBundle(Bundle bundle) {
		if ("com.vaadin".equals(bundle.getSymbolicName()))
			return false;

		@SuppressWarnings("rawtypes")
		Enumeration vaadinPaths = bundle.getEntryPaths(org.ops4j.pax.vaadin.Constants.VAADIN_PATH);
		if (vaadinPaths == null || !vaadinPaths.hasMoreElements())
			return false;

		return true;
	}

	public static String findWidgetset(Bundle bundle) {
		if (bundle == null)
			return null;
		
		Enumeration<?> widgetEntries = bundle.findEntries("", "*.gwt.xml", true);
		//Enumeration widgetEntries = bundle.getEntryPaths(org.ops4j.pax.vaadin.Constants.VAADIN_PATH);
		if (widgetEntries == null || !widgetEntries.hasMoreElements())
			return null;

		/*
		while (widgetEntries.hasMoreElements()) {

			String path = (String) widgetEntries.nextElement();

			if (path.indexOf("widgetsets") != -1) {
				Enumeration entryPaths = bundle.getEntryPaths(path);
				while (entryPaths.hasMoreElements()){
					path = (String) entryPaths.nextElement();
					if (path.contains(".")) {
						if (path.endsWith("/")) {
							path = path.substring(0, path.length() - 1);
						}
						path = path.substring(path.lastIndexOf("/")+1);
						return path;
					}
				}
			}
		}
		*/
		URL widgetUrl = (URL) widgetEntries.nextElement();
		String path = widgetUrl.getPath();
		path = path.substring(1,path.length()-8);
		path = path.replace("/", ".");
		return path;
	}
}
