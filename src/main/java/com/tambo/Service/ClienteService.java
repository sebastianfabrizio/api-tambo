package com.tambo.Service;
import com.tambo.Model.Cliente;
import com.tambo.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente not found with id " + id));
        existingCliente.setNom_cliente(cliente.getNom_cliente());
        existingCliente.setEmail(cliente.getEmail());
        existingCliente.setNumero(cliente.getNumero());

        return clienteRepository.save(existingCliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
