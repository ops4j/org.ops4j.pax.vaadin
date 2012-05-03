package org.ops4j.pax.vaadin.internal.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ops4j.pax.vaadin.VaadinResourceService;
import org.osgi.framework.Bundle;

public class VaadinResourceServlet extends HttpServlet implements VaadinResourceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String _VAADIN = "/VAADIN";
	
	private final Bundle vaadin;
	
	private final List<Bundle> resourceBundles = new ArrayList<Bundle>();
	
	public VaadinResourceServlet(Bundle vaadin) {
		this.vaadin = vaadin;
	}
	
	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException,
			IOException {
		String path = req.getPathInfo();
		String resourcePath = _VAADIN + path;

		URL resourceUrl = vaadin.getResource(resourcePath);
		
		if (null == resourceUrl) {
			resourceUrl = loadFromResources(resourcePath);
		}
		
		if (null == resourceUrl) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		InputStream in = resourceUrl.openStream();
		OutputStream out = resp.getOutputStream();

		byte[] buffer = new byte[1024];
		int read = 0;
		while (-1 != (read = in.read(buffer))) {
			out.write(buffer, 0, read);
		}
	}

	private URL loadFromResources(String resourcePath) {
		for (Bundle resourceBundle : resourceBundles) {
			URL resourceUrl = resourceBundle.getResource(resourcePath);
			if (null != resourceUrl)
				return resourceUrl;
		}
		return null;
	}

	@Override
	public void addResources(Bundle bundle) {
		resourceBundles.add(bundle);
	}

	@Override
	public void removeResources(Bundle bundle) {
		resourceBundles.remove(bundle);
	}

}
