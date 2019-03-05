package com.example.hello.random;

import java.util.Random;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Primary
public class RealRandom implements MyRandom {
	
	public String number;

	@Override
	public int nextInt(int bound) {
		throw new MyRandomException();
//		return new Random().nextInt(bound);
	}
	
}
