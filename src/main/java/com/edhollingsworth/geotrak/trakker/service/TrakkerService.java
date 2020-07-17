package com.edhollingsworth.geotrak.trakker.service;

import com.edhollingsworth.geotrak.trakker.model.GeoTrak;

/**
 * Service interface for creating GeoTrak events.
 * 
 * @author Ed Hollingsworth
 */
public interface TrakkerService {
	GeoTrak create(final GeoTrak trak);
}
