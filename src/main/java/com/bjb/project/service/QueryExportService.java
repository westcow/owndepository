package com.bjb.project.service;

import com.bjb.project.dao.QueryExportDao;
import com.bjb.project.entity.QueryExport;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

@Service
public class QueryExportService {
    //读取配置文件
    @Value("${fileUrl}")
    private String FILE_URL;
    @Value("${dirPath}")
    private String DIR_PATH;
    @Value("${EXCEL_TEMPLATE_PATH}")
    private String EXCEL_TEMPLATE_PATH;

    @Resource
    private QueryExportDao queryExportDao;

    public void addQueryExport(QueryExport queryExport) {
        queryExportDao.insertQueryExport(queryExport);
    }

    public void updateQueryExport(QueryExport queryExport) {
        queryExportDao.updateQueryExport(queryExport);
    }

    public void delete(Integer sort) {
        queryExportDao.deleteQueryExport(sort);
    }

    public QueryExport findOne(Integer sort) {
        return queryExportDao.findOne(sort);
    }

    public List<QueryExport> findAll(QueryExport queryExport) {
        List<QueryExport> list = queryExportDao.findAll(queryExport);
        return list;
    }

    public Page<QueryExport> getListByPage(QueryExport queryExport, Integer curPage, Integer pageSize) throws SQLException {
        PageHelper.startPage(curPage, pageSize);
        List<QueryExport> list = queryExportDao.findAll(queryExport);
        Page<QueryExport> page = (Page) list;
        return page;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
//        String fileUrl = FILE_URL + DIR_PATH + fileName;
        String fileUploadPath = EXCEL_TEMPLATE_PATH;
        File localFile = new File(fileUploadPath,fileName);
        File localDir = localFile.getParentFile();
        if (!localDir.exists()) {
            localDir.mkdirs();
        }
        file.transferTo(localFile);
        return fileUploadPath + fileName;
    }

}
