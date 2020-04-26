package com.zd1024.withyou.Util;

import com.zd1024.withyou.entity.Dynamic;
import com.zd1024.withyou.entityVo.AndroidData;

import java.util.List;

public class DataDealUtil {

    public static AndroidData dealdata(List objlist){
        AndroidData redata = new AndroidData();
        if (objlist.size()!=0){
            redata.setSuccess(true);
            redata.setDatalist(objlist);
            redata.setMsg("共获取到 "+objlist.size()+" 条数据");
        }else {
            redata.setSuccess(false);
            redata.setMsg("未获取到数据");
        }
        return redata;
    }

    public static AndroidData dealdata(int rs,String msg){
        AndroidData redata = new AndroidData();
        if (rs==1){
            redata.setSuccess(true);
            redata.setMsg(msg+"成功");
        }else {
            redata.setSuccess(false);
            redata.setMsg(msg+"失败");
        }
        return redata;
    }

    public static AndroidData dealbeanData(Object entity,String msg){
        AndroidData<Object> redata = new AndroidData();
        if (entity!=null){
            redata.setSuccess(true);
            redata.setMsg(msg+"成功");
            redata.setData(entity);
        }else {
            redata.setSuccess(false);
            redata.setMsg(msg+"失败");
        }

        return redata;
    }

}
