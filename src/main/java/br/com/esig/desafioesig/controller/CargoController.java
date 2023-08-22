package br.com.esig.desafioesig.controller;

import br.com.esig.desafioesig.domain.Cargo;
import br.com.esig.desafioesig.repository.CargoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named(value = "cargoController")
@ViewScoped
public class CargoController implements Serializable {

    @Autowired
    private CargoRepository repository;

    @Getter
    @Setter
    private Cargo cargo;

    @Setter
    private List<Cargo> cargos = new ArrayList<>();

    public List<Cargo> getCargos() {
        try {
            this.cargos = repository.findAll();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return cargos;
    }

    public List<Cargo> listarCargos(){
        List<Cargo> list;
        list = repository.findAll();
        return list;
    }

    public Optional<Cargo> buscaPorId(Integer id) {
        return repository.findById(id);
    }
}
