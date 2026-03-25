package com.videostreaming.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class PagedResponse<T> {

    private T content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

}
