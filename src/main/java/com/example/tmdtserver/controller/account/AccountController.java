package com.example.tmdtserver.controller.account;

import com.example.tmdtserver.model.Account;
import com.example.tmdtserver.model.Role;
import com.example.tmdtserver.model.Users;
import com.example.tmdtserver.service.account.EmailService;
import com.example.tmdtserver.service.account.IAccountService;
import com.example.tmdtserver.service.account.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/accounts")
public class AccountController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserService userService;


    @PostMapping(value = "/randomCode/{value}")
    public ResponseEntity<String> randomCode(@PathVariable("value") String email) {
        Users users = userService.findByEmail(email);
        if (users == null) {
            String code = accountService.randomCode(email);
            return new ResponseEntity<>(code, HttpStatus.ACCEPTED);
        } else {
            String code = "emailError";
            return new ResponseEntity<>(code, HttpStatus.ACCEPTED);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Void> save(@RequestBody() Account account) {
        userService.save(account.getUsers());
        Users users = userService.findByEmail(account.getUsers().getEmail());
        Role role = account.getRole();
        Account account1 = new Account(role, users, true);
        accountService.save(account1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/showList")
    public ResponseEntity<Page<Account>> showList(@PageableDefault(size = 5) Pageable pageable) {
        Page<Account> listAccount = accountService.listAll(pageable);
        return new ResponseEntity<>(listAccount, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody() Users users) {
        HashMap<String, Object> hashMap = (HashMap<String, Object>) userService.login(users);
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Users> findUserByAccount(@PathVariable("id") Long id) {
        Users users = accountService.findUserByAccount(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/shop/{id}")
    public ResponseEntity<Users> findUserByIdShop(@PathVariable("id") Long id) {
        Users users = userService.findByIdShop(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/information/{id}")
    public ResponseEntity<Account> findAccountByIdShop(@PathVariable("id") Long id) {
        Account account = accountService.findAccountByIdShop(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    //    update user
    @PostMapping(value = "/update-user")
    public ResponseEntity<Void> updateUser(@RequestBody() Users users) {
        userService.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
