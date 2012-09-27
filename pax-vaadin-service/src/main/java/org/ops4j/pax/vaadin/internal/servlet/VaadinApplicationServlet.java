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

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

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
		protected Application getNewApplication(HttpServletRequest request) throws ServletException {
			return application;
		}

		@Override
		protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
			return application.getClass();
		}

	}
}