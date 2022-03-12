package ds.com.phoncnic.service.emoji;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.entity.EmojiInfo;
import ds.com.phoncnic.repository.EmojiInfoRepository;

@Service
public class EmojiInfoServiceImpl implements EmojiInfoService {
    
    @Autowired
    EmojiInfoRepository emojiInfoRepository;

    @Override
    public List<EmojiInfo> getEmojiInfoList() {
        
        List<EmojiInfo>  emojiInfoList = emojiInfoRepository.getEmojiInfoAll();
        
        return emojiInfoList;
    }
    
}
