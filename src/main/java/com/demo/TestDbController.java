package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class TestDbController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db")
    public String testDb() {
        try (Connection connection = dataSource.getConnection()) {
            return "Kết nối DB thành công: " + connection.getMetaData().getURL();
        } catch (Exception e) {
            e.printStackTrace();
            return "KẾT NỐI DB THẤT BẠI: " + e.getMessage();
        }
    }
}
