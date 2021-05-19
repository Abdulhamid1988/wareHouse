package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.entity.Client;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.ClientRepository;
import ecma.ai.warehouseapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    ClientRepository clientRepository;


    @Autowired
    ClientService clientService;

    @PostMapping("/add")
    public ApiResponse addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse editClientInfo(@PathVariable Integer id,@RequestBody Client client){
            return clientService.editClientInfo(id,client);
    }

    @GetMapping("/all")
    public ApiResponse getAll(){
        return clientService.getAll();
    }

    @DeleteMapping("/deleteClient/{id}")
    public ApiResponse deleteClient(@PathVariable Integer id){
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()){
            clientRepository.deleteById(id);
            return new ApiResponse("Deleted successfully",true);
        }

        return new ApiResponse("Client id not fount", false);
    }

}
