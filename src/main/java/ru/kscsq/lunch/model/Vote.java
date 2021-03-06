package ru.kscsq.lunch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQuery(name = Vote.GET_BY_DATE, query = "SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {
    public static final String GET_BY_DATE = "Vote.getVoteDate";

    @Column(name = "date")
    @JsonIgnore
    private Date date = new Date();

    @Column(name="restaurant_id", insertable=false, updatable=false)
    private Integer restaurantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name="user_id", insertable=false, updatable=false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private User user;

    public Vote() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Vote{" +
                ", date=" + date +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                '}';
    }
}
