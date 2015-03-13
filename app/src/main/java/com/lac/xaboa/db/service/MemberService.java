// +----------------------------------------------------------------------
// | FileName:   MemberService.java  
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
import com.lac.xaboa.db.model.Member;
import com.lac.xaboa.db.dao.MemberDao;
import com.lac.xaboa.db.dao.MemberDao.Properties;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

public class MemberService{

	private static Context mContext;
	private static MemberService instance;
	
	private MemberDao dao;
	
	private MemberService(){}
	
	public static MemberService getInstance(Context context) {
		if (instance == null) {
			instance = new MemberService();
			if (mContext == null) {
				mContext = context;
			}
			// 数据库对象
			DaoSession daoSession = DBUtils.getDaoSession(mContext);
			instance.dao = daoSession.getMemberDao();
		}
		return instance;
	}
	
	/** 添加数据 */
	public void add(Member member) {
		dao.insert(member);
	}
	
	/** 添加数据数组 */
	public void addList(final List<Member> list) {
		if(list == null || list.isEmpty()){  
            return;  
        }  
		dao.getSession().runInTx(new Runnable() {  
            @Override  
            public void run() {  
                for(int i=0; i<list.size(); i++){  
                    Member member = list.get(i);  
                    dao.insertOrReplace(member);  
                }  
            }  
        }); 
	}
	
	/** 删除 根据ID */
	public void _delById(int id) {
		QueryBuilder<Member> qb = dao.queryBuilder();
		DeleteQuery<Member> bd = qb.where(Properties._id.eq(id)).buildDelete();
		bd.executeDeleteWithoutDetachingEntities();
	}

	/** 删除所有 */
	public void delAll() {
		dao.deleteAll();
	}
	
	/** 修改数据  */
	public void update(Member member){
		dao.insertOrReplace(member);
	}
	
	/** 查询所有  */
	public List<Member> getAll() {
		QueryBuilder<Member> qb = dao.queryBuilder();
		return qb.list();
	}
	
	/** 数据是否存在 */
	public boolean isExist(int id)
	{
	    QueryBuilder<Member> qb = dao.queryBuilder();
	    qb.where(Properties._id.eq(id));
	    qb.buildCount().count();
	    return qb.buildCount().count() > 0 ;
	}
	
	/** 数据是否存在 */
	public boolean isExistById(String id)
	{
		QueryBuilder<Member> qb = dao.queryBuilder();
		qb.where(Properties.Id.eq(id));
		qb.buildCount().count();
		return qb.buildCount().count() > 0 ;
	}
	
	/** 单个数据*/
	public Member displayById(String id)
	{
	    QueryBuilder<Member> qb = dao.queryBuilder();
	    qb.where(Properties.Id.eq(id));
	    return qb.unique();
	}
	
	/** 删除单挑数据根据ID */
	public void delById(String id) {
		QueryBuilder<Member> qb = dao.queryBuilder();
		DeleteQuery<Member> bd = qb.where(Properties.Id.eq(id)).buildDelete();
		bd.executeDeleteWithoutDetachingEntities();
	}
	
	/** 服务器同步数据单条 */
    public void syncModel(final Member member){
        delById(member.getId());
        add(member);
    }
    
	/** 服务器同步数据多条 */
    public void syncList(List<Member> list){
    	if(list == null || list.isEmpty()){  
            return;  
        }  
    	int size = list.size();
        for(int i=0; i<size; i++){  
            Member member = list.get(i);  
            syncModel(member);  
        }  
    }
    
	// +----------------------------------------------------------------------
	// | 以后的方法属于手动添加，如果需要重新生成，请先备份
	// +----------------------------------------------------------------------
}