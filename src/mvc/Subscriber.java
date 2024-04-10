package mvc;


public interface Subscriber {
    void update();

    void update(String msg, Object oldState, Object newState);
}