package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class formDTO {

    private String business_number;
    private String note;
    private List<String> item_code;
    private List<String> item_name;
    private List<String> item_price;
    private List<String> lead_time;
    private List<String> details;
    private MultipartFile contractFile;
    private String contract_origin_name;

}
