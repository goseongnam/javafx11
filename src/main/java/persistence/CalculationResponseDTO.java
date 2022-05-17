package persistence;

import java.io.Serializable;

public class CalculationResponseDTO implements Serializable {
    private double resultExchange;

    public CalculationResponseDTO(double resultExchange) {
        this.resultExchange = resultExchange;
    }

    public double getResultExchange() {
        return resultExchange;
    }

    public void setResultExchange(double resultExchange) {
        this.resultExchange = resultExchange;
    }
}