package com.company;

import java.util.Date;

public final class RandomBoundries {
    final int low;
    final int high;

    final Date earlyDate;
    final Date lateDate;

    public RandomBoundries(int low, int high, Date earlyDate, Date lateDate){
        this.low = low;
        this.high = high;
        this.earlyDate = earlyDate;
        this.lateDate = lateDate;
    }
}
