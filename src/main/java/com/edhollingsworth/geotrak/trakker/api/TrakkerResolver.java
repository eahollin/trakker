package com.edhollingsworth.geotrak.trakker.api;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;

import com.edhollingsworth.geotrak.trakker.model.GeoTrak;
import com.edhollingsworth.geotrak.trakker.service.TrakkerService;

/**
 * Define the GraphQL API for sending GeoTrak event.
 * 
 * @author Ed Hollingsworth
 */
@GraphQLApi
public class TrakkerResolver {
	@Inject
	private TrakkerService tService;
	
	public TrakkerResolver(TrakkerService tService) {
		this.tService = tService;
	}

	@Mutation("createTrak")
	public GeoTrak createTrak(@Name("trak") GeoTrak trak) {
		return this.tService.create(trak); // tService handles sending to Kafka
	}
}
