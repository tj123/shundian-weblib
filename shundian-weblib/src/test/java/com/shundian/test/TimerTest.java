package com.shundian.test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	
	
	
	
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("hehedata");
				
			}
		}, 2000,1000);
		System.out.println("hehedata1");
	}
	
	
	

}
