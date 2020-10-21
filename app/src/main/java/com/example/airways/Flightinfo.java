package com.example.airways;

class Flightinfo {

    public String name,number,from,to,day,seatb,seate,seatfc,seatpe,ab,ae,afc,ape,cb,ce,cfc,cpe,time;

   public Flightinfo()
    {

    }
    public Flightinfo(String ename, String enumber, String efrom, String eto, String eday, String etime, String eseatb, String eseate, String eseatfc, String eseatpe, String ab, String ae, String afc, String ape, String cb, String ce, String cfc, String cpe)
    {
        this.name = ename;
        this.number = enumber;
        this.from = efrom;
        this.to = eto;
        this.day = eday;
        this.time = etime;
        this.seatb = eseatb;
        this.seate = eseate;
        this.seatfc = eseatfc;
        this.seatpe = eseatpe;
        this.ab = ab;
        this.ae = ae;
        this.afc = afc;
        this.ape = ape;
        this.cb = cb;
        this.ce = ce;
        this.cfc = cfc;
        this.cpe = cpe;
        this.name=name;

    }
}
