package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDTO {
    private String item_code;
    private Date startDate;
    private Date endDate;

}
