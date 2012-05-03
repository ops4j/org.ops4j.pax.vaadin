package org.pax.vaadin.internal.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

public class VaadinApplicationServlet extends AbstractApplicationServlet {

    private static final long serialVersionUID = 1L;
	private Application application;
    
    public VaadinApplicationServlet(Application application) {
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
