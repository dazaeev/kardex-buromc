package com.kardex.quartz;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.kardex.quartz.task.CronTriggerJob;
import com.kardex.quartz.task.SimpleJob;

/**
 * @author ngonzalez
 *
 */
@Configuration
public class SchedulerConfig {

	/**
	 * @param applicationContext
	 * @return
	 */
	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {
		SpringJobFactory jobFactory = new SpringJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	/**
	 * @param jobFactory
	 * @param simpleJobTrigger
	 * @return
	 * @throws IOException
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, Trigger simpleJobTrigger)
			throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setJobFactory(jobFactory);
		factory.setQuartzProperties(quartzProperties());
		factory.setTriggers(simpleJobTrigger);
		System.out.println("Starting Jobs....");
		return factory;
	}
	
	/**
	 * Programar CRON por simplejob.frequency (segundos)
	 * @param jobDetail
	 * @param frequency
	 * @return
	 */
	// @Bean
	// public SimpleTriggerFactoryBean simpleJobTrigger(@Qualifier("simpleJobDetail") JobDetail jobDetail, @Value("${simplejob.frequency}") long frequency) {
	// 	System.out.println("simpleJobTrigger");

	// 	SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
	// 	factoryBean.setJobDetail(jobDetail);
	// 	factoryBean.setStartDelay(0L);
	// 	factoryBean.setRepeatInterval(frequency);
	// 	factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
	// 	return factoryBean;
	// }
	
	/**
	 * Programar CRON por expreción
	 * @return
	 */
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(@Value("${simplejob.ambient}") String ambient, @Value("${simplejob.cron.expression}") String expression) {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(simpleJobDetail(ambient).getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("mytrigger");
		stFactory.setGroup("mygroup");
		stFactory.setCronExpression(expression);
		return stFactory;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	/**
	 * @return
	 */
	@Bean
	public JobDetailFactoryBean simpleJobDetail(@Value("${simplejob.ambient}") String ambient) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		// Validar en que ambiente nos encontramos
		if("DESA".equals(ambient)) {
			factoryBean.setJobClass(SimpleJob.class);
		} else if("PROD".equals(ambient)) {
			factoryBean.setJobClass(CronTriggerJob.class);
		}
		factoryBean.setDurability(true);
		return factoryBean;
	}
}