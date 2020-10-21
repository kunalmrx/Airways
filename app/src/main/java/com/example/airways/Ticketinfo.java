package com.example.airways;

import android.widget.TextView;

class Ticketinfo {
    public  String customerid,flightid,adultseat,childseat,clas,date,pnr,name,from,to,ammount;
   public Ticketinfo(String phone, String number, String adultcount, String childcount, String dayofmonth, String month, String year,String clas,String date,String pnr,String name,String from,String to,String amount) {

        customerid=phone;
        flightid=number;
        adultseat=adultcount;
        childseat=childcount;
        this.date=date;
        this.clas=clas;
        this.pnr=pnr;
        this.name=name;
        this.from=from;
        this.to=to;
        this.ammount=amount;

    }
}
