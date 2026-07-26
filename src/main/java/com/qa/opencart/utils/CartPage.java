package com.qa.opencart.utils;

import org.openqa.selenium.By;

public class CartPage {

    int i = 10;
    public By cartLocator = By.id("cart");

    public void cart(){
        System.out.println("CartPage Method");
    }
}
