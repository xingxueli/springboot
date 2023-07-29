package com.company.project.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
public class RoutesMeta implements Serializable {


    private String title;

    private String icon;

    //badge: 'New'
    private String badge;

    private List<String> permissions = Arrays.asList("admin","editor");
}
