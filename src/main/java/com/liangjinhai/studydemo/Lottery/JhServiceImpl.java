package com.liangjinhai.studydemo.Lottery;

import com.liangjinhai.studydemo.common.util.OKHttpUtil;
import org.springframework.stereotype.Service;

/**
 * @author Jin
 * @Title: JhServiceImpl
 * @date 19-1-21下午8:18
 */
@Service
public class JhServiceImpl implements JhService {
    @Override
    public String queryLottery(String lotteryId) {
        String url = JhUtil.lottery.QUERY + "?key=" + JhUtil.lottery.KEY+"&lottery_id="+lotteryId;
        String result = OKHttpUtil.httpGet(url);
        return result;
    }
}
