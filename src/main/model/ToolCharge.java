package main.model;

import java.math.BigDecimal;

public class ToolCharge {

    public ToolCharge(BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    private final BigDecimal dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }


    public boolean hasWeekdayCharge() {
        return weekdayCharge;
    }


    public boolean hasWeekendCharge() {
        return weekendCharge;
    }

    public boolean hasHolidayCharge() {
        return holidayCharge;
    }


}
