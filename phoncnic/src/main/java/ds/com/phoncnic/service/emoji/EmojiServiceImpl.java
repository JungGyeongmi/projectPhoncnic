package ds.com.phoncnic.service.emoji;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.repository.EmojiRepository;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class EmojiServiceImpl implements EmojiService {

    @Autowired
    private EmojiRepository emojiRepository;

    @Override
    public Long dyningEmojiRegister(EmojiDTO emojiDTO) {
        Emoji dyningemoji = dtoToEntity(emojiDTO);
        emojiRepository.save(dyningemoji);

        return dyningemoji.getEno();
    }

    @Override
    public Long galleryEmojiRegiter(EmojiDTO emojiDTO){
        Emoji galleryEmoji = dtoToEntity(emojiDTO);
        emojiRepository.save(galleryEmoji);
        return galleryEmoji.getEno();
    }

    @Override
    public List<EmojiDTO> dyningEmojiList(Long dno) {
        List<Emoji> emojilist = emojiRepository.findByDno(dno);
        List<EmojiDTO> emojiDTOList = new ArrayList<>();
        emojilist.stream().forEach(emoji -> {
            EmojiDTO emojiDTO = entityToEmojiDTO(emoji);
            emojiDTOList.add(emojiDTO);
            
        });

        return emojiDTOList;
    }

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
    public List<EmojiDTO> getEmojiByGno(String info, Long no) {
        List<Emoji> emojiList = info.equals("g") ? emojiRepository.getEmojiByGno(no)
        : emojiRepository.getEmojiByDno(no);
        log.info("gno......:"+no);
        log.info("info......:"+info);
        
        List<EmojiDTO> emojiDTOList = new ArrayList<>();
        emojiList.stream().forEach(emoji -> {
            EmojiDTO emojiDTO = entityToEmojiDTO(emoji);
            emojiDTOList.add(emojiDTO);
        });
        log.info("emojiList......:"+emojiList);
        log.info("emojiDTOList......:"+emojiDTOList);

    return emojiDTOList;
    }

    

    // @Override
    // public List<EmojiDTO> getEmojiByGno2(Long gno) {
        
    //     List<Emoji> emojilist = emojiRepository.getEmojiByGno(gno);
    //     List<EmojiDTO> emojiDTOList = new ArrayList<>();
    //     emojilist.stream().forEach(emoji -> {
    //         EmojiDTO emojiDTO = entityToEmojiDTO(emoji);
    //         emojiDTOList.add(emojiDTO);
    //     });
    //     return emojiDTOList;
    // }

    @Override
    public void emojiRemove(Long eno) {
        Optional<Emoji> emoji = emojiRepository.findById(eno);
        if (emoji.isPresent()) {
            Emoji result = emoji.get();
            emojiRepository.delete(result);
        }
    }

}
