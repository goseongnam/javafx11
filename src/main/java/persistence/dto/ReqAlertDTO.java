package persistence.dto;

import com.example.javafx_practice.item.AlertObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ReqAlertDTO implements Serializable {
    private ArrayList<AlertObject> timerArrList;

    public ReqAlertDTO(ArrayList<AlertObject> timerArrList) {
        this.timerArrList = timerArrList;
    }

    public ArrayList<AlertObject> getTimerArrList() {
        return timerArrList;
    }

    public void setTimerArrList(ArrayList<AlertObject> timerArrList) {
        this.timerArrList = timerArrList;
    }
}