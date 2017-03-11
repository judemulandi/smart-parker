package ke.co.jmconsulting.smartparker;

/**
 * Created by jmulandi on 1/9/2017.
 */
public class Vehicle {
    private String number="";
    private String destination="";
    private String type="";
    private boolean parked=false;
    private String note= " ";

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return (getNumber());
    }
}
