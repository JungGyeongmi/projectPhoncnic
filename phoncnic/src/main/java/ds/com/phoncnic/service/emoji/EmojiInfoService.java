package ds.com.phoncnic.service.emoji;

import java.util.List;

import ds.com.phoncnic.dto.EmojiInfoDTO;
import ds.com.phoncnic.entity.EmojiInfo;

public interface EmojiInfoService {
    List<EmojiInfo> getEmojiInfoList();
    

    /* Entity -> DTO */
    default EmojiInfoDTO entityToDTO(EmojiInfo emojiInfo) {

        EmojiInfoDTO emojiInfoDTO = EmojiInfoDTO.builder()
            .emojitype(emojiInfo.getEmojitype())
            .emojipath(emojiInfo.getEmojipath())
            .kindofemoji(emojiInfo.getKindofemoji())
            .build();
        return emojiInfoDTO;

    }

}
