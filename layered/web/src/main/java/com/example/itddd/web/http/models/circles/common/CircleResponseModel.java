package com.example.itddd.web.http.models.circles.common;

import java.util.List;

public record CircleResponseModel(String id, String name, String ownerId, List<String> memberIdList) {
}
