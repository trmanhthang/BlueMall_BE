package com.example.tmdtserver.service.account;

import com.example.tmdtserver.model.Users;
import com.example.tmdtserver.service.account.icore.ICrud;

import java.util.Map;

public interface IUserService extends ICrud<Users> {
    Map login(Users users);
    Users findByIdShop(Long id);

}
