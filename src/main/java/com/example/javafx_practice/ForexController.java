package com.example.javafx_practice;

import api.Api;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ForexController implements Initializable {

    public TextField txtDateInput;
    public TextField txtDateOutput;
    public Button btnDateOk;
    public Button btnMonth;
    public Button btnYear;
    public TextField txtAverageOutput;
    public TextField txtStandardDeviationOutput;
    public  LineChart<Number, Number> chart;
    ObservableList<XYChart.Series<Number, Number>> list;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtDateInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtDateInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void searchForex(ActionEvent actionEvent) throws MalformedURLException {
        LocalDate rawLocalDate =LocalDate.now();
        String nowDate = rawLocalDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String title=StageStore.stage.getTitle();
        String forex =title.substring(0,3);
        System.out.println("forex:"+ forex);
        String DateInput="";

        if (txtDateInput.getText()!=null){
            DateInput = txtDateInput.getText();
            if(DateInput.length()!=8){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("날짜는 8자리로 입력해야 합니다.");
                alert.showAndWait();
                return;
            }
        } else {
            return; // null 값이라서 종료함.
        }
        double forexValue= 1100d;
        boolean isError= false;
        if (true) {
       //if (txtDateInput.getText().equals(nowDate)){
            //db에 접근하지 않고 바로 api 호출.
            //만약 결과값이 null이면 Alert 실행
            // 주말이거나 공휴일이라서 환율 조회 불가능.
            Api api =new Api();
            forexValue = api.apiExecutuon("20220506",forex);
            if (forexValue!=0){
                txtDateOutput.setText(Double.toString(forexValue));
                return;
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("주말이거나 공휴일을 입력하셨습니다.");
                alert.showAndWait();
                return;
            }

        }
        // 서버에 DateInput를 넘겨준다. send()함수 사용.

        // receive()함수를 통해서 해당 날짜의 환율을 가져온다.
        // 만약 에러가 있다면 isError=true로 한다.
        if (isError){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("조회할 수 없는 날짜를 입력하셨습니다.");
            alert.showAndWait();
            return;
        }

        txtDateOutput.setText(Double.toString(forexValue));

    }

    public void viewGraphMonth(ActionEvent actionEvent) {

        chart.getData().clear();
        list = FXCollections.observableArrayList();

        XYChart.Series series = new XYChart.Series();
        series.setName("Month Data");
//        series.getData().add(new XYChart.Data(1, 6));
//        series.getData().add(new XYChart.Data(2, 4));
//        series.getData().add(new XYChart.Data(3, 5));
//        series.getData().add(new XYChart.Data(4, 4));
//        series.getData().add(new XYChart.Data(5, 4));
//        series.getData().add(new XYChart.Data(6, 6));
//        series.getData().add(new XYChart.Data(7, 2));
//        series.getData().add(new XYChart.Data(8, 5));
//        series.getData().add(new XYChart.Data(9, 3));
//        series.getData().add(new XYChart.Data(10, 7));
//        series.getData().add(new XYChart.Data(11, 9));
//        series.getData().add(new XYChart.Data(12, 5));

        int xDay = 0;
        int yPrice = 0;
        list.addAll(series);
        chart.setData(list);

    }

    public void viewGraphYear(ActionEvent actionEvent) {

        chart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName("Year Data");
        series.getData().add(new XYChart.Data(1, 3));
        series.getData().add(new XYChart.Data(2, 4));
        series.getData().add(new XYChart.Data(3, 5));
        series.getData().add(new XYChart.Data(4, 4));
        series.getData().add(new XYChart.Data(5, 4));
        series.getData().add(new XYChart.Data(6, 6));
        series.getData().add(new XYChart.Data(7, 2));
        series.getData().add(new XYChart.Data(8, 5));
        series.getData().add(new XYChart.Data(9, 3));
        series.getData().add(new XYChart.Data(10, 7));
        series.getData().add(new XYChart.Data(11, 9));
        series.getData().add(new XYChart.Data(12, 5));
        list.addAll(series);
        chart.setData(list);
    }

    public void moveMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 350);
        StageStore.stage.setTitle("Main");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }
    
}