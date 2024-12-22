package com.miningo.server.events;

import com.miningo.constants.EventRarity;
import com.miningo.constants.EventTag;
import com.miningo.constants.EventType;

import java.util.List;

public abstract class ChaosEvent {
    public ChaosEvent(
        int cooldown_seconds,
        EventRarity rarity,
        EventType type,
        int duration,
        String description,
        String title,
        String id,
        List<EventTag> tags,
        String message) {

        this.cooldown_seconds = cooldown_seconds;
        this.rarity = rarity;
        this.type = type;
        this.duration = duration;
        this.description = description;
        this.title = title;
        this.id = id;
        this.tags = tags;
        this.message = message;
    }

    private int cooldown_seconds;

    public int getcooldown_seconds() {
        return cooldown_seconds;
    }

    public void setcooldown_seconds(int cooldown_seconds) {
        this.cooldown_seconds = cooldown_seconds;
    }

    public List<EventTag> getTags() {
        return tags;
    }

    public void setTags(List<EventTag> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventRarity getRarity() {
        return rarity;
    }

    public void setRarity(EventRarity rarity) {
        this.rarity = rarity;
    }

    private List<EventTag> tags;

    private String id;
    private String title;
    private String message;

    private String description;

    private int duration;
    private EventType type;
    private EventRarity rarity;
}
