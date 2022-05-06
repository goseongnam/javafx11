package com.example.javafx_practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForexController implements Initializable {

    public TextField txtDateInput;
    public TextField txtDateOutput;
    public Button btnDateOk;
    public Button btnMonth;
    public Button btnYear;
    public TextField txtAverageOutput;
    public TextField txtStandardDeviationOutput;
    public Button txtForeignExchange;
    public LineChart chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void searchForex(ActionEvent actionEvent) {
        System.out.println("title:"+StageStore.stage.getTitle());
        String DateInput="";
        if (txtDateInput.getText()!=null){
            DateInput = txtDateInput.getText();
        } else {
            return; // null 값이라서 종료함.
        }

        // 서버에 DateInput를 넘겨준다. send()함수 사용.

        // receive()함수를 통해서 해당 날짜의 환율을 가져온다.
        txtDateOutput.setText("1100");



    }

    public void viewGraphMonth(ActionEvent actionEvent) {
    }

    public void viewGraphYear(ActionEvent actionEvent) {
    }

    public void moveMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 350);
        StageStore.stage.setTitle("Main");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }
}