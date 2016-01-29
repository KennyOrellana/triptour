package co.icoms.triptour.ui;

public class RestaurantCell {
    String name;
    String photoUrl;
    String price;
    int calification;

    RestaurantCell(String name, String photoUrl, String price, int calification){
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

    public String getPrice() {
        return price;
    }

    public int getCalification() {
        return calification;
    }
}
