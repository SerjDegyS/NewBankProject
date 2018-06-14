package Entity;

import org.junit.experimental.theories.DataPoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "EXCHANGE_RATE")
public class ExchangeRate extends Entyti {

    @Column(name = "USD", nullable = false)
    private double usd;
    @Column(name = "EUR", nullable = false)
    private double eur;
    @Column(name = "UAH", nullable = false)
    private double uah;

    @Column(name = "DATA", nullable = false)
    private Date data;

    public ExchangeRate() { }

    public ExchangeRate(double usd, double eur,
                        double uah) {
        this.usd = usd;
        this.eur = eur;
        this.uah = uah;
        this.data = new Date();
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getEur() {
        return eur;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    public double getUah() {
        return uah;
    }

    public void setUah(double uah) {
        this.uah = uah;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "data=" + data +
                ", \n\tusd= " + usd +
                ", \n\teur= " + eur +
                ", \n\tuah= " + uah +
                '}';
    }
}
