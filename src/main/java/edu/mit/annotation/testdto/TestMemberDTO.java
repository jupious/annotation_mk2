package edu.mit.annotation.testdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestMemberDTO {
    private String email;
    private String name;
    private String password;
    private String google_app_password;
}
