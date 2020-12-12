package com.kardex.quartz.task;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * Job de Prueba
 * @author ngonzalez
 *
 */
public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		System.out.println("Ejecutando Job de Desarrollo: " + new Date());
	}
}