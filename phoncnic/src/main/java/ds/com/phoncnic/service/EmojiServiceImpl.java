package ds.com.phoncnic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.repository.EmojiRepository;

@Service
public class EmojiServiceImpl implements EmojiService {

    @Autowired
    private EmojiRepository emojiRepository;

    @Override
    public List<EmojiDTO> getEmojiList(String id) {

        List<Emoji> emojiList = emojiRepository.getEmojiByMember(id);
        
        // List<EmojiDTO> emojiDTOList =
        //  emojiList.stream().map(emoji::entityToDTO)
        // .collect(Collectors.toList());
        
        List<EmojiDTO> emojiDTOList = new ArrayList<>();
        emojiList.stream().forEach(emoji->{
            EmojiDTO emojiDTO = entityToEmojiDTO(emoji);
            emojiDTOList.add(emojiDTO);
        });

        return emojiDTOList;
    }
    
}

