// package ds.com.phoncnic.controller.dyningController;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import ds.com.phoncnic.service.dyning.DyningService;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @Controller
// @Log4j2
// @RequestMapping("/dyning/setting")
// @RequiredArgsConstructor
// public class DyningSettingController {

//     private final DyningService dyningService;

//     @GetMapping("/register")
//     public void register() {
//         log.info("dyning register.....");
//     }

//     // 가게를 처음으로 등록할 때 뜨는 페이지
//     @PostMapping("/register")
//     public String register(Model model) {
//         log.info("dyning register.....");

//         // 가게를 등록하고 난 후 아래로 이동
//         return "/dyning/manage/read";
//     }
    
//     // 가게가 등록되어 있는 사장이 등록을 눌렀을 때 뜨는 페이지
//     // 이미지 클릭 시 이미지 업로드 창이 뜨고, 이미지 업로드 시 기존 이미지는 삭제후 등록.
//     @GetMapping("/read")
//     public String read() {
//         log.info("dyning read.....");

//         return "redirect:/dyning/manage/read";
//     }

//     // 수정하기 눌렀을 때
//     @PostMapping("/read/modify")
//     public String read(Model model) {
//         log.info("dyning modify.....");

//         return "redirect:/dyning/manage/read";
//     }

//     @GetMapping("/read/remove")
//     public String remove() {
//         log.info("dyning remove.....");

//         return "redirect:/dyning/manage/register";
//     }
// }
