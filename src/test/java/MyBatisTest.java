import com.example.dao.WorkNodeDao;
import com.example.entity.WorkNode;
import com.example.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

public class MyBatisTest {
    public static void main(String[] args) {
        SqlSession sqlSession =  MyBatisUtil.getSqlSession();
        WorkNodeDao mapper = sqlSession.getMapper(WorkNodeDao.class);
        WorkNode workNode = new WorkNode();
        workNode.setHostname("hostname");
        workNode.setPort("990");
        workNode.setType(1);
        workNode.setLaunchDate(new Date());
        mapper.addWorkerNode(workNode);
        System.out.println(workNode.toString());
        sqlSession.commit();
        sqlSession.close();
    }
}
