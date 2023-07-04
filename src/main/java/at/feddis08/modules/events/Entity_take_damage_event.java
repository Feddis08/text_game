package at.feddis08.modules.events;

import at.feddis08.world.entities.Entity;

public class Entity_take_damage_event extends Event{
    public Entity from;
    public int amount;
    public Entity_take_damage_event(Entity entity, Entity from, int amount) {
        super("entity_take_damage_event", entity);
        this.from = from;
        this.amount = amount;
    }
}
