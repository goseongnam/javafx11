package persistence;

public class ReqSearchDTO {
    private String dateInput;
    private String forex;

    public ReqSearchDTO(String dateInput, String forex) {
        this.dateInput = dateInput;
        this.forex = forex;
    }

    public ReqSearchDTO() {
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public String getForex() {
        return forex;
    }

    public void setForex(String forex) {
        this.forex = forex;
    }
}
