package com.bbva.ws;

import com.fga.beans.ResponseEntity;
import com.google.gson.Gson;

import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericWs {

    private static final Gson gson = new Gson();

	protected static final Logger LOGGER = Logger.getLogger(GenericWs.class.getName());

    public static final String CHARSET_UTF8 = ";charset=UTF-8";

    /**
     * Method that generate a Json response to return in WS
     *
     * @param code WS response code
     * @param message WS response message
     * @param data WS response data
     * @return Response
     */
    public Response generateResponse(String code, String message, Object data, Integer httpCode) {
        return this.generateResponse(code, message, data, httpCode, gson);
    }

	/**
	 * Method that generate a Json response to return in WS
	 * 
	 * @param code WS response code
	 * @param message WS response message
     * @param data WS response data
     * @param gsonParser Parser to build json response object
	 * @return Response
	 */
	public Response generateResponse(String code, String message, Object data, Integer httpCode, Gson gsonParser) {
		Response response;

        String result;
        if (data != null) {
            if (data instanceof Exception) {
                result = buildResponse(code, message, null, gsonParser);
                LOGGER.log(Level.WARNING, result);
                response = Response.status(httpCode).entity(result).build();
            } else {
                result = buildResponse(data, gsonParser);
                response = Response.status(httpCode).entity(result).build();
            }
        } else if (message != null) {
            result = buildResponse(code, message, null, gsonParser);
            response = Response.status(httpCode).entity(result).build();
        } else {
            response = Response.status(Response.Status.NO_CONTENT).build();
        }

		return response;
	}
	
	/**
	 * Builds OData Response Error Representation
	 * 
	 * @param code Error code
	 * @param message Message
	 * @param data Data
     * @param gsonParser Gson parser
	 * @return {@link String} OData response representation
	 */
	protected String buildResponse(String code, String message, Object data, Gson gsonParser) {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>();

        responseEntity.setCode(code);
        responseEntity.setMessage(message);
        responseEntity.setData(data);

	    return gsonParser.toJson(responseEntity);
	}

    /**
     * Builds OData Response Representation
     *
     * @param data Data
     * @param gsonParser Gson parser
     * @return {@link String} OData response representation
     */
    protected String buildResponse(Object data, Gson gsonParser) {
        return gsonParser.toJson(data);
    }
}