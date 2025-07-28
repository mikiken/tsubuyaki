package jp.kobe_u.cs.daikibo.tsubuyaki.controller;

import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import jp.kobe_u.cs.daikibo.tsubuyaki.service.TsubuyakiService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TsubuyakiController {
    @Autowired
    TsubuyakiService ts;

    // タイトル画面を表示
    @GetMapping("/")
    String showIndex() {
        return "index";
    }

    // メイン画面を表示
    @GetMapping("/read")
    String showTsubuyakiList(Model model,
            @RequestParam(name = "keyword", required = false) String keyword) {
        List<Tsubuyaki> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = ts.searchTsubuyaki(keyword);
        } else {
            list = ts.getAllTsubuyaki();
        }
        model.addAttribute("tsubuyakiList", list);
        model.addAttribute("tsubuyakiForm", new TsubuyakiForm());
        return "tsubuyaki_list";
    }

    // つぶやきを投稿
    @PostMapping("/read")
    String postTsubuyaki(@ModelAttribute("tsubuyakiForm") TsubuyakiForm form, Model model) {
        // フォームからエンティティに移し替え
        Tsubuyaki t = new Tsubuyaki();
        t.setName(form.getName());
        t.setComment(form.getComment());
        // サービスに投稿処理を依頼
        ts.postTsubuyaki(t);
        return "redirect:/read"; // メイン画面に転送
    }
}
