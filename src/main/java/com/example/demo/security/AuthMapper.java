package com.example.demo.security;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
  Optional<Account> findAccountByUsername(String username);

  List<AccountRoles> findAccountRolesByUsername(String username);

  List<String> findRolesByUsername(String username);

  void saveAccount(Account account);

  void saveAccountRoles(String username, String role);

  void deleteAccountRoles(String username, String role);
}
