package com.example.dpr;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.example.dpr.data.DeliveryPlan;

public class Sql2oReportr implements Reportr {
	 private Sql2o sql2o;
	 
	 public Sql2oReportr(Sql2o sql2o) {
	        this.sql2o = sql2o;
	 }

	@Override
	public List<DeliveryPlan> getReport(String customer, String part, String depot, Date startDueDate, Date endDueDate,
			Date startDeliveryDate, Date endDeliveryDate, long offset, int limit, List<String> sortOn) {
		
		System.out.println(sortOn.stream().collect(Collectors.joining(",")));
		try (Connection conn = sql2o.open()) {
			return conn.createQuery("select * from DeliveryPlan where 1=1"
					+ " and (:customer is null or customer=:customer)"
					+ " and (:part is null or part=:part)"
					+ " and (:depot is null or depot=:depot)"
					+ " and (duedate between coalesce(:start_due_date, '1000-01-01') and coalesce(:end_due_date, '9999-12-31'))"
					+ " and (deliverydate between coalesce(:start_delivery_date, '1000-01-01') and coalesce(:end_delivery_date, '9999-12-31'))"
					+ " order by " + sortOn.stream().collect(Collectors.joining(",")) 
					+ " limit :limit offset :offset")
			.addParameter("customer", customer)
			.addParameter("part", part)
			.addParameter("depot", depot)
			.addParameter("start_due_date",startDueDate)
			.addParameter("end_due_date",endDueDate)
			.addParameter("start_delivery_date",startDeliveryDate)
			.addParameter("end_delivery_date",endDeliveryDate)
			.addParameter("limit", limit)
			.addParameter("offset", offset)
			.executeAndFetch(DeliveryPlan.class);
		}
	}
	 
}

