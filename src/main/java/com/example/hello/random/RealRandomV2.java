package com.example.hello.random;

import java.util.Random;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RealRandomV2 implements MyRandom {
	
	public String number;

	@Override
	public int nextInt(int bound) {
		return 5555;
	}
	
}
