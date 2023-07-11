package io.security.corespringsecurity.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {
  @Id
  @GeneratedValue
  private Long id;

  private String username;
  private String password;
  private String email;
  private String role;
}
