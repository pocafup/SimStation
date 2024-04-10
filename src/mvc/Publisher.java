package mvc;

import java.util.HashSet;

public class Publisher {
    private final HashSet<Subscriber> subscribers;

    public Publisher() {
        subscribers = new HashSet<>();
    }

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            s.update();
        }
    }
}