//package com.decipherzone.usernotification.bootstrap_op;
//
//import com.decipherzone.usernotification.model.Notification;
//import com.decipherzone.usernotification.model.User;
//import com.decipherzone.usernotification.repository.NotificationRepository;
//import com.decipherzone.usernotification.repository.UserRepository;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Set;
//
////It puts some data into repository upon initialization of the Application
//@Component
//public class InitRepoData implements ApplicationListener<ContextRefreshedEvent> {
//    private NotificationRepository notificationRepository;
//    private UserRepository userRepository;
//
//    @Autowired
//    public InitRepoData(NotificationRepository notificationRepository, UserRepository userRepository) {
//        this.notificationRepository = notificationRepository;
//        this.userRepository = userRepository;
//    }
//
//    @SneakyThrows
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        initReposData();
//        showInitData();
//    }
//
//    private void showInitData() {
//        System.out.println("\nUsers");
//        for(User user : userRepository.findAll()){
//            System.out.println(user);
//            System.out.println("Notifications {");
//            for(Notification notification : user.getNotifications()){
//                System.out.println(notification);
//            }
//            System.out.println(" }");
//        }
//
//        System.out.println("\nNotifications");
//        for(Notification notification : notificationRepository.findAll()){
//            System.out.println(notification);
//            System.out.println("User {"+notification.getUser()+" }");
//        }
//    }
//
//    private void initReposData() throws ParseException {
//        User user = new User(
//                "Marcus Stoinis",
//                "stoinisM@gmail.com",
//                "8764567890");
//
//        userRepository.save(user);
//
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        Notification notification1 = new Notification(
//                Notification.Type.SMS,
//                formatter.parse("03/10/2021"),
//                true,
//                false
//        );
//
//        Notification notification2 = new Notification(
//                Notification.Type.SMS,
//                formatter.parse("23/12/2021"),
//                false,
//                true
//        );
//
//        notification1.setUser(user);
//        notification2.setUser(user);
//
//
//        notificationRepository.save(notification1);
//        notificationRepository.save(notification2);
//    }
//
//}
