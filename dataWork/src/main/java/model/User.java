package model;

import model.statuses.UserStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by Rafa on 29.06.2015.
 */
@Entity
public class User {
    @Id
    long id;
    @Column
    String login;
    @Column
    long passwordHash;
    @Column
    UserStatus status;
}
