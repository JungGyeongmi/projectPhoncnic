package ds.com.phoncnic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.repository.CharacterLookInfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterLookServiceImpl implements CharacterLookService{
    @Autowired
    private CharacterLookInfoRepository CharacterLookInfoRepository;
    
    
}
