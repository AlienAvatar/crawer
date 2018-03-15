package model;

public class HuxiuBean {
    private String title;

    private String author;

    private String content;

    private String comment;

    public HuxiuBean(){
    }

    public HuxiuBean(String title, String author, String content, String comment) {
        super();
        this.title = title;
        this.author = author;
        this.content = content;
        this.comment = comment;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "HuxiuBean{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
