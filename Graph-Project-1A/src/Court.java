import java.util.List;

public class Court<R, S> {
    private List<R> responders;
    private List<S> seekers;

    public Court(List<R> responders, List<S> seekers) {
        this.responders = responders;
        this.seekers = seekers;
    }

}
