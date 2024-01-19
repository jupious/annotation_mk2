package edu.mit.annotation.realdto;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListWithPaging<T> {
    private Integer page_count;
    private Integer currentPage;
    private List<String> pageList;
    private List<T> dataList;
    private String msg;
}
