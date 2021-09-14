package com.zhuyang.test.k8sdeployspringboot;

import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/4/5
 */
@Configuration
public class JDBCTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCreateUnit(){
        List<Unit> units = new ArrayList<>();
        Unit unit1 = new Unit(1990759655713808384L,"份");
        Unit unit2 = new Unit(1990759655713808284L,"克");
        Unit unit= new Unit(1990759655713208384L,"袋");
        Unit unit3 = new Unit(1990759655723808384L,"碗");
        Unit sad = new Unit(1990759655712808384L,"桶");
        Unit f = new Unit(1990759652713808384L,"盘");
        Unit w = new Unit(1990759255713808384L,"打");
        Unit g = new Unit(1990759155713808384L,"听");
        Unit s = new Unit(1990759455713808384L,"瓶");
        Unit fg = new Unit(1990759255713808384L,"个");
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        units.add(unit);
        units.add(sad);
        units.add(f);units.add(w);
        units.add(g);
        units.add(s);
        units.add(fg);
        units.forEach(t->{
            StringBuffer sql = new StringBuffer("insert into unit values ()")
                    .append(t.getId()).append(",'").append(t.getName()).append("')");
            jdbcTemplate.execute(sql.toString());
        });


    }

    @Data
    @AllArgsConstructor
    public class Unit{
        private Long id;
        private String name;
    }
}
