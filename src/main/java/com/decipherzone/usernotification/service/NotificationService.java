package com.decipherzone.usernotification.service;

import com.decipherzone.usernotification.model.Notification;
import com.decipherzone.usernotification.model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface NotificationService {
    List<Notification> getAllNotifications();

    Notification getNotification(Long id);

    Notification createNotification(Notification notification);

    Notification updateNotification(Long id,Notification notification);

    void deleteNotification(Long id);

    Notification addUserToNotification(Long notificationId, Long userId);

    void deleteUserFromNotification(Long notificationId, Long userId);

    Set<User> getAllUserFromNotification(Long notificationId);

    void enableNotification(Long notificationId, Boolean state);

    Notification sendNotification(Long notificationId);

    Set<Notification> getAllNotifications(Long userId);

    Set<Notification> getAllNotifications(Notification.Type notificationType);

    Set<Notification> getAllNotifications(LocalDate notificationDate);
}
