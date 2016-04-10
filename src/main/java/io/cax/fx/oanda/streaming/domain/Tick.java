package io.cax.fx.oanda.streaming.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;

/**
 * Created by cq on 10/4/16.
 */
@JsonRootName(value = "tick")
public class Tick {

    //{"tick":{"instrument":"EUR_USD","time":"2016-04-08T20:59:58.540499Z","bid":1.13929,"ask":1.14029}}
    private String instrument;

    @JsonProperty("time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.S", locale = "ENGLISH")
    private Date dateTime;

    private double bid;

    private double ask;

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tick{");
        sb.append("instrument='").append(instrument).append('\'');
        sb.append(", dateTime=").append(dateTime);
        sb.append(", bid=").append(bid);
        sb.append(", ask=").append(ask);
        sb.append('}');
        return sb.toString();
    }
}
