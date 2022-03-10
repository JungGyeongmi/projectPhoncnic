package ds.com.phoncnic.controller.dyningController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/dyning/setting")
@RequiredArgsConstructor
public class DyningSettingController {

    @GetMapping("/register")
    public void register() {
        log.info("dyning register.....");
    }

    // 가게를 처음으로 등록할 때 뜨는 페이지
    @PostMapping("/register")
    public String register(Model model) {
        log.info("dyning register.....");

        // 가게를 등록하고 난 후 아래로 이동
        return "/dyning/manage/read";
    }
    
}