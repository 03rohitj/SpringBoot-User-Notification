package com.decipherzone.usernotification.model;

import com.decipherzone.usernotification.Overload;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** Notification model to store a notification sent to a user */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "users")
@Entity
public @Data class Notification extends EntityId {

    public enum Type{
        SMS, EMAIL
//        SMS{
//            @Override
//            public String toString() {
//                return "SMS";
//            }
//        },
//
//        EMAIL{
//            @Override
//            public String toString () {
//                return "EMAIL";
//            }
//        }

    }
    /** Defines the type of message(SMS or EMAIL) */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)        //To store enum value as string
    private Type type;

    /**
     * Store all the users to which notification has been sent.
     * Many-to-Many relation
     */
    @ManyToMany(mappedBy = "notifications", fetch = FetchType.EAGER)     //Data member of User model
    @JsonIgnore
    @EqualsAndHashCode.Exclude private Set<User> users = new HashSet<>();

    /** Time at which notification is to be sent */
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate notifyTime;

    /** Time at which record is created */
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;

    /** Time at which record is updated last */
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastUpdated;

    /** Notification is enabled or not */
    @Column
    private Boolean enabled;


    /** is the notification sent to user or not, False by default */
    @Column
    private Boolean isSent;

    /** Should the Notification repeated or not */
    @Column(nullable = false)
    private Boolean isRepeat;

    public Notification(Type type, Date notifyTime, Boolean enabled, Boolean isRepeat) {
        System.out.println("\nArg C'tor called");
        this.type = type;
        this.notifyTime = convertDateToLocalDate(notifyTime);
        this.enabled = enabled;
        this.isRepeat = isRepeat;
        isSent = false;
        dateCreated = convertDateToLocalDate(new Date());
        lastUpdated = convertDateToLocalDate(new Date());
    }

    //method to convert java.util.Date to java.time.LocalDate
    public static LocalDate convertDateToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Overload
    public void setDateCreated(Date date) {
       this.dateCreated = convertDateToLocalDate(date);
    }

    @Overload
    public void setLastUpdated(Date date) {
        this.lastUpdated = convertDateToLocalDate(date);
    }

    public User addUser(User user){
        this.users.add(user);
        user.getNotifications().add(this);
        return user;
    }

    public void deleteUser(User user){
        users.remove(user);
        user.getNotifications().remove(this);
    }

    public void deleteUser(){
        ArrayList<User> userArray = new ArrayList<>(users);
        for(User user : userArray)
            deleteUser(user);
    }
}
