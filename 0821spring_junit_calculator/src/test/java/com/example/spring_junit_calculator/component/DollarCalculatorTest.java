package com.example.spring_junit_calculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

//Component Test

@SpringBootTest  // @SpringBootTest 하면 Import 빼도 됨 (통합 관리)
//@Import({MarketApi.class, DollarCalculator.class})
public class DollarCalculatorTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
//    private DollarCalculator dollarCalculator; // (1)
    private Calculator calculator;  //(2)
    //  DollarCalculator가 아닌 private Calculator calculator;를
    // 하면 굳이 init을 호출하지 않아도 됨

    @Test
    public void dollarCalculatorTest(){

        Mockito.when(marketApi.connect()).thenReturn(3000);
        // Mockito에 marketApi에 connect가 일어날 때 3000을 리턴
//        dollarCalculator.init();  // (1)

        int sum = calculator.sum(10,10);  // dollarCalculator(1) --> calculator(2)
        int minus = calculator.minus(10,10);  // dollarCalculator(1) --> calculator(2)

        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0, minus);

    }


}
