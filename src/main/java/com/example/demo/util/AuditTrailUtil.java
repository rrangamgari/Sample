package com.example.demo.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import com.example.demo.model.UserActivity;
import com.example.demo.repository.UserActivityRepository;

public class AuditTrailUtil {

	@Autowired
	private static UserActivityRepository auditTrail;

	public static void audit(String summary, String description, Long user) {
		try {
			UserActivity userActivity = new UserActivity();
			userActivity.setActivitySummary(summary);
			userActivity.setActivityDetail(description);
			userActivity.setCreatedBy(user);
			userActivity.setCreatedDate(new Date());
			auditTrail.save(userActivity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
