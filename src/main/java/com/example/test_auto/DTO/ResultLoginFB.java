package com.example.test_auto.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultLoginFB {
    int countSuccess = 0;
    int countFailed = 0;

}
