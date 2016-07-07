/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rebscher.appservertest.boundary;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.rebscher.appservertest.entity.SmallPayload;

/**
 *
 * @author mrebscher
 */
@Stateless
public class SmallPayloadService {

    @PersistenceContext
    EntityManager em;

    @Inject
    Logger tracer;
    
    public static final String PAYLOAD_ID = "id";
    public static final String PAYLOAD = "payload";
    public static final String PAYLOAD2 = "payload2";
    

    public SmallPayload find(long smallPayloadId) {
        return this.em.find(SmallPayload.class, smallPayloadId);
    }

    public JsonObject store(SmallPayload request) {
        tracer.info("smallpayload arrived: " + request);
        /*SmallPayload smallpayload = em.merge(request);
        tracer.info("smallpayload stored: " + request);
        return convert(smallpayload); */
        // Deliberately making the code slower by retrieving the stored item from the DB.
        SmallPayload smallpayload = em.merge(request);
        tracer.info("smallpayload stored: " + request);
        return convert(find(smallpayload.getId()));
    }

    public JsonObject findAsJson(int smallPayloadId) {
        return convert(find(smallPayloadId));
    }

    public List<SmallPayload> getFiveHundred() {
        return this.em.createNamedQuery(SmallPayload.findAll)
                .setMaxResults(500)
                .getResultList();
    }

    public JsonArray allAsJson() {
        Collector<JsonObject, ?, JsonArrayBuilder> jsonCollector
                = Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add,
                        (left, right) -> {
                            left.add(right);
                            return left;
                        });
        return getFiveHundred().stream().map(this::convert).
                collect(jsonCollector).build();

    }

    /**
     * Because the application only offers JAX-RS endpoint and may offer
     * WebSockets in the future, the conversion from domain objects (entities)
     * to JsonObjects happens in the protocol-neutral boundary.
     *
     * You could also convert entities into JsonObject in the JAX-RS / WebSocket
     * endpoints in case you need the domain objects for a serverside Java web
     * framework.
     */
    JsonObject convert(SmallPayload smallpayload) {
        return Json
                .createObjectBuilder()
                .add(PAYLOAD_ID, smallpayload.getId())
                .add(PAYLOAD, smallpayload.getPayload())
                .add(PAYLOAD2, smallpayload.getPayload2())
                .build();
    }
}
