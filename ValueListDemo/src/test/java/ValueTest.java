import net.mlw.vlh.ValueList;
import net.mlw.vlh.ValueListHandler;
import net.mlw.vlh.ValueListInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author iths
 * @date 2018/4/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:valuelist_test.xml")
public class ValueTest {

    @Autowired
    private ValueListHandler valueListHandler;

    @Test
    public void testLimit(){
        ValueListInfo valueListInfo = new ValueListInfo();
        //pageNumber 第几页
        //numberPageSize 每页显示条数
        int pageNumber = 2;
        int numberPageSize = 2;
        valueListInfo.getFilters().put("numberPageSize",2);
        valueListInfo.getFilters().put("startPageSize",(pageNumber-1)*numberPageSize);
        ValueList valueList = valueListHandler.getValueList("query.member", valueListInfo);
        List<Map<String,Object>> list = (List<Map<String,Object>>) valueList.getList();
        for(Map<String,Object> row:list){
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String,Object> entry:row.entrySet()){
                sb.append("["+entry.getKey()+":"+entry.getValue()+"]");
            }
            System.out.println(sb.toString());
        }
    }

    @Test
    public void testBase(){
        ValueList valueList = valueListHandler.getValueList("query.member", new ValueListInfo());
        List list = valueList.getList();
        System.out.println(list.size());
    }
}
