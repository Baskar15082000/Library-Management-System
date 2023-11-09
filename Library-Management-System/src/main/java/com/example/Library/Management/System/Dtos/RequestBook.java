package com.example.Library.Management.System.Dtos;

import com.example.Library.Management.System.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBook {
    private String BookName;
    private String Prize;
    private int Rating;
    private boolean IsAvailable;
    private Genre genre;

}
