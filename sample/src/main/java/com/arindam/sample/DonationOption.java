package com.arindam.sample;

/**
 * Created by Arindam Ghosh on 29.08.2019.
 */
public class DonationOption {

    public final String description;
    public final String amount;

    public DonationOption(String amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return "DonationOption{" +
                "description='" + description + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
