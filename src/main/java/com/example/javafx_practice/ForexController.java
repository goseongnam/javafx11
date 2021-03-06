package com.example.javafx_practice;

import com.example.javafx_practice.item.StageStore;
import com.example.javafx_practice.item.WindowSize;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class ForexController implements Initializable {

    public TextField txtDateInput;
    public TextArea txtDateOutput;
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

    public void searchForex(ActionEvent actionEvent) throws MalformedURLException {//????????? ????????? ?????? ??? ??????

        String title= StageStore.stage.getTitle();
        String forex =title.substring(0,3);
        System.out.println("forex:"+ forex);
        String DateInput="";

        DateInput = txtDateInput.getText();
        if (txtDateInput.getText()!=null){
            if(DateInput.length()!=8){//?????? ????????? ???????????? ??????
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("????????? 8????????? ???????????? ?????????.");
                alert.showAndWait();
                return;
            }
        } else {
            return; // null ???????????? ?????????.
        }
        double forexValue= 1100d;
        boolean isError= false;

        // ????????? DateInput??? ????????????. send()?????? ??????
        // ?????? ??????????????? String DateInput ?????? ex)20200804, String forex ?????? ?????? ex)USD ????????? ????????? ?????????
        // receive()????????? ????????? ?????? ????????? ????????? ????????????.
        // ?????? ????????? ????????? isError=true??? ??????.
        if (isError){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("????????? ??? ?????? ????????? ?????????????????????(???????????? ?????? ?????? ?????? ??????).");
            alert.showAndWait();
            return;
        }

        txtDateOutput.setText(Double.toString(forexValue));

    }

    public void viewGraphMonth(ActionEvent actionEvent) {//????????? ????????? ?????? ??? ??????

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
        // send ??? ????????? startDate.endDate,Forex ??????????????? ????????? ex>20220410 20220510 USD
        // ????????? receive() ??? ????????? ????????? ????????? ?????? ?????? ???????????? ArrayList<String> ?????? ??? string ???????????? ???????????? ?????? ????????? ????????? ????????? ??????????????????;

        ArrayList<String> arrayListPrice= new ArrayList<>();
        arrayListPrice.add("1000");
        arrayListPrice.add("2000");
        arrayListPrice.add("1500");
        arrayListPrice.add("2000");
        arrayListPrice.add("1100");
        arrayListPrice.add("1500");
        //?????? ?????? ???????????? ?????? ????????? ??????.


        chart.getData().clear();
        list = FXCollections.observableArrayList();

        XYChart.Series series = new XYChart.Series();
        series.setName("Month Data");
        double totalPrice = 0d;

        for (int i=0;i<arrayListPrice.size();i++){
            double price = Double.valueOf(arrayListPrice.get(i));//bkpr???
            series.getData().add(new XYChart.Data(i,price));
            totalPrice += price;

        }
        DecimalFormat form = new DecimalFormat("#.##");
        Double average= totalPrice/arrayListPrice.size();
        txtAverageOutput.setText("??????: "+form.format(average));//??????

        double dsum=0d;
        for(int i=0; i<arrayListPrice.size(); i++){
            double price = Double.valueOf(arrayListPrice.get(i));//bkpr???
            dsum += (price-average)*(price-average);
        }

        double sd = Math.sqrt(dsum/arrayListPrice.size());
        txtStandardDeviationOutput.setText("????????????: "+form.format(sd));//????????????

        list.addAll(series);
        chart.setData(list);

    }

    public void viewGraphYear(ActionEvent actionEvent) {//????????? ????????? ?????? ??? ??????

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
        // send ??? ????????? startDate.endDate,Forex ??????????????? ????????? ex>20220410 20220510 USD
        // ????????? receive() ??? ????????? ????????? ????????? ?????? ?????? ???????????? ArrayList<String> ?????? ??? string ???????????? ???????????? ?????? ????????? ????????? ????????? ??????????????????;

        ArrayList<String> arrayListPrice= new ArrayList<>();
        arrayListPrice.add("2000");
        arrayListPrice.add("1000");
        arrayListPrice.add("2500");
        arrayListPrice.add("1000");
        arrayListPrice.add("2100");
        arrayListPrice.add("1500");
        //?????? ?????? ???????????? ?????? ????????? ??????.


        chart.getData().clear();
        list = FXCollections.observableArrayList();

        XYChart.Series series = new XYChart.Series();
        series.setName("Year Data");
        double totalPrice = 0d;

        for (int i=0;i<arrayListPrice.size();i++){
            double price = Double.valueOf(arrayListPrice.get(i));//bkpr???
            series.getData().add(new XYChart.Data(i,price));
            totalPrice += price;

        }
        DecimalFormat form = new DecimalFormat("#.##");
        Double average= totalPrice/arrayListPrice.size();
        txtAverageOutput.setText("??????: "+form.format(average));//??????

        double dsum=0d;
        for(int i=0; i<arrayListPrice.size(); i++){
            double price = Double.valueOf(arrayListPrice.get(i));//bkpr???
            dsum += (price-average)*(price-average);
        }

        double sd = Math.sqrt(dsum/arrayListPrice.size());
        txtStandardDeviationOutput.setText("????????????: "+form.format(sd));//????????????

        list.addAll(series);
        chart.setData(list);
    }

    public void moveMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowSize.MAIN_X, WindowSize.MAIN_Y);
        StageStore.stage.setTitle("Main");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }
    
}