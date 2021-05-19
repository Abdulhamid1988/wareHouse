package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.model.UserDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.UserRepository;
import ecma.ai.warehouseapp.repository.WarehouseRepository;
import ecma.ai.warehouseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ApiResponse addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @GetMapping("/allUser")
    public ApiResponse getlList(){
        return userService.getAll();
    }

    @GetMapping("/getOneById/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        return userService.getOne(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse editUser(@PathVariable Integer id,@RequestBody UserDTO userDTO){
        return userService.editUser(id,userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteById(@PathVariable Integer id){
        return userService.deleteUsers(id);
    }

}
