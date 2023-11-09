package com.example.Library.Management.System.Dtos;

import com.example.Library.Management.System.Enum.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private String BookName;
    private String Prize;
    private int Rating;
    private boolean IsAvailable;
    private Genre genre;
    private String AuthorName;
}
