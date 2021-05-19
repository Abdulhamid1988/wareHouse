package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.User;
import ecma.ai.warehouseapp.entity.Warehouse;
import ecma.ai.warehouseapp.model.UserDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.UserRepository;
import ecma.ai.warehouseapp.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse addUser(UserDTO userDTO){
        User addingUser = new User();
        addingUser.setFirstName(userDTO.getFirstName());
        addingUser.setLastName(userDTO.getLastName());
        boolean checkPhone = warehouseRepository.existsByName(userDTO.getPhoneNumber());
        if (checkPhone){
            return new ApiResponse("bu phone number DB da mavjud",false);
        }

        addingUser.setPhoneNumber(userDTO.getPhoneNumber());
        addingUser.setActive(userDTO.getActive());//
        addingUser.setPassword(userDTO.getPassword());

        Set<Integer> integers = userDTO.getWare_house_id();
        Set<Warehouse> warehouses = new HashSet<>();

        for (Integer integer : integers) {
            Optional<Warehouse> byId = warehouseRepository.findById(integer);
            if (byId.isPresent()){
                Warehouse warehouse = byId.get();
                warehouses.add(warehouse);
            }
            else {
                return new ApiResponse("wareHouse id not fount",false);
            }
        }

        addingUser.setWarehouses(warehouses);
        userRepository.save(addingUser);
        return new ApiResponse("added successfully",true);
    }

    public ApiResponse getAll(){
        List<User> all = userRepository.findAll();

        return new ApiResponse("Users list",true,all);
    }

    public ApiResponse getOne(Integer id){
        Optional<User> byId = userRepository.findById(id);
        return byId.map(user -> new ApiResponse("gettOneById", true, user)).orElseGet(() ->
                new ApiResponse("id not fount", false));

    }

    public ApiResponse editUser(Integer id,UserDTO userDTO){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            User user = byId.get();

            user.setFirstName(userDTO.getFirstName());

            user.setLastName(userDTO.getLastName());

            user.setPhoneNumber(userDTO.getPhoneNumber());

            user.setActive(userDTO.getActive());

            user.setPassword(userDTO.getPassword());

            Set<Integer> integers = userDTO.getWare_house_id();

            Set<Warehouse> warehouses = new HashSet<>();

            for (Integer integer : integers) {

                Optional<Warehouse> warehouseId = warehouseRepository.findById(integer);

                if (warehouseId.isPresent()){

                    Warehouse warehouse = warehouseId.get();
                    if(warehouse.getActive()) {
                        warehouses.add(warehouse);
                        user.setWarehouses(warehouses);
                    }else {
                        return new ApiResponse("Bu WareHouse active emas",false);
                    }
                }
                else {
                    return new ApiResponse("wareHouse id not fount",false);
                }
            }

            userRepository.save(user);
            return new ApiResponse("edited successfully",true);

        }

        return new ApiResponse("id not fount please try again later",false);
    }

    public ApiResponse deleteUsers(Integer id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            userRepository.deleteById(id);
            return new ApiResponse("deleted successfully",true);
        }

        return new ApiResponse("USER ID NOT FOUNT",false);
    }
}
