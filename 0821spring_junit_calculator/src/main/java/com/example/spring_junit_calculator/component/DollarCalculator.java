package com.example.spring_junit_calculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component // Spring에 의해 Bean으로 관리 되어짐
@RequiredArgsConstructor
public class DollarCalculator implements ICalculator {

    private int price = 1;
    private final MarketApi marketApi;

    public void init(){
        this.price = marketApi.connect();
    }

    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x+y;
    }

    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;
        return x-y;
    }
}
