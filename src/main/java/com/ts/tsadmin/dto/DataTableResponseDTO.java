package com.ts.tsadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataTableResponseDTO<T> {
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;
    private List<T> data;
}
