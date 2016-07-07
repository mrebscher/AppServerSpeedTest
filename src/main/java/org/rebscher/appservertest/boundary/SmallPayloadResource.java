/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rebscher.appservertest.boundary;

import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.rebscher.appservertest.entity.SmallPayload;

/**
 *
 * @author mrebscher
 */
@Stateless
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("smallpayload")
public class SmallPayloadResource {
    
    @Inject
    SmallPayloadService smallpayloadservice;

    @POST
    public Response store(SmallPayload request, @Context UriInfo info) {
        JsonObject smallpayload = smallpayloadservice.store(request);
        long id = smallpayload.getInt(SmallPayloadService.PAYLOAD_ID);
        URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).entity(smallpayload).build();
    }

    @GET
    @Path("{id}")
    public SmallPayload find(@PathParam("id") long payloadId) {
        return smallpayloadservice.find(payloadId);
    }

    @GET
    public Response getFiveHundred() {
        JsonArray registrationList = this.smallpayloadservice.allAsJson();
        if (registrationList == null || registrationList.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(registrationList).build();
    }

    @GET
    @Path("{id}/dummy")
    public Response dummy(@PathParam("id") int payloadId, @Context UriInfo info) {
        JsonObject smallpayload = smallpayloadservice.store(new SmallPayload(payloadId));
        long id = smallpayload.getInt(SmallPayloadService.PAYLOAD_ID);
        URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).entity(smallpayload).build();
    }
}
