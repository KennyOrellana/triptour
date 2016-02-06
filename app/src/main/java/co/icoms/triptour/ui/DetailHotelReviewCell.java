package co.icoms.triptour.ui;

public class DetailHotelReviewCell {
    String user;
    String review;
    int stars;
    String date;

    public DetailHotelReviewCell(String user, String review, int stars, String date) {
        this.user = user;
        this.review = review;
        this.stars = stars;
        this.date = date;
    }

    public DetailHotelReviewCell() {
    }

    public String getUser() {
        return user;
    }

    public String getReview() {
        return review;
    }

    public int getStars() {
        return stars;
    }

    public String getDate() {
        return date;
    }
}
