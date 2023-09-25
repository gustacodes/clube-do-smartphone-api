package com.clube.smartphone.services;

import com.clube.smartphone.entities.Aparelho;
import com.clube.smartphone.repositories.AparelhoRepository;
import org.springframework.stereotype.Service;

@Service
public class AparelhoService {

    private AparelhoRepository repository;

    public AparelhoService(AparelhoRepository repository) {
        this.repository = repository;
    }

    public void save(Aparelho aparelho) {
        repository.save(aparelho);
    }
}
