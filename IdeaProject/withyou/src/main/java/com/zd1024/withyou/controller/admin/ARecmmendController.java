package com.zd1024.withyou.controller.admin;

import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.Util.PictrueDealUtil;
import com.zd1024.withyou.entity.Recommend.Book;
import com.zd1024.withyou.entity.Recommend.CurePlan;
import com.zd1024.withyou.entity.Recommend.Music;
import com.zd1024.withyou.entity.Recommend.Videos;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.RecommendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/recmd")
public class ARecmmendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/")
    public String getAllRcmdByCtgy(Integer current, String ctgy, Model model) {
        ObjVo datavo = recommendService.getAllRecommendByCtgy(current, 10, ctgy);
        model.addAttribute(ctgy + "Vo", datavo);
        return "recommend/" + ctgy + "info";
    }

    @PostMapping("/addBook")
    public String addRecommendByCtgy(Book book, Model model) {
        if (book.getUpPic() != null) {
                   book.setFirstPictrue(PictrueDealUtil.savePictrue(book.getUpPic()));
        }
        int rs = recommendService.addNewRecommend(book, "book");

        if (rs == 1) {
            model.addAttribute("result", "书籍推荐添加成功");
        } else {
            model.addAttribute("result", "书籍推荐添加失败");
        }
        return getAllRcmdByCtgy(1, "book", model);
    }

    @PostMapping("/addMusic")
    public String addRecommendByCtgy(Music music, Model model) {
        if (music.getUpPic()!=null){
            music.setFirstPictrue(PictrueDealUtil.savePictrue(music.getUpPic()));
        }
        int rs = recommendService.addNewRecommend(music, "music");
        if (rs == 1) {
            model.addAttribute("result", "书籍推荐添加成功");
        } else {
            model.addAttribute("result", "书籍推荐添加失败");
        }
        return getAllRcmdByCtgy(1, "music", model);
    }

    @PostMapping("/addVideos")
    public String addRecommendByCtgy(Videos videos, Model model) {
        if (videos.getUpPic()!=null){
            videos.setFirstPictrue(PictrueDealUtil.savePictrue(videos.getUpPic()));
        }
        int rs = recommendService.addNewRecommend(videos, "videos");
        if (rs == 1) {
            model.addAttribute("result", "书籍推荐添加成功");
        } else {
            model.addAttribute("result", "书籍推荐添加失败");
        }
        return getAllRcmdByCtgy(1, "videos", model);
    }

    @PostMapping("/addCureplan")
    public String addRecommendByCtgy(CurePlan curePlan, Model model) {
        int rs = recommendService.addNewRecommend(curePlan, "cureplan");
        if (rs == 1) {
            model.addAttribute("result", "书籍推荐添加成功");
        } else {
            model.addAttribute("result", "书籍推荐添加失败");
        }
        return getAllRcmdByCtgy(1, "cureplan", model);
    }

    @GetMapping("/delete")
    public String deleteRecommendById(Integer current, String recmId, String ctgy, Model model) {
        int rs = recommendService.deleteRecommendById(recmId, ctgy);
        if (rs == 1) {
            model.addAttribute("result", "删除成功");
        } else {
            model.addAttribute("result", "删除失败");
        }
        return getAllRcmdByCtgy(current, ctgy, model);
    }
}
