package com.benson.esignin.web.controller;

import com.benson.esignin.common.enums.StateResponse;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.annotation.SysControllerLog;
import com.benson.esignin.web.dao.ISignInRecordDao;
import com.benson.esignin.web.domain.entity.SignInType;
import com.benson.esignin.web.domain.vo.*;
import com.benson.esignin.web.service.ISignInRecordService;
import com.benson.esignin.web.service.ISignInTypeService;
import com.benson.esignin.web.service.IUserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 统计业务控制层
 *
 * @author: Benson Xu
 * @date: 2016年06月16日 00:51
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private ISignInTypeService signInTypeService;

    @Autowired
    private ISignInRecordService signInRecordService;


    /**
     * 统计用户
     * @param model
     * @return
     */
    @RequestMapping(value = "/user")
    public String user(Model model) {
        return "admin/statistics_user";
    }

    @RequestMapping(value = "/getUserData", method = RequestMethod.POST)
    @SysControllerLog(content = "统计年度用户注册情况.")
    @ResponseBody
    public Object getUserStaticData(String year) {
        logger.info("Enter getUserStaticData Method. ");
        UserStatisticsResponse response = null;
        if (CommonUtil.isNull(year)) {
            year = DateUtil.getCurrDate("yyyy");
        }

        try {
            Map<String, Integer> map1 = userInfoService.statisticsRegister(new StatisticsQuery(year, 1));
            Map<String, Integer> map2 = userInfoService.statisticsRegister(new StatisticsQuery(year, 2));
            Map<String, Integer> map3 = userInfoService.statisticsRegister(new StatisticsQuery(year, 3));

            int[] data1 = new int[12];
            int[] data2 = new int[12];
            int[] data3 = new int[12];

            List<String> months = getMonths(year);
            for (int i=0; i<months.size(); i++) {
                data1[i] = map1.get(months.get(i));
                data2[i] = map2.get(months.get(i));
                data3[i] = map3.get(months.get(i));
            }

            response = new UserStatisticsResponse(StateResponse.SUCCESS);
            response.setData1(data1);
            response.setData2(data2);
            response.setData3(data3);

        } catch (Exception e) {
            response = new UserStatisticsResponse(StateResponse.ERROR_SYS);
            logger.error("统计年度用户注册情况时异常:", e);
        } finally {
            logger.info("Exit getUserStaticData Method. ");
        }
        return JsonUtil.toJson(response);
    }

    /**
     * 获取指定年份的12个月份
     * @param year
     * @return
     */
    private List<String> getMonths(String year) {
        List<String> months = new ArrayList<String>(12);
        months.add(year+"-01");
        months.add(year+"-02");
        months.add(year+"-03");
        months.add(year+"-04");
        months.add(year+"-05");
        months.add(year+"-06");
        months.add(year+"-07");
        months.add(year+"-08");
        months.add(year+"-09");
        months.add(year+"-10");
        months.add(year+"-11");
        months.add(year+"-12");
        return months;
    }


    /**
     * 签到统计
     * @param model
     * @return
     */
    @RequestMapping(value = "/signIn")
    public String signIn(Model model) {
        return "admin/statistics_signin";
    }

    @RequestMapping(value = "/getSignInStaticData", method = RequestMethod.POST)
    @SysControllerLog(content = "统计年度签到情况.")
    @ResponseBody
    public Object getSignInStaticData(String year) {
        logger.info("Enter getSignInStaticData Method. ");
        SignInStatisticsResponse response = null;
        if (CommonUtil.isNull(year)) {
            year = DateUtil.getCurrDate("yyyy");
        }

        try {
            List<SignInRecordStatisticsVo> list = signInRecordService.statisticsSignIn(year);

            List<SignInType> types = signInTypeService.findAll();

            int[] data = new int[types.size()];
            String[] names = new String[types.size()];

            int size = 0;
            int counts = 0;
            SignInType signInType = null;
            for (int i=0; i<types.size(); i++) {
                counts = 0;
                signInType = types.get(i);
                for (SignInRecordStatisticsVo vo : list) {
                    if (signInType.getId()==vo.getSignInType()) {
                        counts = vo.getCounts();
                        ++size;
                        break;
                    }
                }
                data[i] = counts;
                names[i] = signInType.getTypeName();
            }

            response = new SignInStatisticsResponse(StateResponse.SUCCESS);
            response.setData(data);
            response.setNames(names);
            response.setSize(size);

        } catch (Exception e) {
            response = new SignInStatisticsResponse(StateResponse.ERROR_SYS);
            logger.error("统计年度签到情况时发生异常:", e);
        } finally {
            logger.info("Exit getSignInStaticData Method. ");
        }
        return JsonUtil.toJson(response);
    }


}
