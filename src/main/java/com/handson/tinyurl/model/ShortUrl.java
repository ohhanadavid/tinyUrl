package com.handson.tinyurl.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShortUrl {
    private Map<String, Integer> clicks = new HashMap<>();

    public Map<String, Integer> getClicks() {
        return clicks;
    }

    public void setClicks(Map<String, Integer> clicks) {
        this.clicks = clicks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortUrl shortUrl = (ShortUrl) o;
        return Objects.equals(clicks, shortUrl.clicks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clicks);
    }

    @Override
    public String toString() {
        return "ShortUrl{" +
                "clicks=" + clicks +
                '}';
    }

}
