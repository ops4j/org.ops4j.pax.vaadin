package org.ops4j.pax.vaadin.internal.servlet;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ops4j.pax.swissbox.core.ContextClassLoaderUtils;
import org.osgi.framework.Bundle;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;
import com.vaadin.terminal.gwt.server.ApplicationServlet;

public class VaadinApplicationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ClassLoader classLoader;
	private Servlet servlet;

	public VaadinApplicationServlet(Application application) {
		classLoader = application.getClass().getClassLoader();
		servlet = new AppServlet(application);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		service(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		service(req, resp);
	}

	@Override
	public void service(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {

		try {
			ContextClassLoaderUtils.doWithClassLoader(classLoader,
					new Callable<Void>() {

						public Void call() throws Exception {
							servlet.service(request, response);
							return null;
						}

					});
		} catch (ServletException e) {
			// re-thrown
			throw e;
		} catch (RuntimeException e) {
			// re-thrown
			throw e;
		} catch (Exception ignore) {
			// ignored as it should never happen
		}
	}

	@Override
	public void init(final ServletConfig config) throws ServletException {
		try {
			ContextClassLoaderUtils.doWithClassLoader(classLoader,
					new Callable<Void>() {

						public Void call() throws Exception {
							servlet.init(config);
							return null;
						}

					});
		} catch (ServletException e) {
			// re-thrown
			throw e;
		} catch (RuntimeException e) {
			// re-thrown
			throw e;
		} catch (Exception ignore) {
			// ignored as it should never happen
		}
	}

	private class AppServlet extends AbstractApplicationServlet {

		private final Application application;

		public AppServlet(Application application) {
			this.application = application;
		}

		@Override
		protected Application getNewApplication(HttpServletRequest request)
				throws ServletException {
			return application;
		}

		@Override
		protected Class<? extends Application> getApplicationClass()
				throws ClassNotFoundException {
			return application.getClass();
		}

	}
}