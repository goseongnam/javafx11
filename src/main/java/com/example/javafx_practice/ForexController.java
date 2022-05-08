package com.example.javafx_practice;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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

    public void searchForex(ActionEvent actionEvent) {
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