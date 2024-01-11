package edu.mit.annotation.realdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    private String email;
    private String name;
    private String password;
    private String google_app_password;
}
