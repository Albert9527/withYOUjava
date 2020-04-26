package com.zd1024.withyou.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zd1024.withyou.dao.Recommend.BookMapper;
import com.zd1024.withyou.dao.Recommend.CurePlanMapper;
import com.zd1024.withyou.dao.Recommend.MusicMapper;
import com.zd1024.withyou.dao.Recommend.VideosMapper;
import com.zd1024.withyou.entity.Recommend.Book;
import com.zd1024.withyou.entity.Recommend.CurePlan;
import com.zd1024.withyou.entity.Recommend.Music;
import com.zd1024.withyou.entity.Recommend.Videos;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private VideosMapper videosMapper;

    @Autowired
    private CurePlanMapper curePlanMapper;

    @Override
    public ObjVo getAllRecommendByCtgy(Integer current, Integer size, String ctgy) {
        ObjVo objVo = new ObjVo();
        objVo.setSize(size);
        objVo.setCurrent(current);
        switch (ctgy) {
            case "book":
                IPage<Book> bookpage = new Page<>(current, size);
                bookMapper.selectPage(bookpage, null);
                return dealData(bookpage, objVo);

            case "music":
                IPage<Music> musicpage = new Page<>(current, size);
                musicMapper.selectPage(musicpage, null);
                return dealData(musicpage, objVo);

            case "videos":
                IPage<Videos> vdpage = new Page<>(current, size);
                videosMapper.selectPage(vdpage, null);
                return dealData(vdpage, objVo);

            case "cureplan":
                IPage<CurePlan> cplpage = new Page<>(current, size);
                curePlanMapper.selectPage(cplpage, null);
                return dealData(cplpage, objVo);
            default:
                return null;
        }
    }

    @Override
    public int addNewRecommend(Object recommend, String ctgy) {
        return 0;
    }

    private ObjVo dealData(IPage page, ObjVo objVo) {
        objVo.setTotal(page.getTotal());
        objVo.setDatalist(page.getRecords());
        objVo.setPages(page.getPages());

        return objVo;
    }

}
