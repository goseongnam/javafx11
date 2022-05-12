package com.example.javafx_practice.item;

import javafx.scene.control.TextField;

public class Calculation {
    public static void btnChk_Calculate(TextField txtExInput, TextField txtExOutput) {
        //여기서는 selectedTmp(선택된 통화), inputCurrency(입력한 가격)을 네트워크로 보냄
        String inputCurrency = txtExInput.getText();
        int currentExchange = Integer.parseInt(inputCurrency);


        txtExOutput.setText(String.valueOf(currentExchange));
    }
}