package persistence.dto;

import java.io.Serializable;

public class ResCalculationDTO implements Serializable {
    private double resultExchange;

    public ResCalculationDTO(double resultExchange) {
        this.resultExchange = resultExchange;
    }

    public double getResultExchange() {
        return resultExchange;
    }

    public void setResultExchange(double resultExchange) {
        this.resultExchange = resultExchange;
    }
}