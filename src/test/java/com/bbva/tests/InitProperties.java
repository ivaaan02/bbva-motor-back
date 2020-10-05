package com.bbva.tests;

import com.fga.biz.impl.PropertyBiz;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InitProperties {

	private static final LocalServiceTestHelper helper1 = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	private static final LocalServiceTestHelper helper2 = new LocalServiceTestHelper(new LocalMemcacheServiceTestConfig());
	
	private static final String initParam = "app.properties,fga.properties";
	
	public final static void init() {
		helper1.setUp();
		helper2.setUp();
		String[] propertiesList = initParam.trim().split(",");
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		List<Properties> properties = new ArrayList<Properties>();
		InputStream configStream = null;
		Properties p = null;
		for (String property : propertiesList) {
			configStream = contextClassLoader.getResourceAsStream(property);
			p = new Properties();
			try {
				p.load(configStream);
				properties.add(p);
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
		PropertyBiz.initResources(properties);   
	}
 	
	
}
