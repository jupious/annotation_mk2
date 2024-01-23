package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoBusinessNum {
    private String business_number;
    private String company_name;
}
