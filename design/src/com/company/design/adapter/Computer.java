package com.company.design.adapter;

public class Computer implements Electronic220V{
    @Override
    public void connect() {
        System.out.println("컴퓨터 220v on");
    }
}
