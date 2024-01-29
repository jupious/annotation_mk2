package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishedPODTO {
    private String purch_order_number;
    private Date purch_order_date;
    private String purch_order_detail;
}
