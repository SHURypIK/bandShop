package com.example.bandShop.service;

import com.example.bandShop.entity.AdminEntity;
import com.example.bandShop.entity.ShopEntity;
import com.example.bandShop.entity.UserEntity;
import com.example.bandShop.exception.PasswordUncorectException;
import com.example.bandShop.exception.ShopNotFoundedException;
import com.example.bandShop.exception.UserAlreadyExistException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.model.Admin;
import com.example.bandShop.model.Shop;
import com.example.bandShop.model.User;
import com.example.bandShop.repository.AdminRepo;
import com.example.bandShop.repository.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private ShopRepo shopRepo;

    public AdminEntity registration(AdminEntity admin) throws UserAlreadyExistException {
        if (adminRepo.existsById(admin.getId())){
            throw new UserAlreadyExistException("Админ с таким id уже существует");
        }
        //admin.setShop(new ShopEntity());
        return adminRepo.save(admin);
    }

    public Admin getOne(Integer id) throws UserNotFoundException {
        if(!adminRepo.existsById(id))
            throw new UserNotFoundException("Админ не был найден");
        return Admin.toModel(adminRepo.findById(id).get());
    }

    public Admin enter(AdminEntity admin) throws UserNotFoundException, PasswordUncorectException {
        if(!adminRepo.existsById(admin.getId()))
            throw new UserNotFoundException("Пользователь не был найден");
        AdminEntity admine = adminRepo.findById(admin.getId()).get();
        if(!admine.getPassword().equals(admin.getPassword()))
            throw new PasswordUncorectException("Не верный пароль");
        return Admin.toModel(admine);
    }

    public Admin delete(Integer id) throws UserNotFoundException {
        if(!adminRepo.existsById(id))
            throw  new UserNotFoundException("Пользователь не найден");
        adminRepo.deleteById(id);
        return new Admin();
    }
    public Shop setShop(int id, int shop_id) throws UserNotFoundException, ShopNotFoundedException {
        if(!adminRepo.existsById(id))
            throw new UserNotFoundException("Админ не был найден");
        if(!shopRepo.existsById(shop_id))
            throw new ShopNotFoundedException("Магазин не найден");
        AdminEntity admin = adminRepo.findById(id).get();
        ShopEntity shop = shopRepo.findById(shop_id).get();
        admin.setShop(shop);
        shop.setAdmin(admin);
        shopRepo.save(shop);
        adminRepo.save(admin);
        return Shop.toModel(shop);
    }

    public List<Admin> getFree() {
        List<AdminEntity> adminEntities = (List<AdminEntity>) adminRepo.findAll();
        List<Admin> admins = new ArrayList<>();
        for(AdminEntity ae : adminEntities){
            if(ae.getRole().equals("управляющий") && ae.getShop() == null)
            admins.add(Admin.toModel(ae));
        }
        return admins;
    }

}
