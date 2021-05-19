package com.decipherzone.usernotification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** User model to store a user's information */
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "notifications")
@Entity
public @Data class User extends EntityId{

    @NonNull
    @Column(nullable = false, length = 50)
    private String name;

    @NonNull
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NonNull
    @Column(nullable = false, unique = true, scale = 10)
    private String phone;

    /** To store list of notifications sent to the user */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Notification> notifications = new HashSet<>();

}
