package com.edhollingsworth.geotrak.trakker.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import com.edhollingsworth.geotrak.trakker.model.GeoTrak;

/**
 * Implementation of the Trakker service interface.  Used for publishing
 * GeoTrak events to the outbound Kafka channel.
 * 
 * @author Ed Hollingsworth
 */
@ApplicationScoped
public class TrakkerServiceImpl implements TrakkerService {
	private static final Logger LOG = Logger.getLogger(TrakkerServiceImpl.class);
	
	// Create a binding to the outbound "geotrak" channel configured in application.properties
	@Inject @Channel("geotrak") Emitter<GeoTrak> emitter;
	
	/**
	 * Simple service method augments and sends the passed GeoTrak object
	 * to the Emitter, which was injected above.
	 * 
	 * @param trak the GeoTrak object to be augmented and sent to Kafka
	 * @returns the augmented GeoTrak, complete with UUID and timestamp
	 */
	@Override
	public GeoTrak create(final GeoTrak trak) {
		// Create a new UUID for this Trak and add current timestamp
		trak.setTrakId(UUID.randomUUID().toString());
		trak.setTimestamp(OffsetDateTime.now());
		
		// JsonbSerializer converts the GeoTrak to a String suitable for publishing to the Kafka topic
		LOG.info("Sending event to the Kafka stream: " + trak.getTrakId());
		
		emitter.send(trak);
		return trak;
	}
}
