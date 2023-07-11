package io.security.corespringsecurity.service.impl;

import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserRepository userRepository;
  @Override
  @Transactional
  public void createUser(Account account) {
    userRepository.save(account);
  }
}
