package persistence;

public class GraphDTO {
    private String startDate;
    private String endDate;
    private String forex;

    public GraphDTO(String startDate, String endDate, String forex) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.forex = forex;
    }

    public GraphDTO() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getForex() {
        return forex;
    }

    public void setForex(String forex) {
        this.forex = forex;
    }
}