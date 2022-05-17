package persistence.dto;

import java.util.ArrayList;

public class ResGraphDTO {
    private ArrayList<String> list;

    public ResGraphDTO(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}
