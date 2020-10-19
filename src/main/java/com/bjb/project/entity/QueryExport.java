package com.bjb.project.entity;

import lombok.Data;

@Data
public class QueryExport {
    private Integer sort;
    private String title;
    private String sqlMessage;
    private String dbName;
    private String templateUrl;
}
