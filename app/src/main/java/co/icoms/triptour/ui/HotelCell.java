package co.icoms.triptour.ui;

public class HotelCell {
    String name;
    String photoUrl;
    int price;
    int calification;

    HotelCell(String name, String photoUrl, int price, int calification){
        this.name=name;
        this.photoUrl=photoUrl;
        this.price=price;
        this.calification=calification;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getPrice() {
        return price;
    }

    public int getCalification() {
        return calification;
    }
}
