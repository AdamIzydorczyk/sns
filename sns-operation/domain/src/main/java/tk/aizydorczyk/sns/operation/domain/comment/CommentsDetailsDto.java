package tk.aizydorczyk.sns.operation.domain.comment;

public class CommentsDetailsDto {
    private String comment;
    private int pluses;
    private int minuses;

    public CommentsDetailsDto() {
    }

    public CommentsDetailsDto(String comment, int pluses, int minuses) {
        this.comment = comment;
        this.pluses = pluses;
        this.minuses = minuses;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPluses() {
        return pluses;
    }

    public void setPluses(int pluses) {
        this.pluses = pluses;
    }

    public int getMinuses() {
        return minuses;
    }

    public void setMinuses(int minuses) {
        this.minuses = minuses;
    }

    @Override
    public String toString() {
        return "CommentsDetailsDto{" +
                "comment='" + comment + '\'' +
                ", pluses=" + pluses +
                ", minuses=" + minuses +
                '}';
    }
}
