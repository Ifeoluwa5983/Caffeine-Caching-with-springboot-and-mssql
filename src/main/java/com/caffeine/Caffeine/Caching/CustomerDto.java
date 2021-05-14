package com.caffeine.Caffeine.Caching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long customer_id;

    private String Name;

    private String ShortName;

    private String DateOfBirth;

    private String Sex;

    private String PhoneRef;

    private String EMailAddress;

}
