package com.decipherzone.usernotification.controller;

import com.decipherzone.usernotification.model.Notification;
import com.decipherzone.usernotification.model.User;
import com.decipherzone.usernotification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private NotificationService notificationService;
    
    @Autowired
    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }


    @PostMapping
    public Notification addNotification(@RequestBody Notification notification){
        return notificationService.createNotification(notification);
    }

    @PutMapping("/{id}")
    public Notification editNotification(@PathVariable Long id, @RequestBody Notification notification){
        return notificationService.updateNotification(id,notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id){
        notificationService.deleteNotification(id);

    }

    //Method is responsible for adding a user to the set of users, of the notification having ID as 'notificationId'
    @PutMapping("/{notificationId}/addUser")
    public Notification addUserToNotification(@PathVariable Long notificationId, @RequestBody Long userId){
        return notificationService.addUserToNotification(notificationId, userId);
    }

    //Method is responsible for deleting a user from the set of users, of the notification having ID as 'notificationId'
    @DeleteMapping("/{notificationId}/deleteUser")
    public void deleteUserFromNotification(@PathVariable Long notificationId, @RequestBody Long userId){
        notificationService.deleteUserFromNotification(notificationId, userId);
    }

    //Method is responsible for displaying all the users , of the notification having ID as 'notificationId'
    @GetMapping("/{notificationId}/getAllUser")
    public Set<User> getAllUserFromNotification(@PathVariable Long notificationId){
        return notificationService.getAllUserFromNotification(notificationId);
    }

    //Controller to implement a service that enable or disable a notification
    @PutMapping("/{notificationId}/enableNotification")
    public void enableNotification(@PathVariable Long notificationId, @RequestBody Boolean state){
        notificationService.enableNotification(notificationId,state);

    }

    @GetMapping("{notificationId}/sendNotification")
    public Notification sendNotification(@PathVariable Long notificationId){
        return notificationService.sendNotification(notificationId);
    }

    @GetMapping
    public List<Notification> fetchAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public Notification fetchNotification(@PathVariable Long id){
        return notificationService.getNotification(id);
    }

    //API to fetch all the notifications registered to the 'userId'
    @GetMapping("/getNotificationsById/{userId}")
    public Set<Notification> fetchAllNotifications(@PathVariable Long userId){
        return notificationService.getAllNotifications(userId);
    }

    //API to fetch all the notifications registered to the 'userId'
    @GetMapping("/getNotificationsByType/{notificationType}")
    public Set<Notification> fetchAllNotifications(@PathVariable Notification.Type notificationType){
        return notificationService.getAllNotifications(notificationType);
    }

    //API to fetch all the notifications based on notifyDate
    @GetMapping("/getNotificationsByDate/{notificationDate}")
    public Set<Notification> fetchAllNotifications(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate notificationDate){
        return notificationService.getAllNotifications(notificationDate);
    }
}
