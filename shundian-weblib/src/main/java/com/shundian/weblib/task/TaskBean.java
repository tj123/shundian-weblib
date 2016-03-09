package com.shundian.weblib.task;

import java.lang.reflect.Method;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 在运行时需要将其配置为一个bean
 * @author TJ
 *
 */
public class TaskBean<T extends TaskBean<?>> implements ApplicationListener<ContextRefreshedEvent>{

	private TaskConfig config;

	public void setConfig(TaskConfig config) {
		this.config = config;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		@SuppressWarnings("unchecked")
		T ts =(T)this;
		Method[] methods = ts.getClass().getMethods();
		for(Method method:methods){
			 Task task = method.getAnnotation(Task.class);
			 if(task !=null){
				 
			 }
		}
	}

}
