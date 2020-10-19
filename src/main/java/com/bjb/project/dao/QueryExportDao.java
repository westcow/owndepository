package com.bjb.project.dao;

import com.bjb.project.entity.QueryExport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryExportDao {
    /**
     * 新增
     * @param queryExport
     */
    void insertQueryExport(QueryExport queryExport);

    /**
     * 更新
     * @param queryExport
     */
    void updateQueryExport(QueryExport queryExport);

    /**
     * 删除
     * @param sort
     */
    void deleteQueryExport(Integer sort);

    /**
     * 根据sort查询单条信息
     * @param sort
     * @return
     */
    QueryExport findOne(Integer sort);

    /**
     * 查询queryExport列表
     * @param queryExport
     * @return
     */
    List<QueryExport> findAll(QueryExport queryExport);

}
