package com.zhuyang.test.k8sdeployspringboot;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/3/15
 */
@RestController
@Configuration
public class Index {


    @GetMapping(path = "/info")
    public String info() throws InterruptedException {
        System.out.println("enter...s");
//        testCreateUnit();
        testCreateSku();
        ;
        return "ok";
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void testCreateUnit() {
        List<Unit> units = new ArrayList<>();
        Unit unit1 = new Unit(1990759655713808384L, "份");
        Unit unit2 = new Unit(1990759655713808284L, "克");
        Unit unit = new Unit(1990759655713208384L, "袋");
        Unit unit3 = new Unit(1990759655723808384L, "碗");
        Unit sad = new Unit(1990759655712808384L, "桶");
        Unit f = new Unit(1990759652713808384L, "盘");
        Unit w = new Unit(1990759255713808384L, "打");
        Unit g = new Unit(1990759155713808384L, "听");
        Unit s = new Unit(1990759455713808384L, "瓶");
        Unit fg = new Unit(1991759255713808384L, "个");
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        units.add(unit);
        units.add(sad);
        units.add(f);
        units.add(w);
        units.add(g);
        units.add(s);
        units.add(fg);
        units.forEach(t -> {
            StringBuffer sql = new StringBuffer("insert into unit values (")
                    .append(t.getId()).append(",'").append(t.getName()).append("')");
            System.out.println(sql);
            jdbcTemplate.execute(sql.toString());
        });
    }


    private Long getRanLongInArr(List<Long> ids) {
        int length = ids.size();
        int index = (int) (Math.random() * length);
        return ids.get(index);
    }
    private BigDecimal getRanBigDecimalInArr(List<BigDecimal> ids) {
        int length = ids.size();
        int index = (int) (Math.random() * length);
        return ids.get(index);
    }

    public void testCreateSku() {
        List<BigDecimal> prices = createPrice();
        List<Long> unitIds = (List) jdbcTemplate.query("select id from unit", new ResultSetExtractor() {
            @Override
            public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<Long> ids = new ArrayList();
                while (resultSet.next()) {
                    Long unitId = resultSet.getLong("id");
                    ids.add(unitId);
                }
                return ids;
            }
        });

        List<Sku> skus = new ArrayList<>();
        for (int i = 100; i < 500; i++) {
            Long unitId = (Long) getRanLongInArr(unitIds);
            System.out.println(unitId);
            BigDecimal price = getRanBigDecimalInArr(prices);
            Sku sku = new Sku(1990759255713808384L + i, "商品" + i, unitId, new BigDecimal(100), price);
            skus.add(sku);
        }
        for (Sku sku : skus) {
            StringBuffer sql = new StringBuffer("insert into sku values (")
                    .append(sku.getId()).append(",'").append(sku.getName()).append("',")
                    .append(sku.getUnitId()).append(",").append(sku.getStock()).append(",").append(sku.getPrice()).append(")");
            System.out.println(sql.toString());
            jdbcTemplate.execute(sql.toString());
        }
    }

    private List<BigDecimal> createPrice() {
        List<BigDecimal> bigDecimallist = new ArrayList<>();
        bigDecimallist.add(new BigDecimal(14));
        bigDecimallist.add(new BigDecimal(10));
        bigDecimallist.add(new BigDecimal(12));
        bigDecimallist.add(new BigDecimal(25));
        bigDecimallist.add(new BigDecimal(6));
        bigDecimallist.add(new BigDecimal(26));
        bigDecimallist.add(new BigDecimal(30));
        bigDecimallist.add(new BigDecimal(23));
        bigDecimallist.add(new BigDecimal(15));
        bigDecimallist.add(new BigDecimal(15));
        bigDecimallist.add(new BigDecimal(15));
        bigDecimallist.add(new BigDecimal(24));
        bigDecimallist.add(new BigDecimal(15));
        bigDecimallist.add(new BigDecimal(15));
        bigDecimallist.add(new BigDecimal(6));
        bigDecimallist.add(new BigDecimal(7));
        bigDecimallist.add(new BigDecimal(15));
        bigDecimallist.add(new BigDecimal(17));
        bigDecimallist.add(new BigDecimal(20));
        bigDecimallist.add(new BigDecimal(30));
        bigDecimallist.add(new BigDecimal(45));
        bigDecimallist.add(new BigDecimal(48));
        bigDecimallist.add(new BigDecimal(29));
        bigDecimallist.add(new BigDecimal(22));
        bigDecimallist.add(new BigDecimal(5));
        bigDecimallist.add(new BigDecimal(25));
        bigDecimallist.add(new BigDecimal(26));
        bigDecimallist.add(new BigDecimal(31));
        bigDecimallist.add(new BigDecimal(41));
        bigDecimallist.add(new BigDecimal(45));
        bigDecimallist.add(new BigDecimal(65));
        bigDecimallist.add(new BigDecimal(15));
        bigDecimallist.add(new BigDecimal(32));
        bigDecimallist.add(new BigDecimal(23));
        bigDecimallist.add(new BigDecimal(57));
        bigDecimallist.add(new BigDecimal(23));
        bigDecimallist.add(new BigDecimal(5));
        bigDecimallist.add(new BigDecimal(12));
        bigDecimallist.add(new BigDecimal(56));
        bigDecimallist.add(new BigDecimal(1));
        bigDecimallist.add(new BigDecimal(2));
        bigDecimallist.add(new BigDecimal(5));
        return bigDecimallist;

    }

    @Data
    @AllArgsConstructor
    public class Unit {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    public class Sku {
        private Long id;
        private String name;
        private Long unitId;
        private BigDecimal stock;
        private BigDecimal price;
    }
}
