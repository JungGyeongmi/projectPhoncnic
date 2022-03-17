package ds.com.phoncnic.service.emoji;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        // emojiList.stream().map(emoji::entityToDTO)
        // .collect(Collectors.toList());

        List<EmojiDTO> emojiDTOList = new ArrayList<>();
        emojiList.stream().forEach(emoji -> {
            EmojiDTO emojiDTO = entityToEmojiDTO(emoji);
            emojiDTOList.add(emojiDTO);
        });

        return emojiDTOList;
    }

    @Override
    public List<EmojiDTO> getEmojiList(String type, Long no) {
        List<Emoji> emojiList = type.equals("g") ? emojiRepository.getEmojiByGno(no)
                : emojiRepository.getEmojiByDno(no);

        List<EmojiDTO> emojiDTOList = new ArrayList<>();
        emojiList.stream().forEach(emoji -> {
            EmojiDTO emojiDTO = entityToEmojiDTO(emoji);
            emojiDTOList.add(emojiDTO);
        });

        return emojiDTOList;
    }


    @Override
    public void emojiRemove(Long eno){
        Optional<Emoji> emoji = emojiRepository.findById(eno);
        if(emoji.isPresent()){
            Emoji result = emoji.get();
            emojiRepository.delete(result);
        }
    }

}

}
