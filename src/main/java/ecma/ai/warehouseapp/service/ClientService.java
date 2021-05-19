package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Client;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ApiResponse addClient(Client client){
        Client addingClient = new Client();
        addingClient.setName(client.getName());
        addingClient.setPhoneNumber(client.getPhoneNumber()); // number tekshirilishi kk

        clientRepository.save(addingClient);
        return new ApiResponse("Added Successfully", true);

    }

    public ApiResponse editClientInfo(Integer id,Client client){
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()){
            Client editingClient = byId.get();
            editingClient.setName(client.getName());
            editingClient.setPhoneNumber(client.getPhoneNumber()); // number tekshirilishi kk
            clientRepository.save(editingClient);
            return new ApiResponse("edited Successfully",true);

        }

        return new ApiResponse("Id Not fount",false);
    }

    public ApiResponse getAll(){
        List<Client> all = clientRepository.findAll();
        return new ApiResponse("Clients List",true,all);
    }

    public ApiResponse deleteClient(Integer id){
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()){
            clientRepository.deleteById(id);
            return new ApiResponse("deleted Successfulyy", true);
        }

        return new ApiResponse("Id not fount", false);
    }
}
