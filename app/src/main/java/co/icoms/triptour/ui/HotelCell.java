package co.icoms.triptour.ui;

public class HotelCell {
    int id;
    String name;
    String photoUrl;
    int price;
    int calification;

    HotelCell(int id,String name, String photoUrl, int price, int calification){
        this.id=id;
        this.name=name;
        this.photoUrl=photoUrl;
        this.price=price;
        this.calification=calification;
    }

    public int getId() {
        return id;
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
