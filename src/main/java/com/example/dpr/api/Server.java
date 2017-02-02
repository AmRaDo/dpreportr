package com.example.dpr.api;

import static spark.Spark.get;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sql2o.Sql2o;

import com.example.dpr.Reportr;
import com.example.dpr.Sql2oReportr;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;

public class Server {


	public static void main(String[] args) {
		Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/e2open", "dpuser", "dppass");
		Map<String, String> colMaps = new HashMap<String,String>();
		sql2o.setDefaultColumnMappings(colMaps);
		
		Reportr reportr = new Sql2oReportr(sql2o);
		
		get("/hello", (request, response) -> "Hello World!");
		get("/report", (request, response) ->{
			
			String customer = request.queryParams("customer");
			String part =request.queryParams("part");
			String depot =request.queryParams("depot");
			Date startDueDate = (request.queryParams("startDueDate") != null ? Date.valueOf(request.queryParams("startDueDate")) : null);
			Date endDueDate = (request.queryParams("endDueDate") != null ? Date.valueOf(request.queryParams("endDueDate")) : null);
			Date startDeliveryDate = (request.queryParams("startDeliveryDate") != null ? Date.valueOf(request.queryParams("startDueDate")) : null);
			Date endDeliveryDate = (request.queryParams("endDeliveryDate") != null ? Date.valueOf(request.queryParams("endDeliveryDate")) : null);
			
			String offsetStr = request.queryParams("offset");
			long offset = 0;
			if(offsetStr != null) {
				offset = Long.parseLong(offsetStr);
			}
			
			String limitStr = request.queryParams("limit");
			int limit = 50;
			if(limitStr != null) {
				limit = Integer.parseInt(limitStr);
			}
			String sortStr = request.queryParams("sort");
			List<String> sortOn = Lists.newArrayList("deliveryNo");
			
			if(sortStr != null) {
				sortOn = Lists.newArrayList(sortStr.split(","));
			}
			 response.status(200);
             response.type("application/json");
			return dataToJson( reportr.getReport(customer , part , depot, startDueDate, endDueDate, startDeliveryDate, endDeliveryDate, offset, limit, sortOn));
		});
		
		
	}
	
	public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }
}
	