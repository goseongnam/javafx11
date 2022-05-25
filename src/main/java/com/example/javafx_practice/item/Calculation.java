package com.example.javafx_practice.item;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import persistence.dto.ReqCalculationDTO;

public class Calculation {
    public static void btnChk_Calculate(String currencytmp_tmp, ChoiceBox choiceMethod, TextField txtExInput, TextField txtExOutput) {
        if (currencytmp_tmp == null || currencytmp_tmp.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("통화를 선택하지 않으셨습니다");
            alert.showAndWait();
            return;
        }
        String seletedOption = (String) choiceMethod.getValue();
        if(seletedOption == null || seletedOption.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("옵션을 선택하지 않았습니다");
            alert.showAndWait();
            return;
        }
        String exchangeOption = seletedOption.substring(0, 3);
        String currencytmp = currencytmp_tmp.substring(0, 3);
        //BKPR이 4글자라서 서버에서 서버에 BKP가 날아온경우에 무엇을 보내도록 조건을 걸어야 할듯 
        String inputCurrency = txtExInput.getText();
        if(inputCurrency == null || inputCurrency.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("입력값을 넣지않았습니다.");
            alert.showAndWait();
            return;
        }
        int currentExchange = Integer.parseInt(inputCurrency);
        ReqCalculationDTO calculationRequestDTO = new ReqCalculationDTO(currencytmp, currentExchange, exchangeOption);
        sendCalculation(calculationRequestDTO);
        receiveCalculation();
    }

    public static void sendCalculation(ReqCalculationDTO calculationRequestDTO) {
        //이 부분에 네트워크로 String currencytmp(선택통화), int currentExchange(환율계산입력값), String exchangeOption(파고살때 달라지는 옵션)
        //을 서버에게 보냄
        System.out.println(calculationRequestDTO.getCurrencytmp());
    }

    public static void receiveCalculation() {
        //서버로부터 계산 값을 받음


//        txtExOutput.setText(String.valueOf(currentExchange)); //돌려받음 값을 출력에 해당하는 텍스트필드에 출력함.
    }
}