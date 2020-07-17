package com.edhollingsworth.geotrak.trakker;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import com.edhollingsworth.geotrak.trakker.model.GeoTrak;

/**
 * This is just a convenience endpoint so we can see the messages that
 * are being pushed through Kafka, as the GraphQL interface receives
 * "createTrak" requests.  This would not be used in producction.
 * 
 * @author Ed Hollingsworth
 */
@Path("/geotrak")
public class TrakkerTapper {
	// Create a binding to the "geotrak" channel configured in application.properties
	@Inject @Channel("geotrak") Publisher<GeoTrak> traks; 

	/**
	 * HTTP GET mapping that sends messages to the requester as they
	 * appear on the stream.
	 * 
	 * @return the Publisher declared above.
	 */
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) 
    @SseElementType("text/plain") 
    public Publisher<GeoTrak> stream() { 
        return traks;
    }
}