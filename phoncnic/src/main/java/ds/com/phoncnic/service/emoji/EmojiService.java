package ds.com.phoncnic.service.emoji;

import java.util.List;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.entity.EmojiInfo;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;

public interface EmojiService {
    Long[][] getEmojiCountArrayByGno(Long gno);

    List<EmojiDTO> dyningEmojiList(Long dno);

    List<EmojiDTO> getEmojiList(String id);

    void emojiRemove(Long eno);

    Long dyningEmojiRegister(EmojiDTO emojiDTO);

    Long galleryEmojiRegiter(EmojiDTO emojiDTO);

    List<EmojiDTO> getEmojiByGno(String type, Long no);

    Long getEmojitypeCwt(Long dno, String emojitype);

    /* DTO -> Entity */
    default Emoji dtoToEntity(EmojiDTO emojiDTO) {
        if (emojiDTO.getGno() == null) {
            Emoji emoji = Emoji.builder()
                    .eno(emojiDTO.getEno())
                    .member(Member.builder().id(emojiDTO.getId()).build())
                    .dyning(Dyning.builder().dno(emojiDTO.getDno()).build())
                    .emojiInfo(EmojiInfo.builder().emojitype(emojiDTO.getEmojitype()).build())
                    .build();
            return emoji;
        } else {
            Emoji emoji = Emoji.builder()
                    .eno(emojiDTO.getEno())
                    .member(Member.builder().id(emojiDTO.getId()).build())
                    .gallery(Gallery.builder().gno(emojiDTO.getGno()).build())
                    .emojiInfo(EmojiInfo.builder().emojitype(emojiDTO.getEmojitype()).build())
                    .build();
            return emoji;
        }
    }

    /* Entity -> DTO */
    default EmojiDTO entityToEmojiDTO(Emoji emoji) {

        if (emoji.getDyning() == null) {
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
