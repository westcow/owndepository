package com.bjb.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryExport {
    private Integer sort;
    private String title;
    private String sqlMessage;
    private String dbName;
    private String templateUrl;
}
