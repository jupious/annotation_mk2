package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementPreviewDTO {
    private Date stmtDate;
    private String purch_order_detail;
    private String business_number;
    private String company_name;
    private String company_address;
    private String manager;
    private String manager_tel;
    private List<StatementItemsDTO> itemList;
}
