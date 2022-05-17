package persistence;

public class SearchRequestDTO {
    private String dateInput;
    private String forex;

    public SearchRequestDTO(String dateInput, String forex) {
        this.dateInput = dateInput;
        this.forex = forex;
    }

    public SearchRequestDTO() {
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
