package com.bbva.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @version 1.0
 * @author GCP Team
 * */
public class ObjectifyLoader implements ServletContextListener {

	/*
	We need to register entities as follow
	ObjectifyService.register(Entity.class);
	*/
	static {

	}

	private ServletContextEvent sce;

	/**
	 * This method is called when the context is initialized
	 *
	 * @param sce Servlet Context event
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// Initialization here

	}

	/**
	 * This method is called when the context is destroyed
	 *
	 * @param sce Servlet Context event
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// Destruction here
	}
}