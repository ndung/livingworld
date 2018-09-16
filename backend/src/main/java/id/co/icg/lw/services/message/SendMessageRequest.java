package id.co.icg.lw.services.message;

public class SendMessageRequest {
    private String firstName;
    private String email;
    private String rating;
    private String comment;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }
}
