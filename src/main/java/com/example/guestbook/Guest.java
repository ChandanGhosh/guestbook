package com.example.guestbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Table(value = "guests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest implements Serializable {
    @PrimaryKey
    private Integer id;
    private String name;
    private String address;
    private Integer age;
}
