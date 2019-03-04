package com.example.hello2.random;

import java.util.Random;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RealRandom implements MyRandom {
	
	public String number;

	@Override
	public int nextInt(int bound) {
		return new Random().nextInt(bound);
	}
	
}
