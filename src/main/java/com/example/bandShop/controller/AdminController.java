package com.example.bandShop.controller;

import com.example.bandShop.entity.AdminEntity;
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
                        return ResponseEntity.badRequest().body("Произошла ошибка");
                }
        }

        @GetMapping("/{id}")
        public ResponseEntity getOneAdmin(@PathVariable Integer id){
                try {
                        return  ResponseEntity.ok(adminService.getOne(id));
                }catch(UserNotFoundException ex){
                        return ResponseEntity.badRequest().body(ex.getMessage());
                } catch(Exception e){
                        return ResponseEntity.badRequest().body("Произошла ошибка");
                }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity deleteAdmoin(@PathVariable Integer id){
                try {
                        return  ResponseEntity.ok(adminService.delete(id));
                } catch(Exception e){
                        return ResponseEntity.badRequest().body("Произошла ошибка");
                }
        }

        @GetMapping("/enter")
        public ResponseEntity enterAdmin(@RequestBody AdminEntity admin){
                try {
                        return  ResponseEntity.ok(adminService.enter(admin));
                }catch(UserNotFoundException ex){
                        return ResponseEntity.badRequest().body(ex.getMessage());
                } catch(Exception e){
                        return ResponseEntity.badRequest().body("Произошла ошибка");
                }
        }
}
