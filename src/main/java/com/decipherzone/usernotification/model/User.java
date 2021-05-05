package com.decipherzone.usernotification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** User model to store a user's information */
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "notifications")
@Entity
public @Data class User extends EntityId{

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, unique = true, scale = 10)
    private String phone;

    /** To store list of notifications sent to the user */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_notification", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "notification_id"))
    @JsonIgnore
    private Set<Notification> notifications = new HashSet<>();

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    //Add a notification to the set
    public Notification addNotification(Notification notification){
        this.notifications.add(notification);
        notification.addUser(this);
        return notification;
    }

    //Remove a notification fom the set
    public void removeNotification(Notification notification){
        this.notifications.remove(notification);
        notification.getUsers().remove(this);
    }

    //Remove all the notifications
    public void removeNotification(){
        for(Notification notification : notifications){
            this.removeNotification(notification);
        }
    }
}
