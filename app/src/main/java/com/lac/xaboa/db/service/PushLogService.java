// +----------------------------------------------------------------------
// | FileName:   PushLogService.java  
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
import com.lac.xaboa.db.model.PushLog;
import com.lac.xaboa.db.dao.PushLogDao;
import com.lac.xaboa.db.dao.PushLogDao.Properties;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

public class PushLogService{

	private static Context mContext;
	private static PushLogService instance;
	
	private PushLogDao dao;
	
	private PushLogService(){}
	
	public static PushLogService getInstance(Context context) {
		if (instance == null) {
			instance = new PushLogService();
			if (mContext == null) {
				mContext = context;
			}
			// 数据库对象
			DaoSession daoSession = DBUtils.getDaoSession(mContext);
			instance.dao = daoSession.getPushLogDao();
		}
		return instance;
	}
	
	/** 添加数据 */
	public void add(PushLog pushLog) {
		dao.insert(pushLog);
	}
	
	/** 添加数据数组 */
	public void addList(final List<PushLog> list) {
		if(list == null || list.isEmpty()){  
            return;  
        }  

        for(int i=0; i<list.size(); i++){  
            PushLog pushLog = list.get(i);  
            dao.insertOrReplace(pushLog);  
        }  

	}
	
	/** 删除 根据ID */
	public void _delById(int id) {
		QueryBuilder<PushLog> qb = dao.queryBuilder();
		DeleteQuery<PushLog> bd = qb.where(Properties._id.eq(id)).buildDelete();
		bd.executeDeleteWithoutDetachingEntities();
	}

	/** 删除所有 */
	public void delAll() {
		dao.deleteAll();
	}
	
	/** 修改数据  */
	public void update(PushLog pushLog){
		dao.insertOrReplace(pushLog);
	}
	
	/** 查询所有  */
	public List<PushLog> getAll() {
		QueryBuilder<PushLog> qb = dao.queryBuilder();
		return qb.list();
	}
	
	/** 数据是否存在 */
	public boolean isExist(int id)
	{
	    QueryBuilder<PushLog> qb = dao.queryBuilder();
	    qb.where(Properties._id.eq(id));
	    qb.buildCount().count();
	    return qb.buildCount().count() > 0 ;
	}
	
	/** 数据是否存在 */
	public boolean isExistById(String id)
	{
		QueryBuilder<PushLog> qb = dao.queryBuilder();
		qb.where(Properties.Id.eq(id));
		qb.buildCount().count();
		return qb.buildCount().count() > 0 ;
	}
	
	/** 单个数据*/
	public PushLog displayById(String id)
	{
	    QueryBuilder<PushLog> qb = dao.queryBuilder();
	    qb.where(Properties.Id.eq(id));
	    return qb.unique();
	}
	
	/** 删除单挑数据根据ID */
	public void delById(String id) {
		QueryBuilder<PushLog> qb = dao.queryBuilder();
		DeleteQuery<PushLog> bd = qb.where(Properties.Id.eq(id)).buildDelete();
		bd.executeDeleteWithoutDetachingEntities();
	}
	
	/** 服务器同步数据单条 */
    public void syncModel(final PushLog pushLog){
	    delById(pushLog.getId());
	    add(pushLog);
    }
    
	/** 服务器同步数据多条 */
    public void syncList(List<PushLog> list){
    	if(list == null || list.isEmpty()){  
            return;  
        }  
    	int size = list.size();
        for(int i=0; i<size; i++){  
            PushLog pushLog = list.get(i);  
            syncModel(pushLog);  
        }  
    }
    
	// +----------------------------------------------------------------------
	// | 以后的方法属于手动添加，如果需要重新生成，请先备份
	// +----------------------------------------------------------------------
}