package com.example.spring_junit_calculator.controller;

import com.example.spring_junit_calculator.component.Calculator;
import com.example.spring_junit_calculator.component.DollarCalculator;
import com.example.spring_junit_calculator.component.MarketApi;
import com.example.spring_junit_calculator.dto.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


// CalculatorApiController가 Calculator을 주입받고
// Calculator가 ICalculator를 주입받음 따라서 DollarCalculator 넣어줘야함
// DollarCalculator의 MarketApi는 목킹 처리해야함
@WebMvcTest(CalculatorApiController.class)  // Web에 필요한거만 로딩
@AutoConfigureWebMvc
@Import({Calculator.class, DollarCalculator.class})
public class CalculatorApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;
    // MVC를 목킹으로 테스트하겠다

    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    // Get/Delete Test
    @Test
    public void sumTest() throws Exception {
        // http://localhost:8080/api/sum

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")  // get, delete
                        .queryParam("x", "10")
                        .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());
    }

    // Post/Put Test
    @Test
    public void minusTest() throws Exception {

        Req req = new Req();
        req.setX(10);
        req.setY(10);

        // object --> json
        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/post/minus") // post, put
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)  // content에 object그대로 넣을 수 없음
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0") // result 값 확인
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result.resultCode").value("OK")  // // result 내 resultCode 값 확인
        ).andDo(MockMvcResultHandlers.print());
    }

}
