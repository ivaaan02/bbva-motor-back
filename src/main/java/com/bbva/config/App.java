package com.bbva.config;

import com.bbva.ws.impl.ExampleWS;

import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class App extends Application {

    private Set<Class<?>> classes = new HashSet<>();
    private Set<Object> singletons = Collections.emptySet();

    public App() {
        classes.add(ExampleWS.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}