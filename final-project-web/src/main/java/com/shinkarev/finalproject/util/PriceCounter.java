package com.shinkarev.finalproject.util;

import com.shinkarev.musicshop.entity.Instrument;

import java.util.List;

public class PriceCounter {

    public static double getGeneralPrice(List<Instrument> instruments) {
        double price = 0;
        for (Instrument instrument : instruments) {
            price += instrument.getPrice();
        }
        return price;
    }
}
