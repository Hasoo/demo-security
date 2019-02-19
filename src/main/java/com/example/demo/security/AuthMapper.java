package com.example.demo.security;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
  Account findAccountByUsername(String username);

  AccountRoles findAccountRolesByUsername(String username);
}
