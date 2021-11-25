package iphone.photography.blog;

public class Post {
    private String title;
    private String content;
    private String date;
    private String excerpt;
    private String tags;
    private String categories;
    private String feature_images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
        this.date = data;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getFeature_images() {
        return feature_images;
    }

    public void setFeature_images(String feature_images) {
        this.feature_images = feature_images;
    }
}
