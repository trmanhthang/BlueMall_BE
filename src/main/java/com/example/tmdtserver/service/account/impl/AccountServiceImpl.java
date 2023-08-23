package com.example.tmdtserver.service.account.impl;

import com.example.tmdtserver.model.Account;
import com.example.tmdtserver.model.Users;
import com.example.tmdtserver.repository.IAccountRepository;
import com.example.tmdtserver.repository.IUserRepository;
import com.example.tmdtserver.service.account.EmailService;
import com.example.tmdtserver.service.account.IAccountService;
import com.example.tmdtserver.service.account.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private IUserService iUserService;

    @Override
    public Page<Account> listAll(Pageable pageable) {
        return iAccountRepository.findAll(pageable);
    }

    @Override
    public String randomCode(String email) {
        Random rand = new Random();
        int randomNum = rand.nextInt(900000) + 100000;
        String to = email;
        String subject = "FC-BLUE";
        String text = String.valueOf(randomNum);
//        Users users = iUserService.findByEmail(email);
        String content = "Xin Chào ...!\n" +
                "Bạn hoặc ai đó đẫ dùng email này để đăng ký tài khoản ở trung tâm TMDT-FC-BLUE\n" +
                "\n" +
                "MÃ XÁC NHẬN CỦA BẠN LÀ  : " + text + "\n" +
                "\n" +
                "--------------------------------------\n" +
                " + Phone  : (+084)088.888.888\n" +
                " + Email  : fc.blue.codegym.vn@gmail.com\n" +
                " + Address: Lô TT-04 Số 23 Khu Đô Thị MonCity\n";
        emailService.sendMail(to, subject, content);
        return text;

    }

    @Override
    public void save(Account account) {
        iAccountRepository.save(account);

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Account findByEmail(String email) {
        return null;
    }

    @Override
    public Account findAccountByEmail(String email) {
        return iAccountRepository.findAccountByEmail(email);
    }

    @Override
    public Users findUserByAccount(Long id) {
        return iAccountRepository.findUserByAccount(id);
    }

    @Override
    public Account findById(Long id) {
        return iAccountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findAccountByIdShop(Long id) {
        return iAccountRepository.findAccountByIdShop(id);
    }
}
