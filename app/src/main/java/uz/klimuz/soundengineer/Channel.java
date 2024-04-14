package uz.klimuz.soundengineer;

import java.io.Serializable;

public class Channel implements Serializable {
    private int number;//0
    private String rioName;
    private String rioNumber;
    private String name;//"---"
    private String pickup;//"---"

    public Channel(){
        this.name = "---";
        this.pickup = "---";
    }

    public Channel(int number, String name, String pickup) {
        this.number = number;
        this.name = name;
        this.pickup = pickup;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public  void setRioName(String rioName){this.rioName = rioName; }

    public  void setRioNumber (String rioNumber){this.rioNumber = rioNumber; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public int getNumber() {
        return number;
    }

    public String getRioName() {return rioName; }

    public String getRioNumber() {return rioNumber; }

    public String getName() {
        return name;
    }

    public String getPickup() {
        return pickup;
    }
}
