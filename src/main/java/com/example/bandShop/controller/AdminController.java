package com.example.bandShop.controller;

import com.example.bandShop.entity.AdminEntity;
import com.example.bandShop.exception.PasswordUncorectException;
import com.example.bandShop.exception.ShopNotFoundedException;
import com.example.bandShop.exception.UserAlreadyExistException;
import com.example.bandShop.exception.UserNotFoundException;
import com.example.bandShop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

        @Autowired
        private AdminService adminService;


        @PostMapping
        public ResponseEntity registration(@RequestBody AdminEntity admin){
                try {
                        adminService.registration(admin);
                        return  ResponseEntity.ok("Админ успешно сохранен");
                } catch(UserAlreadyExistException ex){
                        return ResponseEntity.badRequest().body(ex.getMessage());
                }catch(Exception e){
                        return ResponseEntity.badRequest().body("Произошла ошибка" + e.getMessage());
                }
        }

        @GetMapping("/{id}")
        public ResponseEntity getOneAdmin(@PathVariable Integer id){
                try {
                        return  ResponseEntity.ok(adminService.getOne(id));
                }catch(UserNotFoundException ex){
                        return ResponseEntity.badRequest().body(ex.getMessage());
                } catch(Exception e){
                        return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
                }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity deleteAdmoin(@PathVariable Integer id){
                try {
                        return  ResponseEntity.ok(adminService.delete(id));
                } catch(UserNotFoundException ex){
                        return ResponseEntity.badRequest().body(ex.getMessage());
                } catch(Exception e){
                        return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
                }
        }

        @GetMapping("/enter")
        public ResponseEntity enterAdmin(@RequestBody AdminEntity admin){
                try {
                        return  ResponseEntity.ok(adminService.enter(admin));
                }catch(UserNotFoundException ex){
                        return ResponseEntity.badRequest().body(ex.getMessage());
                }catch(PasswordUncorectException exc){
                        return ResponseEntity.badRequest().body(exc.getMessage());
                } catch(Exception e){
                        return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
                }
        }

        @PutMapping("/{id}")
        public ResponseEntity setShop (@PathVariable Integer id,
                                       @RequestHeader("shop_id") int shop_id){
                try {
                        return  ResponseEntity.ok( adminService.setShop(id, shop_id));
                } catch(UserNotFoundException ex){
                        return ResponseEntity.badRequest().body(ex.getMessage());
                }catch(ShopNotFoundedException exc){
                        return ResponseEntity.badRequest().body(exc.getMessage());
                }catch(Exception e){
                        return ResponseEntity.badRequest().body("Произошла ошибка" + e.getMessage());
                }
        }

        @GetMapping("/free")
        public ResponseEntity getFree(){
                try {
                        return  ResponseEntity.ok(adminService.getFree());
                }catch(Exception e){
                        return ResponseEntity.badRequest().body("Произошла ошибка"+ e.getMessage());
                }
        }


}
