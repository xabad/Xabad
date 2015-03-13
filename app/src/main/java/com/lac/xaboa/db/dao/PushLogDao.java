package com.lac.xaboa.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.lac.xaboa.db.model.PushLog;
import com.lac.xaboa.db.DaoSession;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PUSH_LOG.
*/
public class PushLogDao extends AbstractDao<PushLog, Long> {

    public static final String TABLENAME = "PUSH_LOG";

    /**
     * Properties of entity PushLog.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_ID");
        public final static Property Id = new Property(1, String.class, "id", false, "ID");
        public final static Property Pushtype = new Property(2, String.class, "pushtype", false, "PUSHTYPE");
        public final static Property Operate = new Property(3, String.class, "operate", false, "OPERATE");
        public final static Property Tablename = new Property(4, String.class, "tablename", false, "TABLENAME");
        public final static Property Userid = new Property(5, String.class, "userid", false, "USERID");
        public final static Property Dataid = new Property(6, String.class, "dataid", false, "DATAID");
        public final static Property Status = new Property(7, String.class, "status", false, "STATUS");
    };


    public PushLogDao(DaoConfig config) {
        super(config);
    }
    
    public PushLogDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PUSH_LOG' (" + //
                "'_ID' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "'ID' TEXT," + // 1: id
                "'PUSHTYPE' TEXT," + // 2: pushtype
                "'OPERATE' TEXT," + // 3: operate
                "'TABLENAME' TEXT," + // 4: tablename
                "'USERID' TEXT," + // 5: userid
                "'DATAID' TEXT," + // 6: dataid
                "'STATUS' TEXT);"); // 7: status
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PUSH_LOG'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, PushLog entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String pushtype = entity.getPushtype();
        if (pushtype != null) {
            stmt.bindString(3, pushtype);
        }
 
        String operate = entity.getOperate();
        if (operate != null) {
            stmt.bindString(4, operate);
        }
 
        String tablename = entity.getTablename();
        if (tablename != null) {
            stmt.bindString(5, tablename);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(6, userid);
        }
 
        String dataid = entity.getDataid();
        if (dataid != null) {
            stmt.bindString(7, dataid);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(8, status);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public PushLog readEntity(Cursor cursor, int offset) {
        PushLog entity = new PushLog( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // pushtype
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // operate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // tablename
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userid
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // dataid
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // status
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, PushLog entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPushtype(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOperate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTablename(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserid(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDataid(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setStatus(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(PushLog entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(PushLog entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
