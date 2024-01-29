package edu.mit.annotation.realdto;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementPreviewDTO {
    private CompanyInfoDTO companyInfo;
    private List<StatementItemDTO> itemList;
}
