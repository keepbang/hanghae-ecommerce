package com.hhplus.commerce.app.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * create on 4/2/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seq_id")
  private Long id;

  @Column(name = "user_key")
  private UUID userKey;

  @Column(name = "name", length = 100)
  private String name;

  @Column
  private String address;

  public User(UUID userKey, String name, String address) {
    this.userKey = userKey;
    this.name = name;
    this.address = address;
  }
}
