package com.bjb.project.controller;

import com.bjb.project.entity.QueryExport;
import com.bjb.project.service.QueryExportService;
import com.bjb.project.util.DBHelper;
import com.bjb.project.util.JsonResultData;
import com.bjb.project.util.ReturnJsonObject;
import com.github.pagehelper.Page;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/queryExport")
public class QueryExportController {

    @Autowired
    private QueryExportService queryExportService;

    @RequestMapping("/addExport")
    public ReturnJsonObject addExport(QueryExport queryExport) {
        queryExportService.addQueryExport(queryExport);
        return new ReturnJsonObject(JsonResultData.ADD_SUCCESS, null);
    }

    @RequestMapping("/updateExport")
    public ReturnJsonObject updateExport(QueryExport queryExport) {
        queryExportService.updateQueryExport(queryExport);
        return new ReturnJsonObject(JsonResultData.UPDATE_SUCCESS, null);
    }

    @RequestMapping("/deleteExport")
    public ReturnJsonObject deleteExport(Integer sort) {
        queryExportService.delete(sort);
        return new ReturnJsonObject(JsonResultData.DELETE_SUCCESS, null);
    }

    @RequestMapping("/selectOne")
    public ReturnJsonObject selectOne(Integer sort, QueryExport queryExport) {
        queryExport = queryExportService.findOne(sort);
        return new ReturnJsonObject(JsonResultData.SELECT_SUCCESS, queryExport);
    }

    @RequestMapping("/selectAllByPage")
    public ReturnJsonObject selectAllByPage(QueryExport queryExport, Integer curPage, Integer pageSize) throws SQLException {
        curPage = curPage==null?1:curPage;
		pageSize = pageSize==null?10:pageSize;
		Page<QueryExport> page = queryExportService.getListByPage(queryExport, curPage, pageSize);
		return new ReturnJsonObject(JsonResultData.GET_SUCCESS, page);
    }

    @RequestMapping("/export")
    public ReturnJsonObject exportExcel(String IP, String port, String dbName, String userName, String password, String excelPath, String sqlMessage) throws SQLException, ClassNotFoundException, IOException {
//        DBHelper dbHelper = new DBHelper("jdbc:mysql://192.168.1.31:3306/db_cultural_service_guizhou?useUnicode=true&characterEncoding=utf-8", "com.mysql.cj.jdbc.Driver", "root", "123456");
        DBHelper dbHelper = new DBHelper("jdbc:mysql://" + IP + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8", "com.mysql.cj.jdbc.Driver", userName, password);
//        String sql = "select id,projectName FROM t_volunteer_competition where delFlg = 0";
//        ResultSet res = dbHelper.query(sql, null);
        ResultSet res = dbHelper.query(sqlMessage, null);
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excelPath));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int orderNum = 1;
        int rowNum = 1;
        int sqlColumn = res.getMetaData().getColumnCount();
        System.out.println(res.getMetaData().getColumnCount());
        while (res.next()) {
            row = sheet.createRow(rowNum);
            for (int i = 0; i < sqlColumn; i++) {
                cell = row.createCell(i);
                cell.setCellValue(res.getString(i+1));
            }
            rowNum++;
        }
        Date time = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String now = sf.format(time);
        String fileName = now + ".xls";
        String filePath = "F:/" + fileName;
        FileOutputStream output = new FileOutputStream(filePath);
        wb.write(output);
        output.close();
        dbHelper.close();
        return new ReturnJsonObject(JsonResultData.EXPORT_SUCCESS, filePath);
    }

}
