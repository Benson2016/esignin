package com.benson.esignin.web.controller;

import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.web.dao.ISignInRecordDao;
import com.benson.esignin.web.domain.entity.SignInType;
import com.benson.esignin.web.domain.vo.SignInRecordStatisticsVo;
import com.benson.esignin.web.domain.vo.StatisticsQuery;
import com.benson.esignin.web.service.ISignInRecordService;
import com.benson.esignin.web.service.ISignInTypeService;
import com.benson.esignin.web.service.IUserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String user(Model model, HttpServletRequest request) {

        String year = request.getParameter("year");
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

            model.addAttribute("data1", JsonUtil.bean2Json(data1));
            model.addAttribute("data2", JsonUtil.bean2Json(data2));
            model.addAttribute("data3", JsonUtil.bean2Json(data3));

        } catch (Exception e) {
            logger.error("用户注册统计异常:", e);
        }

        return "admin/statistics_user";
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

        try {
            List<SignInRecordStatisticsVo> list = signInRecordService.statisticsSignIn();

            List<SignInType> types = signInTypeService.findAll();

            int[] data1 = new int[types.size()];
            String[] names = new String[types.size()];

            Integer counts = 0;
            SignInType signInType = null;
            for (int i=0; i<types.size(); i++) {
                counts = 0;
                signInType = types.get(i);
                for (SignInRecordStatisticsVo vo : list) {
                    if (signInType.getId()==vo.getSignInType()) {
                        counts = vo.getCounts();
                        break;
                    }
                }
                data1[i] = counts;
                names[i] = signInType.getTypeName();
            }

            model.addAttribute("data1", JsonUtil.bean2Json(data1));
            model.addAttribute("names", JsonUtil.bean2Json(names));

        } catch (Exception e) {
            logger.error("签到统计异常:", e);
        }

        return "admin/statistics_signin";
    }

}
