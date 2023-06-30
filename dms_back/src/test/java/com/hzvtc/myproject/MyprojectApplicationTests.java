package com.hzvtc.myproject;

import com.hzvtc.myproject.annotation.RequirePermission;
import com.hzvtc.myproject.dao.*;
import com.hzvtc.myproject.entity.*;
import com.hzvtc.myproject.service.*;
import com.hzvtc.myproject.utils.MD5Util;
import com.hzvtc.myproject.utils.Match;
import com.hzvtc.myproject.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyprojectApplicationTests {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    SystemUserMapper systemUserMapper;
    @Autowired
    SystemFunctionMapper systemFunctionMapper;
    @Autowired
    SystemRoleMapper systemRoleMapper;
    @Autowired
    SystemUserService systemUserService;
    @Autowired
    SystemFunctionService systemFunctionService;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    SystemLogMapper systemLogMapper;
    @Autowired
    FacultyMapper facultyMapper;
    @Autowired
    BuildingService buildingService;
    @Autowired
    BuildingMapper buildingMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    DepartApplicationService departApplicationService;
    @Autowired
    BackLateMapper backLateMapper;
    @Autowired
    RepairService repairService;
    @Autowired
    RepairMapper repairMapper;

    @Test
    void generate() {
        Repair list = repairMapper.query(1L);
        System.out.println(list);
    }

    @Test
    void testBackLate() {
        List<BackLate> list = backLateMapper.list(new BackLate(), buildingService.getIdsByParentId(1L));
        System.out.println(list);
    }


    @Test
    void testDepartApplication() {
//        List<DepartApplicationUser> list = departApplicationService.listApplicationFlow(2L);
//        System.out.println(list);
        DepartApplication departApplication = departApplicationService.getByDepartApplicationUserId(2L);
        System.out.println(departApplication);
    }


    @Test
    void testNotice() {
        Notice notice = noticeMapper.queryWithReceiver(28L);
        System.out.println(notice);
    }

    @Test
    void generateUser() {
        for (int i = 0; i < 20; i++) {
            SystemUser systemUser = new SystemUser();
            systemUser.setRealName(generateName());
            systemUser.setCellphone(generatePhone());
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0,8);
            systemUser.setLoginName(uuid);
            systemUser.setEmail(uuid + "@163.com");
            systemUserService.saveOrUpdate(systemUser);
        }
    }


    @Test
    void generateStudent() {
        for (int i = 0; i < 3000; i++) {
            try {
                Student student = new Student();
                student.setRoomId(generateRoomId());
                student.setNumber(generateNumber());
                student.setPhone(generatePhone());
                student.setName(generateName());
                student.setFacultyId(generateClassId());
                studentService.saveOrUpdate(student);
            } catch (Exception e) {
                i--;
            }
        }
    }

    long generateClassId() {
        long[] ids = {10,11,12,15,16,18,19,20,22,23};

        return ids[getNum(0, ids.length - 1)];
    }

    int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    String generateNumber() {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    String generatePhone() {
        String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    String generateName() {
        String[] Surname= {"张浩","朱秀梅","方斌","任倩","叶秀云","王文","邵志强","王桂荣","郭想","黄玉","陈勇","张凤兰","李刚","何莉","刘超","毛利","陈刚","姚宁","马萍","张凤兰","余龙","王涛","黄飞","温晶","李杰","贾娜","黄彬","李鹏","陈想","张颖","张兰英","杜雷","张红","严静","尹洁","沈婷","文伟","詹冬梅","廖倩","杜佳","冉雷","李燕","李佳","耿飞","何红霞","况超","莫晨","曹雷","唐帆","李淑兰","何兰英","刘秀英","曹桂芝","黄秀荣","蔡兰英","魏桂兰","张红","郭英","任玉英","魏文","彭阳","林丽丽","丁秀梅","霍兵","薛峰","卢彬","潘淑兰","曹彬","范霞","张倩","程伟","李秀荣","刘淑英","陆楠","杨磊","李璐","张坤","冯建平","宋欢","张林","申英","高娜","黄坤","陈帅","李东","杨秀兰","夏红","丁涛","陶宇","黄军","李建","任洁","尚鑫","林晶","韦婷","曲莉","李梅","高鹏","岳成","孙博","徐坤","吴丽丽","唐志强","孙兰英","潘艳","蒙燕","林涛","刘鑫","吴凯","叶林","李燕","马峰","刘博","王敏","梁磊","李玉珍","韦娟","唐林","袁瑜","陆杨","杨桂英","刘军","庄洁","赵刚","李峰","梁勇","王磊","崔红梅","李亮","张海燕","余娜","吴浩","赵红霞","陈凯","张强","李宁","李佳","康琴","王静","周瑞","潘柳","崔晨","丁文","蒋梅","周瑜","欧杰","王秀珍","吴佳","张建","朱楠","许岩","葛龙","林坤","黄瑜","董雷","余利","刘玉","林秀兰","黄宁","邓桂兰","韦丹丹","刘云","李桂英","李瑞","李秀珍","陈金凤","任磊","尹桂芝","谢磊","赵伟","江丽","徐洋","刘雪","蔡建华","谭艳","高丽娟","邹丽娟","祖丹丹","彭洁","胡丽","胡丹","吕欣","范琳","葛璐","叶红梅","季鹏","李宇","邹兰英","孙萍","段博","黎宁","罗玉兰","吴雪梅","马峰","丁云","叶飞","谢建","张琳","夏倩","李莹","王云","沈林","钱小红","李帆","张亮","何玲","刘丽丽","冯俊","聂建平","李坤","汪建","张秀云","李军","石宁","刘颖","孙英","何桂芳","杨玉华","杜玉兰","张丽丽","李俊","李淑兰","郑桂芳","李成","杨洁","费瑜","罗坤","黄红霞","王英","颜帅","敖杨","文芳","杨霞","李云","官玉珍","邹健","孙云","孙俊","祁桂花","董凯","吴秀珍","荣桂花","郭玉华","尹红梅","林凯","李桂芳","白淑英","和洋","陈晶","陈龙","王畅","唐琴","陶淑英","白玉华","赵辉","柴荣","郑红梅","张飞","喻鑫","张华","董强","刘丽","郭淑兰","刘鹏","黄东","周春梅","王琳","陈建国","覃梅","张丽","徐宇","杨华","毕春梅","周林","林勇","吴想","王瑞","宋颖","苏琴","魏琳","缪颖","梁建华","位琴","马欣","马红","陆平","夏志强","雷英","谢畅","彭桂荣","刘华","范勇","黄坤","叶红霞","李文","张斌","尹冬梅","雷浩","王玲","李桂荣","马勇","陈丽丽","易红霞","欧颖","隆秀华","吴柳","李杨","陈浩","李斌","董慧","林玉华","王晨","徐凤英","刘建华","刘欢","董瑞","黄红霞","路瑜","左浩","王芳","李婷","汤凤英","陈洋","张雷","崔想","王玲","崔涛","柳凤英","张丽丽","张芳","吕婷婷","张玉英","张琴","龙明","谢文","周鑫","钟涛","叶桂芳","张彬","王杨","王金凤","周春梅","张红霞","解玉梅","杨彬","秦鹏","孙淑华","曹婷婷","张红梅","杨勇","李建国","韩丽丽","赵莹","田玉英","杨桂芝","戴春梅","赵玲","田成","王阳","王凤英","唐秀华","王峰","刘兰英","陈彬","赵玉兰","叶霞","曹霞","赵玉","王阳","范华","徐瑞","王红霞","赖桂香","潘坤","梁倩","唐玉","蒋萍","杨丽","季秀珍","邢琴","冯志强","宋宁","邱红霞","屈淑英","陈建","康军","吴雷","姜欢","唐丽娟","陈秀芳","唐秀芳","何桂珍","蒋桂香","龚兰英","黄丹丹","谢军","张慧","翟宁","黄俊","廖娟","许婷婷","廖华","周桂香","陈凤英","蔡丹","刘秀云","邓涛","李淑英","吴佳","宁超","彭秀荣","刘娜","叶涛","侯秀英","张刚","冯鑫","乌涛","陈春梅","周峰","鞠丹丹","段璐","程淑珍","冯磊","黄杰","曾秀云","赵飞","杨红霞","李建国","王秀珍","李春梅","迟桂芝","邓萍","王健","路瑞","陈杨","林凤英","王峰","刘颖","黄梅","尹志强","周秀兰","刘强","程小红","石桂英","张丽","黄建","柳浩","冯龙","姜柳","王杰","马倩","何帅","赵晨","吴建军","陈倩","唐建国","郑莹","祁桂芳","戴洋","李晶","刘健","斯静","杨宁","范亮","吴小红","梁秀华","李建平","田颖","梁艳","裴丹丹","雷红梅","黎欣","王强","李玉华","王红霞","李强","李超","陆静","徐丹","李凤兰","张小红","吴雷","王英","徐晨","吕平","杨辉","陈秀云","张波","刘丽","刘宇","魏丽","汪丹","罗燕","刘佳","王丽丽","张丽丽","温丹","宁丽娟","韦俊","吴莹","吴健","张玉兰","孙鑫","程萍","王强","王莉","李淑珍","李飞","李玉","张帅","许淑兰","韦强","姜柳","汪洁","邹健","夏想","邱兵","刘婷","孔建军","李玉兰","丁佳","燕波","张秀华","许静","郜桂香","王桂珍","谢婷","何龙","杨萍","邱婷婷","柳玉兰","陈凤英","张桂芝","徐飞","陈玉华","杨云","卞丽","牟建军","马雪梅","陈丽娟","徐桂英","李娜","徐利","郭帅","李辉","王艳","杨峰","何莉","王宇","章海燕","刘雷","李冬梅","朱艳","李杨","卢佳","曹云","张超","熊小红","张旭","夏欢","李成","柏涛","贺艳","陈红梅","刘桂芝","李红","孙帆","余艳","曹娟","朱莹","王欢","吴亮","彭杨","王杰","刘桂芳","刘刚","吴楠","杨红","艾丽娟","林秀云","魏莹","俞慧","匡鹏","徐玉梅","周瑜","赵玉珍","张桂兰","周刚","刘龙","胡春梅","俞琳","章梅","李杰","张倩","黄瑜","王岩","李坤","陆鹏","刘婷","陆成","杨莉","刘鑫","张金凤","陈洋","计峰","王健","石璐","江勇","萧敏","张璐","黄瑞","奚桂兰","邵婷婷","黄佳","张霞","徐伟","车明","杨秀英","唐帅","杨帆","董倩","鞠建国","朱霞","郑柳","陈强","李建华","常欣","刘芳","梁慧","李雪","李欢","朱平","李超","袁平","王丽华","王静","徐阳","杨宁","王淑兰","倪英","龙琴","常俊","张涛","郭丹丹","罗帅","张刚","董勇","赵淑兰","郑俊","丁坤","李波","连欢","王秀梅","许磊","贾桂荣","冯倩","温莉","高秀芳","杨瑜","唐雪","王建平","穆丹丹","洪林","刘健","熊英","毛桂珍","王琴","邵淑珍","李瑞","徐燕","杜春梅","严霞","张磊","杨文","曹利","陈倩","孙俊","冯秀兰","高桂荣","李桂珍","张秀华","黎飞","冯秀荣","田丹","黄玉梅","安想","张明","谢洋","周桂珍","谭颖","孙彬","张淑英","田玉英","苗燕","邹秀兰","梁凤英","李帅","周建军","陈雪梅","陈萍","王明","高冬梅","杨楠","黄刚","景云","苏兵","张斌","张桂芝","郭秀云","张凤英","赖平","沈桂珍","孔帆","满玉兰","黄亮","常秀兰","柯丽","刘丽","董英","冯霞","周云","袁淑兰","邹帅","黄帆","张霞","陈兵","张敏","阎丽华","杨桂香","王梅","宋宇","杜秀华","郝斌","韦兰英","胡淑珍","张建","李旭","宋秀珍","邓凤兰","韦金凤","张淑兰","刁欣","吴云","刘刚","卢林","罗桂香","李桂荣","李丹丹","陈娜","胡帅","刘婷","马丽娟","金静","王桂芝","杨瑜","王海燕","冯红梅","覃莹","郭瑜","陈桂荣","朱丹丹","林娟","刘杨","张秀珍","张梅","张秀华","黄霞","郝海燕","张帆","马海燕","杨鹏","高强","冯柳","唐玉珍","高莉","李欢","孙艳","王淑华","蔡雪","田桂花","周帅","刘帅","荣辉","张宁","王畅","张秀兰","祝林","叶莉","蔡洁","董金凤","段荣","郭梅","袁小红","毕金凤","栾红梅","王建华","吴斌","吴玉珍","路彬","李建军","刘瑜","孙静","朱丹","雷玉英","陈洁","吕玉英","杜畅","李帅","汪玉英","李洋","戴婷","萧佳","管飞","张晶","高志强","马宇","陈瑜","庞平","王秀云","冯晶","李淑珍","田晨","谢波","徐桂珍","孙磊","马芳","王建华","岳坤","李瑞","张璐","孙秀芳","袁雪","黄雷","刘娟","游桂芝","张桂兰","王东","周璐","马勇","乔飞","许博","何敏","赵莹","曾博","王建","凌淑华","余龙","段辉","谢秀英","王伟","蔡云","姚鹏","张建国","童军","丁建国","丁秀华","黄金凤","赵倩","胡鑫","谭兰英","王想","施萍","何娜","杜欣","刘华","钟桂花","郭俊","贾宇","马桂芝","张秀芳","徐伟","廖涛","郭淑珍","王丽华","魏金凤","雷龙","李琴","金丹丹","郑瑜","张秀梅","韩玉华","盛文","杨琴","汪杰","王利","王淑兰","王丹","陈杰","李兵","吕红霞","刘莉","刘洁","张婷婷","罗林","刘倩","赖丹","朱洁","张畅","吴芳","董兵","颜桂兰","石桂花","刘婷婷","齐桂芳","颜楠","何金凤","徐玉英","杨琳","张莉","李建华","莫峰","易荣","蔡峰","卢利","彭超","杨强","何红霞","全鑫","袁强","周娜","梁春梅","熊慧","车丽华","章桂兰","李艳","王丽","彭红霞","陈小红","王东","张春梅","张健","关瑜","梅刚","刘柳","邵秀华","朱桂英","杜鑫","韩瑜","冯兰英","阎倩","刘杨","凌龙","石建军","吴小红","刘林","洪丽娟","姚淑英","谭桂芝","谭林","何颖","王玉","柏桂芝","艾秀华","夏林","应柳","崔建平","李桂荣","周云","张旭","周丽娟","陈琴","陆玉兰","陈龙","苑倩","李琴","余淑兰","孙建国","高淑珍","李秀兰","张雪","鞠峰","温明","杜红","周欢","王波","徐萍","黎秀华","万秀梅","刘婷","李玉英","李志强","郭秀华","朱林","王浩","张杨","李欢","陈凯","海鹏","姚丽华","高建平","吴丽","李丽丽","祝红","刘玉","王秀珍","高秀华","梁成","雷建","胡琳","仝英","唐佳","王涛","张丹丹","刘明","刘佳","苑成","陈帅","徐秀英","颜晶","王桂兰","黄军","张倩","严丽华","陈畅","刘雷","蒙志强","夏红霞","梁桂珍","李琴","万利","童娟","杜敏","王宁","李凯","刘勇","靳桂花","杨玉梅","赵秀芳","刘玉珍","刘柳","张婷","陈梅","王秀梅","庞婷婷","何婷","刘亮","李欢","张文","江淑珍","刘兵","袁楠","位伟","鲁丽华","薛建","王凤兰","田宇","胡健","梁芳","刘玲","向岩","赵涛","李莉","胡桂英","高辉","周荣","余秀兰","陈玉","吴旭","苗彬","赵彬","林建国","郭刚","丁春梅","侯玲","屈凤英","张秀梅","朱秀云","何敏","熊秀兰","李春梅","夏宇","李宇","江畅","杨慧","王杨","王芳","徐文","杨建国","傅凤英","李春梅","王欢","姜丹","陈利","梁帆","李婷","张秀芳","萧颖","朱宇","马海燕","林帅","朱丹丹","刘坤","熊静","孙畅","张秀兰","苟峰","张荣","阳倩","李玉华","颜丹","奚丽","徐兵","葛晨","刘超","姜玉华","赵超","赵斌","杨桂花","韩桂珍","何荣","米雪","张慧","王兰英","雷玉英","余秀荣","梁海燕","鲍玲","彭帅","王春梅","詹静","刘莹","赵莹","杨小红","郭强","马辉","唐桂荣","蔡小红","罗刚","林想","赖红","萧红梅","苟玉珍","顾健","万慧","邱丽华","田玉珍","孙涛","罗波","张红","罗凯","姜宇","葛艳","程建平","卢凤英","张琴","戴帅","陈宇","钟娜","李文","邬亮","鞠桂香","徐波","李利","胡玉兰","李倩","周琴","王涛","张博","戴鑫","邓玉华","张旭","赵浩","魏丹","曹桂花","李梅","沈阳","玉建平","刘玉兰","金慧","刘洁","康利","黄娜","张彬","陈宇","王丽","饶成","冯健","周秀华","欧莉","程敏","杜成","王旭","张淑珍","孙静","黄丽娟","王晶","潘磊","王春梅","李桂珍","范桂珍","张博","陈欢","杨春梅","李兰英","刘军","王亮","李旭","余俊","莫晶","王凤兰","李金凤","李宁","陈雪","郑艳","赵欢","何燕","王丽华","吴建华","吴龙","刘凤英","吴志强","胡刚","王淑珍","杨畅","游雪","陈秀梅","李旭","夏杨","马莉","刘利","朱勇","姚娟","张玉珍","朱柳","顾岩","张刚","徐强","李鑫","吴秀兰","郭冬梅","沈秀云","熊秀兰","刘阳","李淑珍","张秀华","殷林","韩建","彭帆","刘秀芳","张丹","文文","李璐","张秀英","萧玲","杨想","王燕","陈凤英","吕荣","岳欢","温志强","房秀珍","侯刚","尹飞","许桂荣","张娟","余佳","王荣","刘淑兰","张娜","陆杨","张玉珍","陶莉","雷瑜","吴小红","吴彬","杨红","赵秀梅","李桂兰","任桂芝","吴桂珍","钟鹏","游静","党雪梅","辛畅","刘鑫","邹雷","赵红霞","王敏","陈利","王建军","满欢","刘萍","张淑华","李鑫","杨金凤","许宇","杨洋","李军","王玉兰","周楠","杨峰","阎丽","马华","陈秀梅","文瑞","杨桂芝","郭博","缪凤英","杨娜","唐燕","姚强","王玉珍","李梅","郭阳","莫淑英","苟明","王莉","邹桂珍","曹楠","刘畅","葛勇","张超","刘文","阮英","阎瑞","贺玉英","屈龙","黄倩","罗燕","耿红霞","陈红","武霞","岳雷","陈柳","陈亮","张亮","赵俊","施玲","王燕","余秀华","陈阳","王洁","徐桂花","张桂荣","李玲","刘玲","陈红梅","张雷","周建国","陈强","袁柳","黄颖","李淑华","牟芳","朱鑫","章冬梅","郁燕","王秀芳","陈佳","宋涛","李鑫","曹建平","苗桂兰","王鹏","高秀云","叶鹏","郑凤兰","林丽华","杜凤英","司伟","罗帆","胡超","张鑫","李淑华","曹坤","郭玲","滕建华","房兰英","陈文","哈建","赵丽华","樊莹","邓建国","史桂芝","谭杰","李楠","高玉珍","冯杰","陶健","邓红梅","赵峰","周凤英","王凤英","梁波","张旭","李秀芳","朱桂珍","杨玉英","杨莹","王洋","王丽丽","张颖","韩峰","邓楠","张秀珍","崔洁","曹金凤","张波","王丹","谢婷婷","郭小红","路丽华","罗洋","周秀芳","劳健","李莉","张楠","杨洋","廖岩","潘欣","王艳","黄秀华","刘文","黄斌","李桂兰","萧秀云","韦欢","邓秀珍","张淑华","沈楠","郑磊","王伟","李兵","胡桂兰","王桂花","车亮","高丽华","王强","尚洋","马琳","汪秀华","薛丽华","李浩","刘林","夏鹏","李玉兰","余宁","蒙利","满婷","孙慧","王阳","林淑英","刘旭","姜志强","刘桂香","符秀英","薛龙","梁浩","杨林","龚英","仝秀兰","宋婷","万帅","王小红","杨萍","陈林","安凤兰","韦洋","王丽","童宇","韩想","李建","汪刚","盛芳","刘桂香","李丹","吕淑兰","郭兵","吴桂香","张冬梅","彭秀珍","李楠","王鹏","林淑华","陈玲","樊玉","李晨","陈军","罗磊","李坤","陈秀云","陈洋","刘秀兰","唐秀珍","司凤兰","高秀云","莫兵","石冬梅","林雪","秦倩","陈雪","李红霞","裴彬","张玉","刘平","高磊","杨桂芳","谢岩","鲍建平","王浩","李明","李坤","马玉梅","白桂香","陆淑华","李红梅","罗洋","张想","祝志强","杜淑兰","陶秀云","龚秀英","李杨","陆秀梅","吴明","王峰","何璐","凌丹丹","沈建军","汤燕","李健","艾桂香","危波","陈芳","袁丽","施辉","李琴","王桂芝","吴云","王欢","杨红","邹婷婷","张敏","吴林","韦婷","陆峰","兰丽华","王磊","李晶","宋秀芳","简凤兰","孙英","谢桂花","陈杨","钱磊","张文","刘超","雷芳","朱倩","方帅","邓华","马岩","邓霞","张红梅","王明","杜婷","李金凤","杨丹","李静","吴刚","赵玉梅","邱冬梅","林秀英","李秀华","晏桂兰","罗敏","丁超","张建国","张桂花","杨桂英","蒋洁","崔宇","张婷婷","李瑞","张玲","郑桂兰","王云","陈雷","薛丹丹","朱刚","乔丹","王柳","郭雪梅","董玉华","杨洁","张利","隋玉华","赵秀英","刘丽丽","罗博","张勇","刘红","黄成","李艳","王秀梅","陈春梅","唐桂珍","李淑英","叶鑫","杨杨","李秀珍","张秀芳","宋秀珍","朱博","吴芳","勾瑞","赵秀荣","武敏","郝丽华","邓明","张岩","朱桂荣","盛玉","张杰","郑建军","陈燕","王志强","贺瑞","陈慧","甘超","蔡刚","朱玉兰","张桂兰","李云","刘瑞","裴浩","徐超","王荣","顾红霞","赵俊","田淑兰","张桂珍","崔成","霍欢","李秀珍","刘帆","刘丹","吴晨","韦红梅","孙玉兰","相东","王磊","张阳","丁丹","张俊","常健","杨淑兰","王建平","赵洁","崔玉兰","袁晶","傅秀华","陈红梅","单宇","崔英","杨秀兰","谢玉华","陈波","龚磊","刘秀华","冯欣","陈琴","涂琴","彭波","黎秀芳","张娜","韦强","段凤英","钱玉兰","阮秀华","袁娟","张洁","刘婷","李秀兰","谢岩","吕桂香","李鑫","王英","张莉","陈波","许志强","袁玉梅","余燕","王建华","林桂香","王浩","杨玉华","崔玉","徐英","郑建国","常萍","龚春梅","刘丽华","方伟","谢伟","钱伟","王宁","杨倩","林婷","朱彬","管红","潘艳","刘平","刘淑华","龙亮","李桂香","徐博","王霞","邹丽丽","吴林","鞠龙","黄涛","姜海燕","邓淑珍","陈燕","杨波","杨秀梅","李岩","张杰","郭玉梅","李欣","宋波","戴秀芳","王秀珍","孙玉珍","冀丹","周云","何海燕","陈艳","方桂香","郭峰","龙淑英","戴建平","胡淑英","谭玉华","邵玉英","陶海燕","宋丽娟","谢龙","叶志强","王琴","刘红霞","沈晨","赵勇","卫成","潘鹏","李杨","钱波","刘建华","宋丽华","柳秀荣","王凯","刘莹","王利","彭亮","曹军","贾秀梅","袁军","王佳","吕金凤","吴波","燕春梅","金秀兰","曾龙","朱春梅","白林","孟玲","李晨","李荣","周兰英","冯荣","唐玉","张云","李燕","赵平","李玲","张超","刘云","汪杨","成红","王强","董桂芳","王平","郑淑英","张明","傅玉兰","刘璐","白瑜","虞丽华","曾金凤","黄桂香","杨阳","文健","张岩","崔建华","郭莉","李婷","刁倩","陈峰","杨晶","林强","王勇","赵玲","刘凤兰","张冬梅","王婷","王雪梅","陆英","雷宇","夏鹏","叶红","杜峰","张玲","温平","李鹏","傅淑珍","傅桂兰","齐敏","潘鑫","杨丽华","成莹","黄英","倪利","郭浩","程杰","刘帆","丁涛","李飞","韩桂香","任芳","孟金凤","张丹丹","黄涛","孟瑜","殷丽","徐晶","王琳","华丽华","鲍欢","龙秀梅","张玉梅","张冬梅","陈静","李建华","赖秀梅","彭想","舒斌","王秀华","陈杰","周小红","赵雷","张桂英","黄瑜","吴冬梅","陶彬","茹丽","门欢","隋红梅","温丽丽","刘洁","王晶","梁丽丽","王涛","刘志强","陈云","刘凤英","支辉","刘丽","樊桂芝","王建","陆斌","熊秀珍","赵平","杨淑英","李红","王鹏","张淑英","古莹","王芳","郭颖","秦桂芝","史玉华","李桂香","陈雷","马丽丽","黄云","倪秀芳","刘兰英","毛斌","王晶","刘建","马建平","张宇","应阳","侯俊","黄敏","湛婷","潘洁","乔洋","李刚","李小红","梁健","颜静","葛建华","雷楠","蔡秀英","凌莉","王英","李凤兰","吴鹏","马畅","滕兵","李杨","刘小红","许峰","王兵","张彬","陈龙","何梅","曾荣","赖玉","马静","秦宁","王璐","陈雪","王丽丽","王建华","江淑珍","崔玲","钱杨","孙坤","陈莹","刘飞","郭桂英","陈桂兰","黄艳","施丽丽","黎晨","王斌","应金凤","郑丹","张健","陈玉英","陈丽丽","舒峰","杨淑珍","陈淑珍","王佳","王晨","朱敏","毛涛","张桂花","徐淑珍","李倩","赵琴","许倩","贺健","曹玉英","吴宁","尹凯","魏瑜","戴秀芳","赵秀梅","郭刚","林宁","李丽华","彭桂香","陈鹏","王兵","萧桂兰","张华","刘芳","邓静","靳玉英","刘娜","郑瑞","蔡红梅","张淑华","周秀荣","卢飞","曾帅","罗林","吕桂英","宋娟","戴婷","陈英","焦丹","孙娜","张雷","欧阳峰","李玲","刘冬梅","邱强","项玉","徐丹","王燕","刘杰","彭桂花","罗华","罗柳","李凤英","王倩","朱明","郑柳","王玲","黄旭","刘慧","施淑兰","金欢","冯凤英","鞠俊","李婷","胡杨","母健","胡东","王桂花","叶佳","李建国","龚玉华","石琴","瞿淑兰","贾旭","林梅","萧婷","阎浩","吴秀梅","郭强","鹿玲","杨健","梁玉英","李畅","张建平","兰秀芳","符晨","黄玉梅","安慧","马荣","陈秀华","胡玲","夏艳","文丹丹","杨秀梅","谢鑫","李飞","魏春梅","陈桂芝","杨建华","邵磊","冯坤","陈杨","万秀兰","刘雪梅","邓金凤","郭帆","白慧","陈华","叶成","叶健","郑秀云","潘鹏","余晨","赵宇","庞玉华","陈鹏","陈玉兰","谭桂香","胡超","廖波","马桂芳","何萍","张玉珍","赵建华","傅帅","刘兵","唐海燕","陈建平","陈秀珍","沈鑫","熊淑兰","张丹","李楠","林金凤","殷春梅","姜凤英","葛龙","王淑兰","李桂荣","胡阳","徐金凤","张雷","杨亮","崔敏","王兰英","张丹","高桂香","张峰","杨建","周秀华","李凯","张畅","陆婷","王成","刘梅","林明","秦金凤","和秀荣","李晨","韩霞","陈峰","郭秀英","王秀梅","邢军","蓝红","刘勇","李想","杨亮","刘岩","胡倩","李冬梅","曲佳","毛莹","孙畅","郑明","王强","张秀荣","汪秀华","郑旭","秦强","顾玉梅","李桂芳","朱畅","陈刚","郭冬梅","曾雪梅","张鹏","莫红霞","韩秀梅","晋欣","孟丽丽","陈林","明艳","俞萍","张彬","苏金凤","李娟","黄燕","张岩","庞雪梅","孙帅","周桂珍","王文","杨淑珍","冯岩","吴华","吴晨","萧雷","寇利","杨红","贾桂兰","刘秀云","金桂兰","陈英","蔡超","徐丹丹","温畅","严坤","李雷","黄晨","陈英","赵建国","张梅","金帆","陈玉","蔡建军","舒梅","杨桂英","危勇","袁伟","刘玉华","李丽华","明利","苏旭","陈荣","赵璐","李冬梅","刘军","黄桂英","杨秀芳","范慧","王婷婷","李欢","李丽","韩秀荣","顾琳","陈莹","程冬梅","吴丹丹","田鑫","李瑜","黄明","赵岩","钱丽华","银刚","袁玉梅","张玉梅","赵淑珍","张玉华","邱杨","林博","李成","邓明","苏玉兰","李磊","董旭","王刚","刘飞","李浩","罗雪","朱雪梅","王桂荣","姜秀华","杨凤兰","吕鑫","曾佳","张鑫","王桂花","王桂荣","李静","陈荣","杨平","何博","牟波","王峰","倪小红","邓颖","刘斌","张秀云","陈龙","李刚","马红","宗莉","刘波","黎坤","李玉英","曹阳","张建国","李婷","马利","李桂珍","邢琳","彭桂香","蒋琳","谭淑英","胡淑英","董旭","滕鑫","屈勇","桂欣","李阳","周玉梅","萧兰英","李志强","彭春梅","张凯","李楠","王帅","陈晶","林建华","刘荣","杨龙","赵坤","李琳","郑建军","李红霞","马欢","柏秀荣","邵晨","朱畅","蒙金凤","张欢","田桂荣","田萍","李军","张娟","李莉","罗霞","梁艳","张晨","王燕","罗秀云","孙涛","赵鑫","李雪","叶辉","程想","余琳","童建国","符玲","周丽华","刘杰","陈荣","洪佳","曹婷","刘秀华","李想","卢丽娟","李秀梅","莫秀英","李涛","郭阳","孟宇","冯桂花","李丹","邹博","张芳","郭畅","金莹","刘丹","丁鑫","黄丽娟","叶健","王婷","赵秀梅","杨平","陈桂英","叶华","王桂芳","叶英","孙梅","彭杨","焦桂芳","刘超","曹兵","祝鹏","王海燕","吴勇","刘玉珍","熊倩","杜峰","李婷","邝杨","谢玲","张俊","刁云","林红霞","叶磊","李玉","何洁","姚芳","翁博","殷雷","姜鑫","曹娟","陈娟","梁莉","徐倩","魏军","叶红","王刚","李芳","李浩","周玉珍","赵荣","刘伟","宋志强","赵帅","余秀珍","李海燕","黄淑华","李秀英","张飞","陆霞","李欣","杨东","鲍欢","范莉","冯秀英","汪红霞","周丽华","董红","朱琳","马红梅","杨玉","郭红","唐建国","杨博","陆秀英","方斌","敬桂芝","李静","王桂英","张玉珍","刘艳","夏超","毛帅","赖建","李秀华","吴慧","黄玉英","石超","武红","薛婷婷","杨帅","刘丹","黄辉","陈静","支凯","王英","杨柳","赵华","周雪","陶桂花","李海燕","向波","李海燕","覃秀华","夏玉珍","黄健","张建","王鹏","安强","吴兵","樊秀珍","吴帆","郭桂荣","陶成","廖佳","刘鑫","袁颖","李超","雷冬梅","梁志强","廖秀兰","张飞","杨桂芳","吴淑英","温兵","李玉兰","李颖","马亮","牛欢","刘斌","关瑜","朱秀梅","白亮","赵雷","刘帅","李琳","刘梅","陈冬梅","曾华","萧杰","龙兰英","马丽华","张桂荣","杨莹","张荣","梁瑜","王秀英","董宁","李秀荣","彭波","杨帅","康帅","董莹","黄宇","许龙","黄凤兰","朱晶","罗玉华","黄宁","刘东","杨金凤","吴志强","韩成","周静","卢颖","曹平","邹鹏","张阳","张浩","王丽","张荣","郭桂兰","黄玲","刘娜","任云","李凤兰","姬云","满志强","刘宁","郎桂英","阳瑞","穆强","田丹","胡凤英","刘丹丹","陈秀梅","段雪梅","萧飞","张桂香","朱坤","单红","吴秀珍","齐秀华","郭艳","祝柳","张丹","谢桂花","王春梅","钟阳","伍倩","辛秀华","马婷婷","段桂珍","叶健","王静","温林","李玉英","邵想","韩博","卢娜","胡林","郑建","樊佳","李柳","刘桂兰","周淑华","祝辉","赵娜","张秀云","吴红霞","练畅","胡玉兰","杨慧","朱平","刘红梅","唐佳","楚玉兰","詹淑英","贺飞","陆峰","刘志强","王英","魏洋","赵秀荣","王玉华","张文","杨健","王兰英","饶龙","萧利","周桂珍","王倩","黄淑华","廖冬梅","金瑞","单玉珍","廖艳","李欢","莫晨","曾超","戴宇","王凤兰","逯桂芝","王刚","李畅","张霞","阎敏","高佳","曹莉","张洋","黄秀荣","李淑兰","张利","朱洁","牛倩","张勇","杜云","唐鹏","王桂英","杨明","陈红","李燕","马帅","周亮","赵丽","陈莹","张文","权勇","朱瑜","季淑英","张冬梅","漆兵","杨利","刘楠","陈秀梅","苟涛","杨宁","汪红霞","钟柳","刘雪","赵玉华","张秀荣","邢涛","杜柳","余东","刘玉英","黎波","李旭","金涛","滕辉","黄丽华","吴桂芝","王艳","李玉华","侯丽娟","徐秀珍","吕秀梅","王玉","刘金凤","宋丹丹","李秀荣","章建华","郑秀云","陈建华","覃宁","邱丹丹","李桂香","张龙","王秀兰","方桂芝","戴冬梅","刘雪梅","谢涛","潘超","张飞","刘文","杨萍","王云","宋兰英","袁秀珍","黄建国","黄红梅","孙琴","向秀珍","王雪","龙洋","李龙","刘浩","吴敏","刘佳","尹丽","林慧","潘红","张强","陈晶","张健","张华","李帆","张婷","王淑兰","姜桂荣","王坤","黄宁","郭欣","贾欢","刘帅","郑霞","程兰英","王晶","马林","汪莉","汪涛","张瑜","朱晶","李丽娟","赵娟","王刚","程丽娟","李帆","傅秀兰","张金凤","黄芳","王鹏","薛建","孙金凤","龚凯","陈鑫","刘琴","朱林","白东","丁强","雷岩","卜洋","何丹","李云","田想","曹浩","左志强","叶建华","宋想","王秀兰","舒洁","陈金凤","赵玉","丁平","谢春梅","杨强","宋玉英","陈桂兰","刘洋","陈淑英","刘建军","董林","杜帆","顾敏","熊磊","张淑华","赖伟","胡勇","张欢","杨旭","温超","何志强","谭淑华","彭华","刘辉","常帅","吴斌","郭丹丹","胡建","胡婷","武静","李琴","翟洁","刘柳","徐建军","黄瑞","林宁","翟丽娟","叶东","方平","吴斌","杨晨","高红梅","李慧","张涛","邓欢","谢建华","王辉","张伟","吕海燕","姜桂芳","谢亮","张勇","周平","杨瑜","雷娜","刘丽娟","陈雪梅","苏涛","曾凤英","周秀梅","孟瑜","王雷","王娟","叶阳","许杰","余淑兰","王旭","赵伟","谭柳","李霞","何秀芳","李华","潘浩","刘想","管想","闵鑫","郭成","朱梅","闻娟","杜丹丹","范秀芳","邢欣","沈秀兰","王萍","邓宁","李雷","殷淑华","赵桂珍","蔡秀云","李明","王超","张秀荣","杨云","林秀梅","梅平","罗海燕","彭帆","芦淑英","吴萍","赵俊","田兵","韩秀英","沈淑珍","李丽娟","邱晨","高梅","王鹏","易红梅","梁娟","刘莉","吴桂芳","胡勇","黄淑英","杨坤","杜军","刘强","张柳","李秀珍","杨柳","张兵","殷玉华","张桂珍","黄桂芝","李建国","王桂英","黄丹","田荣","罗玉英","吴桂兰","杜阳","史秀珍","曾丹丹","黄琴","许玉梅","陈健","潘岩","耿帅","杨建平","江雷","詹冬梅","罗坤","韩桂香","金玉华","张萍","阎建平","高燕","舒兵","彭辉","韩秀云","孙荣","祖波","邵娜","萧秀荣","李娜","李芳","靳华","崔桂芳","蒋东","黄杨","朱杰","钟建国","黄秀英","杨玉兰","李金凤","黄建军","朱娜","孙宇","王丹","王飞","周慧","魏桂荣","李飞","陈凤兰","汪鹏","张欣","刘娜","高桂花","陈萍","宋建国","马宁","陈峰","崔波","方秀珍","雷涛","马秀荣","李萍","吴萍","陈璐","罗琳","魏平","翟玉","玉兰英","张桂兰","赖秀兰","姜帅","阎辉","李桂芳","张娜","邵想","郑瑞","赵洋","王东","陈龙","邓倩","杨淑英","罗勇","杨英","孙兰英","马晶","冯想","周玉梅","齐芳","杨柳","杨飞","刘敏","李秀云","王波","王丹","黄鹏","周秀珍","黄峰","王桂珍","魏林","王博","王秀兰","李明","傅坤","张建军","张兵","李丽娟","刘玉英","袁海燕","许刚","王凯","毛梅","崔艳","赵秀珍","葛秀华","庄洋","韩建国","韦淑华","胡雪","车峰","郑东","张岩","张华","徐玉华","陶洋","吴淑兰","张楠","熊秀英","廖英","王佳","芦凤英","蔡莉","孙金凤","董华","蒙飞","陈丽丽","牛楠","詹健","覃磊","钟金凤","冯婷","张燕","张晶","姜柳","梅建","张玉英","武秀英","李斌","王冬梅","陈建军","李晶","胡建国","陈军","徐雷","李娜","李萍","张桂兰","郑健","沙丹","傅玉梅","黄婷婷","曹欣","王霞","周秀珍","安凯","白军","徐莉","黎鑫","孙玉","叶超","魏东","田楠","陆平"};
        return Surname[getNum(0, Surname.length - 1)];
    }

    long generateRoomId() {
        return new Random().nextInt(400) + 1;
    }

    @Test
    void testRoom() {
        for (int j = 1; j < 6; j++) {
            for(int i = 1;i < 11; i++) {
                String number;
                if (i < 10) {
                    number = "C" + j + "00" + i;
                } else {
                    number = "C" + j + "0" + i;
                }
                roomMapper.save(new Room().setMaxCapacity(6).setBuildingId(18L).setNumber(number));
            }
            for(int i = 11;i < 21; i++) {
                String number;
                if (i < 10) {
                    number = "C" + j + "00" + i;
                } else {
                    number = "C" + j + "0" + i;
                }
                roomMapper.save(new Room().setMaxCapacity(6).setBuildingId(19L).setNumber(number));
            }
        }

    }

    @Test
    void testStudent() {
        List<Student> student = studentMapper.list(new Student(), null, Arrays.asList(6L, 13L, 14L));
        System.out.println(student);
    }
    @Test
    void testBuilding() {

    }
    @Test
    void getMD5Password() {
        System.out.println(MD5Util.md5("123456"));
    }

    @Test
    void log() {
        List<SystemLog> logs = systemLogMapper
                .list(new SystemLog()
                        .setIp("172")
                        .setClas("user")
                        .setMethod("list").setUserId(2L).setOperateTimeStart(new Date()).setOperateTimeEnd(new Date()));
        System.out.println(logs);
    }

    @Test
    void testMapper() {
        List<SystemUser> list = systemUserService.listByIds(Arrays.asList(1L,23L));
        System.out.println(list);
    }

    @Test
    void facultyTest() {
        List<Faculty> list = facultyMapper.list(null);
        System.out.println(list);
    }

    @Test
    void testUserMapper() {
        List<Long> list = new ArrayList<>();
        list.add(2L);
        list.add(3L);
//        System.out.println(systemUserMapper.listIdFitCondition(new SystemUser()));
        systemUserService.listByIds(list);
    }

    @Test
    void insertUser() {
        String[] phone = {"137", "131", "156", "151", "180", "132", "133", "186", "192"};
        for (int i = 0; i < 20; i++) {
            SystemUser user = new SystemUser();
            user.setRealName(UUID.randomUUID().toString().replace("-", "").substring(0, 7));
            user.setPassword("123456");
            user.setLoginName("user" + (int) (Math.random() * 200000));
            user.setCellphone(phone[(int) (Math.random() * (phone.length))] + (int) ((Math.random() * 9 + 1) * 10000000));
            user.setEmail(user.getRealName() + "@qq.com");
            systemUserService.saveOrUpdate(user);
        }
    }

    @Test
    void testRoleMapper() {
        SystemRole role = systemRoleMapper.query(3L).orElseGet(SystemRole::new);
        System.out.println();
    }


    @Test
    void testClass() throws NoSuchMethodException {
        Class<?> clas = applicationContext.getBean("systemFunctionController").getClass();
        Method method = clas.getDeclaredMethod("list");
        if (method.isAnnotationPresent(RequirePermission.class)) {
            RequirePermission hasPermission = method.getAnnotation(RequirePermission.class);
            System.out.println(Arrays.toString(hasPermission.permissions()));
            System.out.println(hasPermission.matchType() == Match.HAS_ANY);
        }
    }


}
