package com.example.lab3.service;

import com.example.lab3.model.Info;
import com.example.lab3.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {

    private final InfoRepository infoRepository;

    @Autowired
    public InfoService(InfoRepository infoRepository){
        this.infoRepository=infoRepository;
    }
    public Info findById(Long id){
        return infoRepository.getOne(id);
    }
    public List<Info> findAll(){
        return infoRepository.findAll();
    }
    public Info saveInfo(Info info){
        return infoRepository.save(info);
    }
    public void deleteById(Long id){
        infoRepository.deleteById(id);
    }
}
