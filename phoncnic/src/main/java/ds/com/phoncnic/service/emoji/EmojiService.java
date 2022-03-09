package ds.com.phoncnic.service.emoji;

import java.util.List;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.entity.EmojiInfo;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;


public interface EmojiService {
    List<EmojiDTO> getEmojiList(String id);
    
    List<EmojiDTO> getEmojiList(String type, Long no);
    
    /* DTO -> Entity */
    default Emoji dtoToEntity(EmojiDTO emojiDTO) {
        Emoji emoji = Emoji.builder()
                .eno(emojiDTO.getEno())
                .member(Member.builder().id(emojiDTO.getId()).build())
                .gallery(Gallery.builder().gno(emojiDTO.getGno()).build())
                .dyning(Dyning.builder().dno(emojiDTO.getDno()).build())
                .emojiInfo(EmojiInfo.builder().emojitype(emojiDTO.getEmojitype()).build())
                .build();
        return emoji;
    }

    /* Entity -> DTO */
    default EmojiDTO entityToEmojiDTO(Emoji emoji){
        // List<EmojiDTO> emojiDTOList = new ArrayList<>();
        if(emoji.getDyning()==null) {
            EmojiDTO emojiDTO = EmojiDTO.builder()
                .eno(emoji.getEno())
                .id(emoji.getMember().getId())
                .gno(emoji.getGallery().getGno())
                .dno(0L)
                .emojipath(emoji.getEmojiInfo().getEmojipath())
                .emojitype(emoji.getEmojiInfo().getEmojitype())
                .kindofemoji(emoji.getEmojiInfo().getKindofemoji())
                .regdate(emoji.getRegDate())
                .moddate(emoji.getModDate())
            .build();
            return emojiDTO;
        } else {
            EmojiDTO emojiDTO = EmojiDTO.builder()
                .eno(emoji.getEno())
                .id(emoji.getMember().getId())
                .dno(emoji.getDyning().getDno())
                .gno(0L)
                .emojipath(emoji.getEmojiInfo().getEmojipath())
                .emojitype(emoji.getEmojiInfo().getEmojitype())
                .kindofemoji(emoji.getEmojiInfo().getKindofemoji())
                .regdate(emoji.getRegDate())
                .moddate(emoji.getModDate())
            .build();
            return emojiDTO;
        }


    }


    
}
