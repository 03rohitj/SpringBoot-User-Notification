package com.decipherzone.usernotification.repository;

import com.decipherzone.usernotification.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;
@Repository
public interface NotificationRepository extends CrudRepository<Notification,Long> {

    @Query("SELECT n FROM Notification n WHERE n.type=:type")
    Set<Notification> findAllNotificationsByType(@Param("type") Notification.Type type);

    @Query("SELECT n FROM Notification n WHERE n.notifyTime=:date")
    Set<Notification> findAllNotificationsByDate(@Param("date") LocalDate date);
}
