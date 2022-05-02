package com.example.itddd.web.http.models.circles.getlist;

import com.example.itddd.web.http.models.circles.common.CircleResponseModel;

import java.util.List;

public record CircleGetListResponseModel(List<CircleResponseModel> circles) {
}
