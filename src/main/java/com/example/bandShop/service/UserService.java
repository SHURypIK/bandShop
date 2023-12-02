package com.example.bandShop.service;

import com.example.bandShop.entity.CartEntity;
import com.example.bandShop.entity.UserEntity;
import com.example.bandShop.exception.PasswordUncorectException;
import com.example.bandShop.exception.ProductNotFoundedException;
import com.example.bandShop.exception.UserAlreadyExistException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.model.OrdersHistory;
import com.example.bandShop.model.PersonalInformation;
import com.example.bandShop.model.User;
import com.example.bandShop.model.WishList;
import com.example.bandShop.repository.CartRepo;
import com.example.bandShop.repository.ProductRepo;
import com.example.bandShop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        UserEntity usere = userRepo.findByLogin(user.getLogin());
        if (usere != null){
            throw new UserAlreadyExistException("Пользователь с таким логином уже существует");
        }
        CartEntity cart = new CartEntity();
        user.setCart(cart);
        user.setOrderHistory(new ArrayList<>());
        user.setReviews(new ArrayList<>());
        user.setWishlist(new ArrayList<>());
        userRepo.save(user);
        cartRepo.save(cart);
        return user;
    }

    public User getOne(Integer id) throws UserNotFoundException {
        if(!userRepo.existsById(id))
            throw new UserNotFoundException("Пользователь не был найден");
        return User.toModel(userRepo.findById(id).get());
    }

    public User delete(Integer id) throws UserNotFoundException {
        if(!userRepo.existsById(id))
            throw  new UserNotFoundException("Пользователь не найден");
        userRepo.deleteById(id);
        return new User();
    }

    public User updateUser (UserEntity user) throws UserNotFoundException, UserAlreadyExistException {
        if(userRepo.findById(user.getId()).isPresent()){
            if(userRepo.findByLogin(user.getLogin()) != null && userRepo.findByLogin(user.getLogin()) != userRepo.findById(user.getId()).get())
                throw new UserAlreadyExistException("Логин занят");
            userRepo.save(user);
            return User.toModel(user);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }

    public User enter(UserEntity user) throws UserNotFoundException, PasswordUncorectException {
        UserEntity usere = userRepo.findByLogin(user.getLogin());
        if(usere == null)
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

    public WishList addToWishList(String product_id, int user_id) throws UserNotFoundException, ProductNotFoundedException {

        if(!userRepo.existsById(user_id))
            throw new UserNotFoundException("Пользователь не был найден");
        UserEntity user = userRepo.findById(user_id).get();
        if(!productRepo.existsById(product_id))
            throw new ProductNotFoundedException("Продукт не найден");
        user.getWishlist().add(productRepo.findById(product_id).get());
        userRepo.save(user);
        return WishList.toModel(user);
    }

    public WishList dellFromWishList(String product_id, int user_id) throws UserNotFoundException, ProductNotFoundedException {

        if(!userRepo.existsById(user_id))
            throw new UserNotFoundException("Пользователь не был найден");
        UserEntity user = userRepo.findById(user_id).get();
        if(!productRepo.existsById(product_id))
            throw new ProductNotFoundedException("Продукт не найден");
        user.getWishlist().remove(productRepo.findById(product_id).get());
        userRepo.save(user);
        return WishList.toModel(user);
    }

    public PersonalInformation updatePerosnalInfo(UserEntity user) throws UserNotFoundException {
        if(userRepo.existsById(user.getId())){
            UserEntity usere = userRepo.findById(user.getId()).get();
            usere.setBirthday(user.getBirthday());
            usere.setFirstName(user.getFirstName());
            usere.setLastName(user.getLastName());
            usere.setMobile(user.getMobile());
            userRepo.save(usere);
            return PersonalInformation.toModel(user);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }

    public WishList getWishList(int user_id) throws UserNotFoundException {
        if(!userRepo.existsById(user_id))
            throw new UserNotFoundException("Пользователь не был найден");
        return WishList.toModel(userRepo.findById(user_id).get());
    }

    public OrdersHistory getOrderHistory(int user_id) throws UserNotFoundException {
        if(!userRepo.existsById(user_id))
            throw new UserNotFoundException("Пользователь не был найден");
        return OrdersHistory.toModel(userRepo.findById(user_id).get());
    }
}
