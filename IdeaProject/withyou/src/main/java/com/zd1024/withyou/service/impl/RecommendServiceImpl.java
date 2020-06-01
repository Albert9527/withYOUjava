package com.zd1024.withyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zd1024.withyou.Util.MySHA512;
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

@Service
public class RecommendServiceImpl implements RecommendService {

    private MySHA512 mySHA512 = new MySHA512();

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
        int rs = -1;
        switch (ctgy) {
            case "book":
                Book book = (Book) recommend;
                book.setRcmbookId(mySHA512.getId("rcmbook"));
                rs = bookMapper.insert(book);
                break;

            case "music":
                Music music = (Music) recommend;
                music.setRcmMusicId(mySHA512.getId("rcmmusic"));
                rs = musicMapper.insert(music);
                break;
            case "videos":
                Videos videos = (Videos) recommend;
                videos.setRcmvdId(mySHA512.getId("rcmvideos"));
                rs = videosMapper.insert(videos);
                break;

            case "cureplan":
                CurePlan curePlan = (CurePlan) recommend;
                curePlan.setRcmcpId(mySHA512.getId("rcmcureplan"));
                rs = curePlanMapper.insert(curePlan);
                break;
            default:
                return rs;
        }
        return rs;
    }

    @Override
    public int deleteRecommendById(String recmId, String ctgy) {
        int rs = -1;
        switch (ctgy) {
            case "book":
                rs = bookMapper.deleteById(recmId);
                break;

            case "music":
                rs = musicMapper.deleteById(recmId);
                break;
            case "videos":
                rs = videosMapper.deleteById(recmId);
                break;

            case "cureplan":
                rs = curePlanMapper.deleteById(recmId);
                break;
            default:
                return rs;
        }
        return rs;
    }

    @Override
    public ObjVo searchrcm(Integer current, Integer size, String keyWord, String ctgy) {
        ObjVo objVo = new ObjVo();
        objVo.setSize(size);
        objVo.setCurrent(current);
        switch (ctgy) {
            case "book":
                IPage<Book> bookpage = new Page<>(current, size);
                QueryWrapper<Book> qwbook = new QueryWrapper<>();
                qwbook.like("rcmbook_name",keyWord)
                        .or()
                        .like("rcmbook_intro",keyWord)
                        .or()
                        .like("rcmbook_reason",keyWord)
                        .or()
                        .like("category_tag",keyWord);
                bookMapper.selectPage(bookpage, qwbook);
                return dealData(bookpage, objVo);

            case "music":
                IPage<Music> musicpage = new Page<>(current, size);
                QueryWrapper<Music> qwmusic = new QueryWrapper<>();
                qwmusic.like("rcm_music_name",keyWord)
                        .or()
                        .like("rcm_music_intro",keyWord)
                        .or()
                        .like("rcm_reason",keyWord)
                        .or()
                        .like("category_tag",keyWord);
                musicMapper.selectPage(musicpage, qwmusic);
                return dealData(musicpage, objVo);

            case "videos":
                IPage<Videos> vdpage = new Page<>(current, size);

                QueryWrapper<Videos> qwvideo = new QueryWrapper<>();
                qwvideo.like("rcmvd_title",keyWord)
                        .or()
                        .like("rcmvd_intro",keyWord)
                        .or()
                        .like("rcm_reason",keyWord)
                        .or()
                        .like("category_tag",keyWord);
                videosMapper.selectPage(vdpage, qwvideo);
                return dealData(vdpage, objVo);

            case "cureplan":
                IPage<CurePlan> cplpage = new Page<>(current, size);

                QueryWrapper<CurePlan> qwcp = new QueryWrapper<>();
                qwcp.like("rcmcp_name",keyWord)
                        .or()
                        .like("rcmcp_intro",keyWord)
                        .or()
                        .like("rcmcp_reason",keyWord)
                        .or()
                        .like("category_tag",keyWord);
                curePlanMapper.selectPage(cplpage, qwcp);
                return dealData(cplpage, objVo);
            default:
                return null;
        }
    }

    private ObjVo dealData(IPage page, ObjVo objVo) {
        objVo.setTotal(page.getTotal());
        objVo.setDatalist(page.getRecords());
        objVo.setPages(page.getPages());

        return objVo;
    }

}
