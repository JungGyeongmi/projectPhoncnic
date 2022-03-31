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
    public Long galleryEmojiRegiter(EmojiDTO emojiDTO) {
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
        log.info("gno......:" + no);
        log.info("info......:" + info);
        List<EmojiDTO> emojiDTOList = new ArrayList<>();
        emojiList.stream().forEach(emoji -> {
            EmojiDTO emojiDTO = entityToEmojiDTO(emoji);
            emojiDTOList.add(emojiDTO);
        });
        log.info("emojiList......:" + emojiList);
        log.info("emojiDTOList......:" + emojiDTOList);
        return emojiDTOList;
    }

    @Override
    public Long[][] getEmojiCountArrayByGno(Long gno) {
        
        List<Object[]> result = emojiRepository.getEmojiCountByGno(gno);
        Long[][] emojiCntArr = new Long[5][2];
       
        for (int i = 0; i < emojiCntArr.length; i++) {
            emojiCntArr[i][0] = Long.valueOf(i + 1);
            emojiCntArr[i][1] = Long.valueOf(0);
        }

        result.stream().forEach(obj -> {
            String type = (obj[0]).toString();
            Long count = (Long) (obj[1]);
            switch (type) {
                case "1":
                    emojiCntArr[0][1] = count;
                    break;
                case "2":
                    emojiCntArr[1][1] = count;
                    break;
                case "3":
                    emojiCntArr[2][1] = count;
                    break;
                case "4":
                    emojiCntArr[3][1] = count;
                    break;
                case "5":
                    emojiCntArr[4][1] = count;
                    break;
            }
        });
        return emojiCntArr;
    }

    @Override
    public void emojiRemove(Long eno) {
        Optional<Emoji> emoji = emojiRepository.findById(eno);
        if (emoji.isPresent()) {
            Emoji result = emoji.get();
            emojiRepository.delete(result);
        }
    }

    @Override
    public Long getEmojitypeCwt(Long dno, String emojitype) {
        Long emojicwt = emojiRepository.getEmojiCountByEmojitype(dno, emojitype);
        return emojicwt;
    }

    @Override
    public Emoji HaveEmoji(String id, Long dno) {
        Emoji emoji = emojiRepository.getEnoAndType(id, dno);
        return emoji;
    }
}
