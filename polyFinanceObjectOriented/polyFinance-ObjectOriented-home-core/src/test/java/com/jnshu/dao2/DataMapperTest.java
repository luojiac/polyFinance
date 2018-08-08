package com.jnshu.dao2;

import com.jnshu.Entry;
import com.jnshu.entity.SystemData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Entry.class)
public class DataMapperTest {

    @Resource
    DataMapper2 mapper;

    @Test
    public void t() throws Exception{
        SystemData n = new SystemData();
        n.setDataName("creditorLine");
//        System.out.println(mapper.getSystemData(n));
        n.setDataValue("53453");
        System.out.println(mapper.updateSystemData(n));
    }

    @Test
    public void t1() throws Exception{
//        String dataName = "officialSeal";
//        System.out.println(mapper.update(dataName));
        System.out.println(mapper.updataDataFromBackup());
    }
}
