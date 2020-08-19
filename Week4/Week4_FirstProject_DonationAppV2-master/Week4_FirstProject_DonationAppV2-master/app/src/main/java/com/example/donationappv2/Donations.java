package com.example.donationappv2;

import android.os.Parcel;
import android.os.Parcelable;

public class Donations implements Parcelable {

    int paymentMethod; // 1 for credit and 2 for paypal
    double amount;
    int[] sharingApps;

    public Donations(int paymentMethod, double amount, int[] sharingApps) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.sharingApps = sharingApps;
    }

    protected Donations(Parcel in) {
        paymentMethod = in.readInt();
        amount = in.readDouble();
        sharingApps = in.createIntArray();
    }

    public static final Creator<Donations> CREATOR = new Creator<Donations>() {
        @Override
        public Donations createFromParcel(Parcel in) {
            return new Donations(in);
        }

        @Override
        public Donations[] newArray(int size) {
            return new Donations[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(paymentMethod);
        dest.writeDouble(amount);
        dest.writeIntArray(sharingApps);
    }
}
