package ds.com.phoncnic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.repository.EmojiRepository;

public class EmojiServiceImpl implements EmojiService {

    @Autowired
    private EmojiRepository emojiRepository;

    @Override
    public List<EmojiDTO> getEmojiList(String id) {
        
        List<EmojiDTO> emojiDTOList = new ArrayList<>();
        Emoji emoji = emojiRepository.getEmojiByMember(id);
        EmojiDTO emojiDTO = entityToEmojiDTO(emoji);


        IntStream.rangeClosed(0, 10).forEach(i->{
            emojiDTOList.set(i, emojiDTO);
        });


        return emojiDTOList;
    }
    
}
