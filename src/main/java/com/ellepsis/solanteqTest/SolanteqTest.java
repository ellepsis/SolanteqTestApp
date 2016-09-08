package com.ellepsis.solanteqTest;

import com.ellepsis.solanteqTest.defaultData.DefaultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@SpringBootApplication
public class SolanteqTest  implements CommandLineRunner{

    private DefaultData defaultData;

    public static void main(String[] args){
        SpringApplication.run(SolanteqTest.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        defaultData.insertData();
    }

    @Autowired
    public void setDefaultData(DefaultData defaultData) {
        this.defaultData = defaultData;
    }
}
