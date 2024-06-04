public interface ResponderInterface<S> {

    void setBalcony(Balcony<? extends ResponderInterface<S>, S> balcony);
    Balcony<? extends ResponderInterface<S>, S> getBalcony();



}
