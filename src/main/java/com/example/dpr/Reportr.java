package com.example.dpr;

import java.sql.Date;
import java.util.List;

import com.example.dpr.data.DeliveryPlan;

public interface Reportr {

	List<DeliveryPlan> getReport(String customer, String part, String depot, Date startDueDate, Date endDueDate,
			Date startDeliveryDate, Date endDeliveryDate, long offset, int limit, List<String> sortOn) ;
	}
