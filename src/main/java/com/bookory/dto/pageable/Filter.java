package com.bookory.dto.pageable;

import lombok.Data;

import java.util.List;


@Data
public class Filter {
    private String field;
//    private QueryOperator operator;
    private String value;
    private List<String> values;
}
