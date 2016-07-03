/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rebscher.appservertest.tracing.boundary;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author patrick
 */
public class LoggerExposer {
    @Produces
    public Logger expose(InjectionPoint ip) {
        String loggerName = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(loggerName);
    }
}

