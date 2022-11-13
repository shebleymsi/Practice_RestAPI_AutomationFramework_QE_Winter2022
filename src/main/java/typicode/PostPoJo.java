package typicode;

public class PostPoJo {

    // https://jsonplaceholder.typicode.com/

    // Work as like an Encapsulation


    // create variable v58 3.47.00
    private int userId;
    private int id;
    private String title;
    private String body;


    // create constructor  v58 3.48.00
    public PostPoJo(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }


    // create getter setter  v58 3.48.00
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
