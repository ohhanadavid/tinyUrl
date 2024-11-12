package com.handson.tinyurl.model;


import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.Objects;

@Table
public class UserClick {
    @PrimaryKey
    private UserClickKey userClickKey;

    private String tiny;
    private String longUrl;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserClick userClick = (UserClick) o;
        return Objects.equals(userClickKey, userClick.userClickKey) && Objects.equals(tiny, userClick.tiny) && Objects.equals(longUrl, userClick.longUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userClickKey, tiny, longUrl);
    }

    public UserClickKey getUserClickKey() {
        return userClickKey;
    }

    public void setUserClickKey(UserClickKey userClickKey) {
        this.userClickKey = userClickKey;
    }

    public void setTiny(String tiny) {
        this.tiny = tiny;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getTiny() {
        return tiny;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public static final class UserClickBuilder {
        private UserClickKey userClickKey;
        private String tiny;
        private String longUrl;

        private UserClickBuilder() {
        }

        public static UserClickBuilder anUserClick() {
            return new UserClickBuilder();
        }

        public UserClickBuilder userClickKey(UserClickKey userClickKey) {
            this.userClickKey = userClickKey;
            return this;
        }

        public UserClickBuilder tiny(String tiny) {
            this.tiny = tiny;
            return this;
        }

        public UserClickBuilder longUrl(String longUrl) {
            this.longUrl = longUrl;
            return this;
        }

        public UserClick build() {
            UserClick userClick = new UserClick();
            userClick.setUserClickKey(userClickKey);
            userClick.setTiny(tiny);
            userClick.setLongUrl(longUrl);
            return userClick;
        }
    }
}
