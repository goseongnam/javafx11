package persistence;

import java.io.Serializable;

public class CalculationRequestDTO implements Serializable {
    private String currencytmp;
    private long currentExchange;
    private String exchangeOption;

    public CalculationRequestDTO(String currencytmp, long currentExchange, String exchangeOption) {
        this.currencytmp = currencytmp;
        this.currentExchange = currentExchange;
        this.exchangeOption = exchangeOption;
    }

    public String getCurrencytmp() {
        return currencytmp;
    }

    public void setCurrencytmp(String currencytmp) {
        this.currencytmp = currencytmp;
    }

    public long getCurrentExchange() {
        return currentExchange;
    }

    public void setCurrentExchange(int currentExchange) {
        this.currentExchange = currentExchange;
    }

    public String getExchangeOption() {
        return exchangeOption;
    }

    public void setExchangeOption(String exchangeOption) {
        this.exchangeOption = exchangeOption;
    }
}