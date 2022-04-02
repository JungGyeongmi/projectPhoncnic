package ds.com.phoncnic.controller.dyningController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.dyning.DyningService;
import ds.com.phoncnic.service.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/manage/dyning")
@RequiredArgsConstructor
public class DyningSettingController {

    private final DyningService dyningService;
    private final EmojiService emojiService;

    //등록가게 리스트
    @GetMapping("/list")
    public void dyningList(@AuthenticationPrincipal AuthMemberDTO dto, Model model) {
        model.addAttribute("result", dyningService.getMyDyningList(dto.getId()));
        model.addAttribute("id",dto.getId());
    }

    //가게 등록 폼
    @GetMapping("/register")
    public String dyningRegister(@AuthenticationPrincipal AuthMemberDTO dto, Model model) {
        model.addAttribute("id",dto.getId());
        model.addAttribute("roofList",dyningService.roofimageList());
        return "/manage/dyning/register";
    }

    //가게 수정 폼
    @GetMapping("/modify")
    public void getDyningModify(@RequestParam("dno") Long dno,@AuthenticationPrincipal AuthMemberDTO dto, Model model) {
        log.info("Modify................."+dno);
        if(dno!=0){
        model.addAttribute("result", dyningService.getDyningDetails(dno));
        model.addAttribute("roofList",dyningService.roofimageList());
        model.addAttribute("imageresult", dyningService.getDyningDetails(dno).getDyningImageDTOList());

        model.addAttribute("id",dto.getId());
        }
    }

    //가게 수정 폼
    @PostMapping("/modify")
    public String postDyningModify(DyningDTO dyningDTO) {

        log.info("Modifying......"+dyningDTO);
        dyningService.modify(dyningDTO);

        return "redirect:/manage/dyning/read?dno="+dyningDTO.getDno();
    }

    //등록 가게 열람 폼
    @GetMapping("/read")
    public String dyningRead(@RequestParam("dno") Long dno, Model model) {
        log.info("Read................."+dno);
        if(dno!=0){
        model.addAttribute("result", dyningService.getDyningDetails(dno));
        model.addAttribute("imageresult", dyningService.getDyningDetails(dno).getDyningImageDTOList());
        model.addAttribute("emojilist", emojiService.dyningEmojiList(dno));
        model.addAttribute("emojitype1",emojiService.getEmojitypeCwt(dno, "1"));
        model.addAttribute("emojitype2",emojiService.getEmojitypeCwt(dno, "2"));
        model.addAttribute("emojitype3",emojiService.getEmojitypeCwt(dno, "3"));
        model.addAttribute("emojitype4",emojiService.getEmojitypeCwt(dno, "4"));
        model.addAttribute("emojitype5",emojiService.getEmojitypeCwt(dno, "5"));
        }
        return "/manage/dyning/read";
    }

    // 가게를 처음으로 등록시
    @PostMapping("/register")
    public String register(DyningDTO dyningDTO, RedirectAttributes ra) {
        log.info("dyning register....."+dyningDTO);
        Long dno = dyningService.register(dyningDTO);

        ra.addFlashAttribute("dno",dno+"번째 가게 등록 왼료");

        // 가게를 등록하고 난 후 바로 아래로 이동
        return "redirect:/manage/dyning/list?id="+dyningDTO.getId();
    }

    @PostMapping("/remove")
    public String remove(@AuthenticationPrincipal AuthMemberDTO dto, Long dno) {
        dyningService.removeWithImages(dno);
        return "redirect:/manage/dyning/list?id="+dto.getId();
    }

}
