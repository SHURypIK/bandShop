package com.example.bandShop.service;

import com.example.bandShop.entity.AdminEntity;
import com.example.bandShop.entity.ShopEntity;
import com.example.bandShop.exception.PasswordUncorectException;
import com.example.bandShop.exception.UserAlreadyExistException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.model.Admin;
import com.example.bandShop.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    public AdminEntity registration(AdminEntity admin) throws UserAlreadyExistException {
        AdminEntity admine = adminRepo.findById(admin.getId()).get();
        if (admine != null){
            throw new UserAlreadyExistException("Админ с таким id уже существует");
        }
        admine.setShop(new ShopEntity());
        return adminRepo.save(admin);
    }

    public Admin getOne(Integer id) throws UserNotFoundException {
        AdminEntity admin = adminRepo.findById(id).get();
        if(admin == null)
            throw new UserNotFoundException("Админ не был найден");
        return Admin.toModel(admin);
    }

    public Admin enter(AdminEntity admin) throws UserNotFoundException, PasswordUncorectException {
        AdminEntity admine = adminRepo.findById(admin.getId()).get();
        if(admine == null)
            throw new UserNotFoundException("Пользователь не был найден");
        if(!admine.getPassword().equals(admin.getPassword()))
            throw new PasswordUncorectException("Не верный пароль");
        return Admin.toModel(admine);
    }

    public Integer delete(Integer id)  {
        adminRepo.deleteById(id);
        return id;
    }


}
