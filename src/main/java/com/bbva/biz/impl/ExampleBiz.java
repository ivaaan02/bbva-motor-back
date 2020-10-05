package com.bbva.biz.impl;

import com.bbva.beans.ExampleBean;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleBiz {

    private static final Logger LOGGER = Logger.getLogger(ExampleBiz.class.getName());

    public ExampleBean get(String name, String surname) {
        ExampleBean exampleBean;

        LOGGER.log(Level.INFO, "Name: {0}, Surname: {1}", new Object[]{name, surname});

        exampleBean = new ExampleBean();
        exampleBean.setText("GET request for ExampleWS");
        exampleBean.setName(name);
        exampleBean.setSurname(surname);

        return exampleBean;
    }
}
