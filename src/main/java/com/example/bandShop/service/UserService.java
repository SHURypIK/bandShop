package com.example.bandShop.service;

import com.example.bandShop.entity.CartEntity;
import com.example.bandShop.entity.ProductEntity;
import com.example.bandShop.entity.UserEntity;
import com.example.bandShop.exception.PasswordUncorectException;
import com.example.bandShop.exception.UserAlreadyExistException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.model.OrdersHistory;
import com.example.bandShop.model.PersonalInformation;
import com.example.bandShop.model.User;
import com.example.bandShop.model.WishList;
import com.example.bandShop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        UserEntity usere = userRepo.findByLogin(user.getLogin());
        if (usere != null){
            throw new UserAlreadyExistException("Пользователь с таким логином уже существует");
        }
        usere.setCart(new CartEntity());
        usere.setOrderHistory(new ArrayList<>());
        usere.setReviews(new ArrayList<>());
        usere.setWishlist(new ArrayList<>());
        return userRepo.save(usere);
    }

    public User getOne(Integer id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if(user == null)
            throw new UserNotFoundException("Пользователь не был найден");
        return User.toModel(user);
    }

    public Integer delete(Integer id)  {
        userRepo.deleteById(id);
        return id;
    }

    public User updateUser (UserEntity user) throws UserNotFoundException, UserAlreadyExistException {
        if(!userRepo.findById(user.getId()).isPresent()){
            if(userRepo.findByLogin(user.getLogin()) != null && userRepo.findByLogin(user.getLogin()) != userRepo.findById(user.getId()).get())
                throw new UserAlreadyExistException("Логин занят");
            userRepo.save(user);
            return User.toModel(user);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }

    public User enter(UserEntity user) throws UserNotFoundException, PasswordUncorectException {
        UserEntity usere = userRepo.findByLogin(user.getLogin());
        if(user == null)
            throw new UserNotFoundException("Пользователь не был найден");
        if(!user.getPassword().equals(usere.getPassword()))
            throw new PasswordUncorectException("Не верный пароль");
        return User.toModel(usere);
    }

    public List<User> getUsers() {
        List<UserEntity> userEntities = (List<UserEntity>) userRepo.findAll();
        List<User> users = new ArrayList<>();
        for(UserEntity ue : userEntities){
            users.add(User.toModel(ue));
        }
        return users;
    }

    public WishList addToWishList(ProductEntity product, int user_id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(user_id).get();
        if(user == null)
            throw new UserNotFoundException("Пользователь не был найден");
        user.getWishlist().add(product);
        userRepo.save(user);
        return WishList.toModel(user);
    }

    public PersonalInformation updatePerosnalInfo(UserEntity user) throws UserNotFoundException {
        if(!userRepo.findById(user.getId()).isPresent()){
            userRepo.save(user);
            return PersonalInformation.toModel(user);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }

    public WishList getWishList(int user_id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(user_id).get();
        if(user == null)
            throw new UserNotFoundException("Пользователь не был найден");
        return WishList.toModel(user);
    }

    public OrdersHistory getOrderHistory(int user_id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(user_id).get();
        if(user == null)
            throw new UserNotFoundException("Пользователь не был найден");
        return OrdersHistory.toModel(user);
    }
}
