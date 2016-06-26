/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rebscher.gf4_maven1.boundary;

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
import org.rebscher.gf4_maven1.entity.SmallPayload;

/**
 *
 * @author patrick
 */
@Stateless
public class SmallPayloadService {

    @PersistenceContext
    EntityManager em;

    @Inject
    Logger tracer;
    
    public static final String PAYLOAD_ID = "payload_id";
    public static final String PAYLOAD = "smallpayload";
    public static final String PAYLOAD2 = "smallpayload2";
    

    public SmallPayload find(long smallPayloadId) {
        return this.em.find(SmallPayload.class, smallPayloadId);
    }

    public JsonObject store(SmallPayload request) {
        tracer.info("smallpayload arrived: " + request);
        SmallPayload smallpayload = em.merge(request);
        tracer.info("smallpayload stored: " + request);
        return convert(smallpayload);
    }

    public JsonObject findAsJson(int smallPayloadId) {
        return convert(find(smallPayloadId));
    }

    public List<SmallPayload> all() {
        return this.em.createNamedQuery(SmallPayload.findAll).
                getResultList();
    }

    public JsonArray allAsJson() {
        Collector<JsonObject, ?, JsonArrayBuilder> jsonCollector
                = Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add,
                        (left, right) -> {
                            left.add(right);
                            return left;
                        });
        return all().stream().map(this::convert).
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
