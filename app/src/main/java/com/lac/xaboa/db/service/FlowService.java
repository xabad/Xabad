// +----------------------------------------------------------------------
// | FileName:   FlowService.java  
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
import com.lac.xaboa.db.dao.NoticeDao;
import com.lac.xaboa.db.model.Flow;
import com.lac.xaboa.db.dao.FlowDao;
import com.lac.xaboa.db.dao.FlowDao.Properties;
import com.lac.xaboa.db.model.Notice;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

public class FlowService{

	private static Context mContext;
	private static FlowService instance;
	
	private FlowDao dao;
	
	private FlowService(){}
	
	public static FlowService getInstance(Context context) {
		if (instance == null) {
			instance = new FlowService();
			if (mContext == null) {
				mContext = context;
			}
			// 数据库对象
			DaoSession daoSession = DBUtils.getDaoSession(mContext);
			instance.dao = daoSession.getFlowDao();
		}
		return instance;
	}
	
	/** 添加数据 */
	public void add(Flow flow) {
		dao.insert(flow);
	}
	
	/** 添加数据数组 */
	public void addList(final List<Flow> list) {
		if(list == null || list.isEmpty()){  
            return;  
        }  
        for(int i=0; i<list.size(); i++){
            Flow flow = list.get(i);
            dao.insertOrReplace(flow);
        }
	}
	
	/** 删除 根据ID */
	public void _delById(int id) {
		QueryBuilder<Flow> qb = dao.queryBuilder();
		DeleteQuery<Flow> bd = qb.where(Properties._id.eq(id)).buildDelete();
		bd.executeDeleteWithoutDetachingEntities();
	}

	/** 删除所有 */
	public void delAll() {
		dao.deleteAll();
	}
	
	/** 修改数据  */
	public void update(Flow flow){
		dao.insertOrReplace(flow);
	}
	
	/** 查询所有  */
	public List<Flow> getAll() {
		QueryBuilder<Flow> qb = dao.queryBuilder();
		return qb.list();
	}
	
	/** 数据是否存在 */
	public boolean isExist(int id)
	{
	    QueryBuilder<Flow> qb = dao.queryBuilder();
	    qb.where(Properties._id.eq(id));
	    qb.buildCount().count();
	    return qb.buildCount().count() > 0 ;
	}
	
	/** 数据是否存在 */
	public boolean isExistById(String id)
	{
		QueryBuilder<Flow> qb = dao.queryBuilder();
		qb.where(Properties.Id.eq(id));
		qb.buildCount().count();
		return qb.buildCount().count() > 0 ;
	}
	
	/** 单个数据*/
	public Flow displayById(String id)
	{
	    QueryBuilder<Flow> qb = dao.queryBuilder();
	    qb.where(Properties.Id.eq(id));
	    return qb.unique();
	}
	
	/** 删除单挑数据根据ID */
	public void delById(String id) {
		QueryBuilder<Flow> qb = dao.queryBuilder();
		DeleteQuery<Flow> bd = qb.where(Properties.Id.eq(id)).buildDelete();
		bd.executeDeleteWithoutDetachingEntities();
	}
	
	/** 服务器同步数据单条 */
    public void syncModel(final Flow flow){
        delById(flow.getId());
        add(flow);
    }
    
	/** 服务器同步数据多条 */
    public void syncList(List<Flow> list){
    	if(list == null || list.isEmpty()){  
            return;  
        }  
    	int size = list.size();
        for(int i=0; i<size; i++){  
            Flow flow = list.get(i);  
            syncModel(flow);  
        }  
    }
    
	// +----------------------------------------------------------------------
	// | 以后的方法属于手动添加，如果需要重新生成，请先备份
	// +----------------------------------------------------------------------

    /** 服务器同步数据单条 */
    public String lastId(){
        Flow flow ;
        QueryBuilder<Flow> qb = dao.queryBuilder();
        qb.orderDesc(NoticeDao.Properties.Id);
        qb.limit(1);
        flow= qb.unique();
        return flow ==null ? "":flow.getId();
    }
}