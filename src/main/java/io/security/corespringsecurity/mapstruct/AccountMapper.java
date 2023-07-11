package io.security.corespringsecurity.mapstruct;

import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.domain.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  Account toAccount(AccountDto accountDto);
}
