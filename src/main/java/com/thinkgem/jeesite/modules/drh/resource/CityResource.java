package com.thinkgem.jeesite.modules.drh.resource;

import java.io.Serializable;
import java.text.Collator;
import java.util.*;

import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.City;
import com.thinkgem.jeesite.modules.drh.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/city")
public class CityResource {

    @Autowired
    private CityService cityService;
    final Collator pinyinComparator = Collator.getInstance(Locale.CHINA);


    @RequestMapping("/list")
    @ResponseBody
    public ResultModel cityList() {
        ResultModel result = new ResultModel(0, "success", new LinkedHashMap());
        List<City> cityList = cityService.findList(new City());
        Collections.sort(cityList, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return pinyinComparator.compare(o1.getCity(), o2.getCity());
            }
        });
        List citys = new ArrayList();
        citys.add(new CityShow("A",groupByFirstWord(cityList,"阿坝藏族羌族自治州","巴彦淖尔市")));
        citys.add(new CityShow("B",groupByFirstWord(cityList,"巴彦淖尔市","沧州市")));
        citys.add(new CityShow("C",groupByFirstWord(cityList,"沧州市","达州市")));
        citys.add(new CityShow("D",groupByFirstWord(cityList,"达州市","鄂尔多斯市")));
        citys.add(new CityShow("E",groupByFirstWord(cityList,"鄂尔多斯市","防城港市")));
        citys.add(new CityShow("F",groupByFirstWord(cityList,"防城港市","甘南藏族自治州")));
        citys.add(new CityShow("G",groupByFirstWord(cityList,"甘南藏族自治州","哈尔滨市")));
        citys.add(new CityShow("H",groupByFirstWord(cityList,"哈尔滨市","基隆市")));
        citys.add(new CityShow("J",groupByFirstWord(cityList,"基隆市","喀什市")));
        citys.add(new CityShow("K",groupByFirstWord(cityList,"喀什市","拉萨市")));
        citys.add(new CityShow("L",groupByFirstWord(cityList,"拉萨市","马鞍山市")));
        citys.add(new CityShow("M",groupByFirstWord(cityList,"马鞍山市","那曲地区")));
        citys.add(new CityShow("N",groupByFirstWord(cityList,"那曲地区","攀枝花市")));
        citys.add(new CityShow("P",groupByFirstWord(cityList,"攀枝花市","七台河市")));
        citys.add(new CityShow("Q",groupByFirstWord(cityList,"七台河市","日喀则地区")));
        citys.add(new CityShow("R",groupByFirstWord(cityList,"日喀则地区","三门峡市")));
        citys.add(new CityShow("S",groupByFirstWord(cityList,"三门峡市","塔城市")));
        citys.add(new CityShow("T",groupByFirstWord(cityList,"塔城市","万宁市")));
        citys.add(new CityShow("W",groupByFirstWord(cityList,"万宁市","西安市")));
        citys.add(new CityShow("X",groupByFirstWord(cityList,"西安市","雅安市")));
        citys.add(new CityShow("Y",groupByFirstWord(cityList,"雅安市","枣庄市")));
        citys.add(new CityShow("Z",groupByFirstWord(cityList,"枣庄市","组")));

//        result.put("A",groupByFirstWord(cityList,"阿坝藏族羌族自治州","巴彦淖尔市"));
//        result.put("B",groupByFirstWord(cityList,"巴彦淖尔市","沧州市"));
//        result.put("C",groupByFirstWord(cityList,"沧州市","达州市"));
//        result.put("D",groupByFirstWord(cityList,"达州市","鄂尔多斯市"));
//        result.put("E",groupByFirstWord(cityList,"鄂尔多斯市","防城港市"));
//        result.put("F",groupByFirstWord(cityList,"防城港市","甘南藏族自治州"));
//        result.put("G",groupByFirstWord(cityList,"甘南藏族自治州","哈尔滨市"));
//        result.put("H",groupByFirstWord(cityList,"哈尔滨市","基隆市"));
//        result.put("J",groupByFirstWord(cityList,"基隆市","喀什市"));
//        result.put("K",groupByFirstWord(cityList,"喀什市","拉萨市"));
//        result.put("L",groupByFirstWord(cityList,"拉萨市","马鞍山市"));
//        result.put("M",groupByFirstWord(cityList,"马鞍山市","那曲地区"));
//        result.put("N",groupByFirstWord(cityList,"那曲地区","攀枝花市"));
//        result.put("P",groupByFirstWord(cityList,"攀枝花市","七台河市"));
//        result.put("Q",groupByFirstWord(cityList,"七台河市","日喀则地区"));
//        result.put("R",groupByFirstWord(cityList,"日喀则地区","三门峡市"));
//        result.put("S",groupByFirstWord(cityList,"三门峡市","塔城市"));
//        result.put("T",groupByFirstWord(cityList,"塔城市","万宁市"));
//        result.put("W",groupByFirstWord(cityList,"万宁市","西安市"));
//        result.put("X",groupByFirstWord(cityList,"西安市","雅安市"));
//        result.put("Y",groupByFirstWord(cityList,"雅安市","枣庄市"));
//        result.put("Z",groupByFirstWord(cityList,"枣庄市","组"));

        result.put("cityList",citys);

        return result;
    }

    private List<City> groupByFirstWord(List<City> list, String from, String to) {
        List<City> result = new ArrayList();
        for (City city : list) {
            if(pinyinComparator.compare(city.getCity(),to)>0){
                break;
            }
            if(pinyinComparator.compare(city.getCity(),from)>=0&&pinyinComparator.compare(city.getCity(),to)<0){
                result.add(city);
//                list.remove(city);
            }
        }
        return result;
    }

}

class CityShow implements Serializable{
    private String group;
    private List<City> cities;

    public CityShow(String group, List<City> cities) {
        this.group = group;
        this.cities = cities;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}