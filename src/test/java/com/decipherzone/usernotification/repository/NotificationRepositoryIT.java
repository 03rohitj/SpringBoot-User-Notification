package com.decipherzone.usernotification.repository;

import com.decipherzone.usernotification.model.EntityId;
import com.decipherzone.usernotification.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/** Integration Testing **/
//Spring Runner is an alias  of SpringJUnit4ClassRunner, which allows to perform test with Context(Spring ContextTest framework)
@RunWith(SpringRunner.class)
@DataJpaTest
class NotificationRepositoryIT extends EntityId {

    @Autowired
    NotificationRepository repository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAllNotificationsByType() {
        Set smsNotifications = repository.findAllNotificationsByType(Notification.Type.SMS);
        System.out.println(smsNotifications.toString());
        assertEquals(1,smsNotifications.size());
    }

    @Test
    void findAllNotificationsByDate() {
        LocalDate notifyDate = LocalDate.of(2021,11,03);
        Set notifications = repository.findAllNotificationsByDate(notifyDate);
        System.out.println(notifications.toString());
        assertEquals(1,notifications.size());
    }
}