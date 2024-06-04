import java.util.List;

public class Court<R extends ResponderInterface<S>, S extends SeekerInterface<R>> {
    private List<Balcony<R, S>> balconies;
    private List<R> responders;
    private List<S> seekers;

    public Court(List<R> responders, List<S> seekers) {
        this.responders = responders;
        this.seekers = seekers;

        createBalconies();
    }

    private void createBalconies() {
        for (R responder : responders) {
            Balcony<R, S> balcony = new Balcony<R, S>(responder);
            responder.setBalcony(balcony);
            balconies.add(balcony);
        }
    }

    public void moveSeekersToBalconies() {
        for (S seeker : seekers) {
            R firstPick = seeker.getFirstPreference();
    }
        



}
