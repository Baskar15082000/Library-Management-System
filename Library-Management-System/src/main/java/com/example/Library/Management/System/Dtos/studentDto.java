package com.example.Library.Management.System.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class studentDto {
    private String StudentName;
    private String StudentEmail;
    private String  StudentMobile;
    private Integer age;
}
