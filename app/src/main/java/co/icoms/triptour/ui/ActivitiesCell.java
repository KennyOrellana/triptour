package co.icoms.triptour.ui;

public class ActivitiesCell {
    String name;
    String photoUrl;
    int calification;

    ActivitiesCell(String name, String photoUrl, int calification){
        this.name=name;
        this.photoUrl=photoUrl;
        this.calification=calification;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }


    public int getCalification() {
        return calification;
    }
}
