// +----------------------------------------------------------------------
// | FileName:   NoticeService.java  
// +----------------------------------------------------------------------
// | Copyright:  http://www.xueyong.net.cn
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn) 
// | 			 Auto Create Java File 
// +----------------------------------------------------------------------
package com.lac.xaboa.db.service;

import android.content.Context;
import java.util.List;

import com.lac.xaboa.db.DBUtils;
import com.lac.xaboa.db.DaoSession;
import com.lac.xaboa.db.model.Notice;
import com.lac.xaboa.db.dao.NoticeDao;
import com.lac.xaboa.db.dao.NoticeDao.Properties;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

public class NoticeService{

	private static Context mContext;
	private static NoticeService instance;
	
	private NoticeDao dao;
	
	private NoticeService(){}
	
	public static NoticeService getInstance(Context context) {
		if (instance == null) {
			instance = new NoticeService();
			if (mContext == null) {
				mContext = context;
			}
			// 数据库对象
			DaoSession daoSession = DBUtils.getDaoSession(mContext);
			instance.dao = daoSession.getNoticeDao();
		}
		return instance;
	}
	
	/** 添加数据 */
	public void add(Notice notice) {
		dao.insert(notice);
	}
	
	/** 添加数据数组 */
	public void addList(final List<Notice> list) {
		if(list == null || list.isEmpty()){  
            return;  
        }  
		dao.getSession().runInTx(new Runnable() {  
            @Override  
            public void run() {  
                for(int i=0; i<list.size(); i++){  
                    Notice notice = list.get(i);  
                    dao.insertOrReplace(notice);  
                }  
            }  
        }); 
	}
	
	/** 删除 根据ID */
	public void _delById(int id) {
		QueryBuilder<Notice> qb = dao.queryBuilder();
		DeleteQuery<Notice> bd = qb.where(Properties._id.eq(id)).buildDelete();
		bd.executeDeleteWithoutDetachingEntities();
	}

	/** 删除所有 */
	public void delAll() {
		dao.deleteAll();
	}
	
	/** 修改数据  */
	public void update(Notice notice){
		dao.insertOrReplace(notice);
	}
	
	/** 查询所有  */
	public List<Notice> getAll() {
		QueryBuilder<Notice> qb = dao.queryBuilder();
		return qb.list();
	}
	
	/** 数据是否存在 */
	public boolean isExist(int id)
	{
	    QueryBuilder<Notice> qb = dao.queryBuilder();
	    qb.where(Properties._id.eq(id));
	    qb.buildCount().count();
	    return qb.buildCount().count() > 0 ? true : false;
	}
	
	/** 数据是否存在 */
	public boolean isExistById(String id)
	{
		QueryBuilder<Notice> qb = dao.queryBuilder();
		qb.where(Properties.Id.eq(id));
		qb.buildCount().count();
		return qb.buildCount().count() > 0 ? true : false;
	}
	
	/** 单个数据*/
	public Notice displayById(String id)
	{
	    QueryBuilder<Notice> qb = dao.queryBuilder();
	    qb.where(Properties.Id.eq(id));
	    return qb.uniqueOrThrow();
	}
	
	/** 删除单挑数据根据ID */
	public void delById(String id) {
		QueryBuilder<Notice> qb = dao.queryBuilder();
		DeleteQuery<Notice> bd = qb.where(Properties.Id.eq(id)).buildDelete();
		bd.executeDeleteWithoutDetachingEntities();
	}

    /** 服务器同步数据单条 */
    public String lastId(){
        Notice notice ;
        QueryBuilder<Notice> qb = dao.queryBuilder();
        qb.orderDesc(Properties.Id);
        qb.limit(1);
        notice= qb.unique();
        return notice ==null ? "":notice.getId();
    }
	
	/** 服务器同步数据单条 */
    public void syncModel(Notice notice){
        delById(notice.getId());
        add(notice);
    }
    
	/** 服务器同步数据多条 */
    public void syncList(List<Notice> list){
    	if(list == null || list.isEmpty()){  
            return;  
        }  
    	int size = list.size();
        for(int i=0; i<size; i++){  
            Notice notice = list.get(i);  
            syncModel(notice);  
        }  
    }
    
	// +----------------------------------------------------------------------
	// | 以后的方法属于手动添加，如果需要重新生成，请先备份
	// +----------------------------------------------------------------------

    /** 查询所有  */
    public List<Notice> getList(int limit,int offset) {
        QueryBuilder<Notice> qb = dao.queryBuilder();
        qb.orderDesc(Properties.Create_time);
        if(limit > 0){
            qb.limit(limit);
        }
        if(offset > 0){
            qb.offset(offset);
        }
        return qb.list();
    }

    /** 查询所有  */
    public List<Notice> getList(int limit) {
        return getList(limit,0);
    }
}