import java.util.List;

public class Balcony<R, S> {
    private Court court;
    private final R responder;
    private List<S> seekers;

    public Balcony(R responder) {
        this.responder = responder;
    }

}