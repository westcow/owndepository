package com.bjb.project.dao;

import com.bjb.project.entity.Database;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseDao {
    /**
     * 新增
     * @param database
     */
    void insertDatabase(Database database);

    /**
     * 更新
     * @param database
     */
    void updateDatabase(Database database);

    /**
     * 删除
     * @param sort
     */
    void deleteDatabase(Integer sort);

    /**
     * 根据sort查询单条信息
     * @param sort
     * @return
     */
    Database queryOneBySort(Integer sort);

    /**
     * 查询数据库连接地址列表
     * @param database
     * @return
     */
    List<Database> queryAll();

}
