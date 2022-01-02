package controller.impl;

import java.util.Random;

import controller.ShippingFeeCalculator;
import entity.order.Order;

public class ShippingFeeCalculatorImpl implements ShippingFeeCalculator {

    @Override
    public int calculateShippingFee(Order order) {
        Random rand = new Random();
        return (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
      }
    }
