package com.decipherzone.usernotification.service;

import com.decipherzone.usernotification.model.Notification;
import com.decipherzone.usernotification.model.User;
import com.decipherzone.usernotification.repository.NotificationRepository;
import com.decipherzone.usernotification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Service
public class NotificationServiceImp implements NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    //API to get all the notifications
    @Override
    public List<Notification> getAllNotifications() {
        return (List<Notification>) notificationRepository.findAll();
    }

    //API to get details of a notification
    @Override
    public Notification getNotification(Long id) {
        return notificationRepository.findById(id).orElseThrow();
    }

    //API to create a new notification
    @Override
    public Notification createNotification(Notification notification) {
        notification.setIsSent(false);
        notification.setDateCreated(new Date());
        notification.setLastUpdated(new Date());
        return notificationRepository.save(notification);
    }

    //API to update a notification
    @Override
    public Notification updateNotification(Long id, Notification notification) {
        notification.setId(id);
        //update lastUpdated to today
        notification.setLastUpdated(new Date());
        notification.setIsSent(false);
        notification.setDateCreated(new Date());

        return notificationRepository.save(notification);
    }

    //API to delete a notification
    @Override
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow();
        //First delete all the users registered to the notification
        notification.deleteUser();

        notificationRepository.deleteById(id);
    }

    //API to register a new user to the notification
    @Override
    public Notification addUserToNotification(Long notificationId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.addUser(user);
        userRepository.save(user);
        return notificationRepository.save(notification);
    }

    //API to delete all the users registered to the notification
    @Override
    public void deleteUserFromNotification(Long notificationId, Long userId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        notification.deleteUser(user);
        notificationRepository.save(notification);
        userRepository.save(user);
    }

    //API to get all the users registered to the notification
    @Override
    public Set<User> getAllUserFromNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        System.out.println("=====>\n\n"+notification.getUsers());
        return notification.getUsers();
    }

    //API to change enable/disable notification
    @Override
    public void enableNotification(Long notificationId, Boolean state) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setEnabled(state);
        notificationRepository.save(notification);
    }

    //API to send notification
    @Override
    public Notification sendNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        if(notification.getIsRepeat() == false){
            notification.setIsSent(true);
            notification.setEnabled(false);
        }
        return notification;
    }

    @Override
    public Set<Notification> getAllNotifications(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getNotifications();
    }

    @Override
    public Set<Notification> getAllNotifications(Notification.Type notificationType) {
        return notificationRepository.findAllNotificationsByType(notificationType);
    }

    @Override
    public Set<Notification> getAllNotifications(LocalDate notificationDate) {
        return notificationRepository.findAllNotificationsByDate(notificationDate);
    }

}
