package edu.mit.annotation.realdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchOrderItemWithCompanyName {
    private String item_name;
    private String company_name;
}
