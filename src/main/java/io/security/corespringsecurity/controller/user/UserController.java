package io.security.corespringsecurity.controller.user;

import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.domain.AccountDto;
import io.security.corespringsecurity.mapstruct.AccountMapper;
import io.security.corespringsecurity.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final AccountMapper accountMapper;
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  @GetMapping(value="/mypage")
  public String myPage() throws Exception {

    return "user/mypage";
  }

  @GetMapping("/users")
  public String createUser() {
    return "user/login/register";
  }

  @PostMapping("/users")
  public String createUser(AccountDto accountDto) {
    Account accountEntity = accountMapper.toAccount(accountDto);
    accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
    userService.createUser(accountEntity);
    return "redirect:/";
  }
}
