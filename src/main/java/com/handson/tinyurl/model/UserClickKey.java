package com.handson.tinyurl.model;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.Date;
import java.util.Objects;

@PrimaryKeyClass
public class UserClickKey {

    @PrimaryKeyColumn(name = "user_name",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String userName;

    @PrimaryKeyColumn(name="click_time",ordinal = 1,type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING )
    private Date clickTime = new Date();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserClickKey that = (UserClickKey) o;
        return Objects.equals(userName, that.userName) && Objects.equals(clickTime, that.clickTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, clickTime);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setClickTime(Date clickTime) {
        this.clickTime = clickTime;
    }

    public String getUserName() {
        return userName;
    }

    public Date getClickTime() {
        return clickTime;
    }

    public static final class UserClickKeyBuilder {
        private String userName;
        private Date clickTime = new Date();

        private UserClickKeyBuilder() {
        }

        public static UserClickKeyBuilder anUserClickKey() {
            return new UserClickKeyBuilder();
        }

        public UserClickKeyBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserClickKeyBuilder withClickTime(Date clickTime) {
            this.clickTime = clickTime;
            return this;
        }

        public UserClickKey build() {
            UserClickKey userClickKey = new UserClickKey();
            userClickKey.setUserName(userName);
            userClickKey.setClickTime(clickTime);
            return userClickKey;
        }
    }
}
