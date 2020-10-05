package com.bbva.ws.impl;

import java.util.List;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bbva.biz.impl.DriveConnector;
import com.bbva.constants.ExampleConstants;
import com.bbva.constants.ExampleRestConstants;
import com.bbva.ws.GenericWs;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@Path(ExampleRestConstants.EXAMPLE_REST_URL)
public class ExampleWS extends GenericWs {

	@GET
	@Path(ExampleRestConstants.EXAMPLE_GET_URL)
	@Produces(MediaType.APPLICATION_JSON + GenericWs.CHARSET_UTF8)
	public Response getRequest(@Context HttpServletRequest request, @PathParam(ExampleConstants.PARAM_NAME) String name,
			@QueryParam(ExampleConstants.PARAM_SURNAME) String surname) {

		Response response;

		try {
			FileList files = DriveConnector.findFiles();
			response = generateResponse("200", "Service executed successfully", files, HttpStatusCodes.STATUS_CODE_OK);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
			response = generateResponse("500", e.getMessage(), e, HttpStatusCodes.STATUS_CODE_SERVER_ERROR);
		}

		return response;
	}
}