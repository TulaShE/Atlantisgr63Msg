/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.logic;

import com.gr63.atlantis.business.domain.Device;
import com.gr63.atlantis.business.domain.Metric;
import com.gr63.atlantis.integration.MetricDAO;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.json.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author dev
 */
@Stateful
public class MetricService implements MetricServiceLocal {

    Metric metric = new Metric();
    
    @Inject
    MetricDAO metricDAO;

    @Override
    @Remove
    public void addMetric(Date date, String value, Device device) {
        metric.setDate(date);
        metric.setValue(value);
        metric.setDeviceMetric(device);
        metricDAO.insert(metric);
        System.out.println("Sauvegarde de la metric");
    }

    @Override
    public List<Metric> getMetricsByDevice(Long deviceId) {
        List<Metric> deviceMetrics = metricDAO.getMetricsByDevice(deviceId);
        System.out.println("Metrics listed");
        return deviceMetrics;
    }
    
    @Override
    public String formattingMetricDataIntoMessage(){
        
            String message;
            
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            List<Metric> metrics = metricDAO.getAllMetrics();
        
            for(int i = 0; i < metrics.size(); i++){
                objectBuilder.add("Id", metrics.get(i).getId().toString())
                    .add("DateMetric", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")                    
                            .format(metrics.get(i).getDate()))
                    .add("Value", metrics.get(i).getValue())
                    .add("Id_Device", metrics.get(i).getDeviceMetric().getId().toString());
                
                JsonObject jsonObject = objectBuilder.build();
                arrayBuilder.add(jsonObject);
            }

            JsonArray jsonArray = arrayBuilder.build();
   
            Writer writer = new StringWriter();
            Json.createWriter(writer).write(jsonArray);
            message = writer.toString();

            System.out.println(message);
        
        return message;
    }
}
