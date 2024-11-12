package com.handson.tinyurl.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Objects;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private int allUrlClicks;
    private Map<String, ShortUrl> shorts;

    public Map<String, ShortUrl> getShorts() {
        return shorts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", allUrlClicks=" + allUrlClicks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return allUrlClicks == user.allUrlClicks && Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, allUrlClicks);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllUrlClicks(int allUrlClicks) {
        this.allUrlClicks = allUrlClicks;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAllUrlClicks() {
        return allUrlClicks;
    }

    public static final class UserBuilder {
        private String id;
        private String name;
        private int allUrlClicks;
        private Map<String, ShortUrl> shorts;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withAllUrlClicks(int allUrlClicks) {
            this.allUrlClicks = allUrlClicks;
            return this;
        }

        public UserBuilder withShorts(Map<String, ShortUrl> shorts) {
            this.shorts = shorts;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setAllUrlClicks(allUrlClicks);
            user.shorts = this.shorts;
            return user;
        }
    }
}
