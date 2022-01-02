package controller.impl;

import java.util.Random;

import controller.ShippingFeeCalculator;
import entity.order.Order;

public class ShippingFeeWithWeightCalculatorImpl implements ShippingFeeCalculator {
    
	private final int LENGTH = 610;
    private final int WIDTH = 270;
    private final int HEIGHT = 320;
    private final int COEFFICIENT = 5000;
    
	@Override
	public int calculateShippingFee(Order order) {
		Random rand = new Random();
        return (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() + LENGTH * WIDTH * HEIGHT / COEFFICIENT);
    }	
}
