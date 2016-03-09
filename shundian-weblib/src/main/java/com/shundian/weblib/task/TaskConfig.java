package com.shundian.weblib.task;

import java.util.Map;

/**
 * 任务的都和写权限的配置
 * @author TJ
 *
 */
public interface TaskConfig {
	
	Map<String,Object> read();
	
	void wirte(Map<String,Object> config);
	
}
