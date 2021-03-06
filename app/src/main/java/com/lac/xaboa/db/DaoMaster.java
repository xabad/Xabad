package com.lac.xaboa.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import com.lac.xaboa.db.dao.MemberDao;
import com.lac.xaboa.db.dao.NoticeDao;
import com.lac.xaboa.db.dao.FlowDao;
import com.lac.xaboa.db.dao.FlowLogDao;
import com.lac.xaboa.db.dao.PushLogDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        MemberDao.createTable(db, ifNotExists);
        
        NoticeDao.createTable(db, ifNotExists);
        
        FlowDao.createTable(db, ifNotExists);
        
        FlowLogDao.createTable(db, ifNotExists);
        
        PushLogDao.createTable(db, ifNotExists);
        
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        MemberDao.dropTable(db, ifExists);
        NoticeDao.dropTable(db, ifExists);
        FlowDao.dropTable(db, ifExists);
        FlowLogDao.dropTable(db, ifExists);
        PushLogDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        
        registerDaoClass(MemberDao.class);
        registerDaoClass(NoticeDao.class);
        registerDaoClass(FlowDao.class);
        registerDaoClass(FlowLogDao.class);
        registerDaoClass(PushLogDao.class);
		
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
