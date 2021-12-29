package gamelogic;

import javafx.event.Event;
import  javafx.event.EventType;

public class CollisionEvent extends Event {
    public static final EventType<CollisionEvent>COIN = new EventType<>(CollisionEvent.COIN, "ANY");
    public static final EventType<CollisionEvent>ITEM = new EventType<>(CollisionEvent.ITEM,"ANY");
    public static final EventType<CollisionEvent>WAVE = new EventType<>(CollisionEvent.WAVE,"ANY");

    public GameObject gameObject;



    public CollisionEvent(EventType<? extends Event> eventType, GameObject gameObject) {
        super(eventType);
        this.gameObject = gameObject;

    }

    public GameObject getObject(){
        return this.gameObject;
    }
}
