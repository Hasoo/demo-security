package com.example.demo.security;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SecurityMapper {
  Optional<Account> findAccountByUsername(String username);

  List<AccountRoles> findAccountRolesByUsername(String username);

  List<String> findRolesByUsername(String username);

  void saveAccount(Account account);

  void saveAccountRoles(String username, String role);

  void deleteAccountRoles(String username, String role);

  Optional<Integer> findFailureCountByUsername(String username);

  void updateFailureCount(@Param("username") String username, @Param("count") int count);

  void updateFailureCountPlusOne(String username);
}
