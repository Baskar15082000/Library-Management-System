package com.example.Library.Management.System.Dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthorDto {

    private String name;
    private int rating;
    private int age;

}
