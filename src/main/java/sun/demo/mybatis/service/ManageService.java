package sun.demo.mybatis.service;

import com.google.common.base.Strings;
import sun.demo.mybatis.dao.MsgDao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageService {
    public void deleteOne(String id){
        if(!Strings.isNullOrEmpty(id)) {
            new MsgDao().deleteOne(Integer.valueOf(id));
        }
    }

    public void deleteOBatch(String[] ids){
        if(ids!=null&&ids.length>0) {
            List<Integer> idlist = new ArrayList<Integer>();
            for(String id:ids){
                idlist.add(Integer.valueOf(id));
            }
            new MsgDao().deleteBatch(idlist);
        }
    }
}
