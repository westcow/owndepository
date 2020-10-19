package com.bjb.project.controller;

import com.bjb.project.entity.Database;
import com.bjb.project.service.DatabaseService;
import com.bjb.project.util.JsonResultData;
import com.bjb.project.util.ReturnJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/database")
public class DatabaseController {
    @Autowired
    private DatabaseService databaseService;

    @RequestMapping("/insertDatabase")
    public ReturnJsonObject insertDatabase(Database database) {
        databaseService.addDatabase(database);
        return new ReturnJsonObject(JsonResultData.ADD_SUCCESS, null);
    }

    @RequestMapping("/updateDatabase")
    public ReturnJsonObject updateDatabase(Database database) {
        databaseService.updateDatabase(database);
        return new ReturnJsonObject(JsonResultData.UPDATE_SUCCESS, null);
    }

    @RequestMapping("/deleteDatabase")
    public ReturnJsonObject deleteDatabase(Integer sort) {
        databaseService.deleteDatabase(sort);
        return new ReturnJsonObject(JsonResultData.DELETE_SUCCESS, null);
    }

    @RequestMapping("/findOne")
    public ReturnJsonObject findOne(Integer sort,Database database) {
        database = databaseService.queryOne(sort);
        return new ReturnJsonObject(JsonResultData.SELECT_SUCCESS, database);
    }

    @RequestMapping("/findList")
    public ReturnJsonObject findList() {
        List<Database> list = databaseService.queryAll();
        return new ReturnJsonObject(JsonResultData.GET_SUCCESS, list);
    }


}
