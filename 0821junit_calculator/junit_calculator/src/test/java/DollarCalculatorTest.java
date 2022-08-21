import org.example.Calculator;
import org.example.DollarCalculator;
import org.example.KrwCalculator;
import org.example.MarketApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class) // Mocking을 할 수 있는 환경을 만듬
public class DollarCalculatorTest {

    @Mock
    public MarketApi marketApi;
    // MarketApi Mocking 처리

    @BeforeEach
    public void init(){
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }
    // marketAp0i connect()가 일어날때 나는 1100원이 아니라 내가 원하는 3000원을 Return 하갰더

    @Test
    public void testHello(){
        System.out.println("hello");
    }

    @Test
    public void KrwTest() {
        Calculator calculator = new Calculator(new KrwCalculator());
        System.out.println(calculator.sum(10, 10));
    }

    @Test
    public void dollarTest(){
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculatorDollar = new Calculator(dollarCalculator);
        System.out.println(calculatorDollar.sum(10,10));

        Assertions.assertEquals(22000, calculatorDollar.sum(10,10));
        Assertions.assertEquals(0, calculatorDollar.minus(10,10));
    }

    @Test
    public void mockTest(){
        // marketapi를 목처리해놓은 걸로 넣음
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculatorDollar = new Calculator(dollarCalculator);
        System.out.println(calculatorDollar.sum(10,10));

        Assertions.assertEquals(60000, calculatorDollar.sum(10,10));
        Assertions.assertEquals(0, calculatorDollar.minus(10,10));
    }



}
