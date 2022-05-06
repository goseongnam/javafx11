package com.example.javafx_practice;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MainController implements Initializable {
    @FXML
    public ListView lstNationalChange;
    public ListView lstNationalRate;

    //즐겨찾기 버튼
    public Button btnBookmarkAdd;
    //즐겨찾기 버튼5개
    public Button btnBookmark1;
    public Button btnBookmark2;
    public Button btnBookmark3;
    public Button btnBookmark4;
    public Button btnBookmark5;
    //선택된 통화 임시저장 변수
    public String currencytmp = null;
    public Button btnBookmarkDelete;


    String[] currencyArrayList = {"AED 아랍에미리트 디르함", "AUD 호주 달러", "BHD 바레인 디나르", "BND 브루나이 달러", "CAD 캐나다 달러", "CHF 스위스 프랑",
            "CNH 위안화", "DKK 덴마아크 크로네", "EUR 유로", "GBP 영국 파운드", "HKD 홍콩 달러", "IDR 인도네시아 루피아", "JPY 일본 옌", "KWD 쿠웨이트 디나르",
            "MYR 말레이지아 링기트", "NOK 노르웨이 크로네", "NZD 뉴질랜드 달러", "SAR 사우디 리얄", "SEK 스웨덴 크로나", "SGD 싱가포르 달러", "THB 태국 바트", "USD 미국 달러"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String arrForex[] = new String[5];
        File file = new File("C:\\fxfile\\bookmark.txt");
        String path = "C:\\fxfile"; //폴더 경로
        try {
            readFile(file,path,arrForex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadBookmark(arrForex);
        lstNationalChange.setItems(FXCollections.observableArrayList(currencyArrayList));
        lstNationalChange.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String strItem = (String) lstNationalChange.getSelectionModel().getSelectedItem();
                currencytmp = strItem;
            }
        });

        lstNationalRate.setItems(FXCollections.observableArrayList(currencyArrayList));
        lstNationalRate.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });
    }

    public void btnBookmark(ActionEvent actionEvent) throws IOException {

            if(currencytmp == null) {
                return;
            }
            else {
                String arr[]=new String[]{"","","","",""};
                File file = new File("C:\\fxfile\\bookmark.txt");
                String path = "C:\\fxfile"; //폴더 경로
                readFile(file,path,arr);
                // 파일을 읽기

                if(!checkDuplicate(arr,currencytmp)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Look, a Warning Dialog");
                    alert.setContentText("이미 즐겨찾기에 추가되어 있는 항목이거나 최대 등록개수를 초과했습니다.");

                    alert.showAndWait();
                }
                // 중복체크
                else {
                 writeFile(file);
                 String arrForex[] = new String[5];
                 readFile(file,path,arrForex);
                 loadBookmark(arrForex);
                 //파일에 쓰고 다시 읽어서 북마크 새로고침
                }
            }
    }
    public void writeFile(File file) throws IOException {

        String currencyName = currencytmp + "\n";
        FileOutputStream fos =  new FileOutputStream(file,true);
        fos.write(currencyName.getBytes());
        fos.close();
    }
    public void writeFile(File file,String[] arr) throws IOException {
        new FileOutputStream(file).close();
        FileOutputStream fos =  new FileOutputStream(file,true);
        String item="";
        for (int i=0;i<arr.length;i++){
            if (arr[i]!=null&&!arr[i].equals("")) {
                item=arr[i]+"\n";
                fos.write(item.getBytes());
            }
        }
        fos.close();
    }

    public void loadBookmark(String[] arrForex){
        Button[] btnBookmarkArr= new Button[5];
        btnBookmarkArr[0]=btnBookmark1;
        btnBookmarkArr[1]=btnBookmark2;
        btnBookmarkArr[2]=btnBookmark3;
        btnBookmarkArr[3]=btnBookmark4;
        btnBookmarkArr[4]=btnBookmark5;

        for(int i=0;i<5;i++){
            if (arrForex[i]==null) {
                String str = "즐겨찾기" + Integer.toString(i+1);
                btnBookmarkArr[i].setText(str);
            }
            else btnBookmarkArr[i].setText(arrForex[i]);
        }
    }


    public void readFile(File file, String path,String[] arr) throws IOException {
        File Folder = new File(path);
        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더 생성합니다.
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        file.createNewFile();//파일 생성, 만약 이미 있으면 있는거 사용.

        FileReader filereader = new FileReader(file);
        BufferedReader bufReader = new BufferedReader(filereader);

        String line = "";
        int i = 0;
        while((line = bufReader.readLine()) != null){
            arr[i]=line;
            i++;
        }

        bufReader.close();
        filereader.close();
    }

    public boolean checkDuplicate(String[] arr,String test){

        if (!arr[4].equals("")) return false;//즐겨찾기 최대 개수 초과
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("")){
                count++;
            }
        }
        Set<String> set = new HashSet<String>(); // set of timeline

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]=="") break;
            set.add(arr[i]);
        }
        set.add(test);

        if (count+1!=set.size()){
            System.out.println("count:" + count+1);
            System.out.println("test: "+test);
            System.out.println("set.size: " + set.size());
            System.out.print("중복 발견");
            return false;
        }
        return true;

    }
    public void pageMove(ActionEvent actionEvent) throws IOException {// 메인에서 환율조회 화면으로 이동
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("forex.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        StageStore.stage.setTitle(currencytmp);
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }

    public void alertClick(ActionEvent actionEvent) {
    }

    public void AlertPageMove(ActionEvent actionEvent) {
    }

    public void btnBookmarkDelete(ActionEvent actionEvent) throws IOException {

        File file = new File("C:\\fxfile\\bookmark.txt");
        String path = "C:\\fxfile"; //폴더 경로
        String arrForex[] = new String[5];
        readFile(file,path,arrForex);
        String[] result = DeleteBookmark(arrForex,currencytmp);
        writeFile(file,result);
        for (int i=0;i<result.length;i++){
            System.out.println("arrForex[i]" + result[i]);
        }
        loadBookmark(result);
    }

    public String[] DeleteBookmark(String[] arrForex, String target){
        String[] result= new String[5];

        for (int i=0;i<result.length;i++){
            if(arrForex[i].equals(target)) {
                for (int j=i; j+1<result.length;j++){
                    result[j]=arrForex[j+1];
                }
                break;
            }
            else result[i]=arrForex[i];
        }
        return result;
    }
}