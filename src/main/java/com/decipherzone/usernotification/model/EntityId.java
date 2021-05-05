package com.decipherzone.usernotification.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass   //It prevents inheritance at Repository level and allows at OOPs level.
public abstract class EntityId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
