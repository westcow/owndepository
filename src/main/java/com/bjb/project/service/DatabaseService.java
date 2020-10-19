package com.bjb.project.service;

import com.bjb.project.dao.DatabaseDao;
import com.bjb.project.entity.Database;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DatabaseService {
    @Resource
    private DatabaseDao databaseDao;

    public void addDatabase(Database database) {
        databaseDao.insertDatabase(database);
    }

    public void updateDatabase(Database database) {
        databaseDao.updateDatabase(database);
    }

    public void deleteDatabase(Integer sort) {
        databaseDao.deleteDatabase(sort);
    }

    public Database queryOne(Integer sort) {
        return databaseDao.queryOneBySort(sort);
    }

    public List<Database> queryAll() {
        List<Database> databaseList = databaseDao.queryAll();
        return databaseList;
    }



}
