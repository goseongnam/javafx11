package com.example.javafx_practice;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        txtDateOutput.setDisable(true);
        txtAverageOutput.setDisable(true);
        txtStandardDeviationOutput.setDisable(true);
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

    public void searchForex(ActionEvent actionEvent) throws MalformedURLException {//서버로 데이터 전송 및 수신

        String title=StageStore.stage.getTitle();
        String forex =title.substring(0,3);
        System.out.println("forex:"+ forex);
        String DateInput="";

        DateInput = txtDateInput.getText();
        if (txtDateInput.getText()!=null){
            if(DateInput.length()!=8){//날짜 입력이 올바른지 체킹
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

        // 서버에 DateInput를 넘겨준다. send()함수 사용
        // 이때 파라매터로 String DateInput 날짜 ex)20200804, String forex 외환 종류 ex)USD 두개를 서버에 넘긴다
        // receive()함수를 통해서 해당 날짜의 환율을 가져온다.
        // 만약 에러가 있다면 isError=true로 한다.
        if (isError){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("조회할 수 없는 날짜를 입력하셨습니다(존재하지 않는 날짜 혹은 휴일).");
            alert.showAndWait();
            return;
        }

        txtDateOutput.setText(Double.toString(forexValue));

    }

    public void viewGraphMonth(ActionEvent actionEvent) {//서버로 데이터 전송 및 수신

        String title=StageStore.stage.getTitle();
        String forex =title.substring(0,3);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        String endDate = df.format(cal.getTime());
        cal.add(Calendar.MONTH, -1);
        String startDate =  df.format(cal.getTime());

        System.out.println("current: " + startDate);
        System.out.println("after: " + endDate);
        // send 로 서버에 startDate.endDate,Forex 파라매터로 넘겨줌 ex>20220410 20220510 USD
        // 이후로 receive() 한 이후에 그래프 그리기 이때 받는 데이터는 ArrayList<String> 이고 각 string 타입에는 시작날짜 부터 각각의 날짜의 가격이 저장되어있음;

        ArrayList<String> arrayListPrice= new ArrayList<>();
        arrayListPrice.add("1000");
        arrayListPrice.add("2000");
        arrayListPrice.add("1500");
        arrayListPrice.add("2000");
        arrayListPrice.add("1100");
        arrayListPrice.add("1500");
        //아직 연결 안됬으니 샘플 데이터 지정.


        chart.getData().clear();
        list = FXCollections.observableArrayList();

        XYChart.Series series = new XYChart.Series();
        series.setName("Month Data");
        for (int i=0;i<arrayListPrice.size();i++){
            series.getData().add(new XYChart.Data(i,Double.valueOf(arrayListPrice.get(i))));
        }


        list.addAll(series);
        chart.setData(list);

    }

    public void viewGraphYear(ActionEvent actionEvent) {//서버로 데이터 전송 및 수신

        String title=StageStore.stage.getTitle();
        String forex =title.substring(0,3);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        String endDate = df.format(cal.getTime());
        cal.add(Calendar.YEAR, -1);
        String startDate =  df.format(cal.getTime());

        System.out.println("current: " + startDate);
        System.out.println("after: " + endDate);
        // send 로 서버에 startDate.endDate,Forex 파라매터로 넘겨줌 ex>20220410 20220510 USD
        // 이후로 receive() 한 이후에 그래프 그리기 이때 받는 데이터는 ArrayList<String> 이고 각 string 타입에는 시작날짜 부터 각각의 날짜의 가격이 저장되어있음;

        ArrayList<String> arrayListPrice= new ArrayList<>();
        arrayListPrice.add("2000");
        arrayListPrice.add("1000");
        arrayListPrice.add("2500");
        arrayListPrice.add("1000");
        arrayListPrice.add("2100");
        arrayListPrice.add("1500");
        //아직 연결 안됬으니 샘플 데이터 지정.


        chart.getData().clear();
        list = FXCollections.observableArrayList();

        XYChart.Series series = new XYChart.Series();
        series.setName("Year Data");
        for (int i=0;i<arrayListPrice.size();i++){
            series.getData().add(new XYChart.Data(i,Double.valueOf(arrayListPrice.get(i))));
        }


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