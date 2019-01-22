package com.liangjinhai.studydemo.Lottery.service.impl;

import com.liangjinhai.studydemo.Lottery.domin.LotteryCompare;
import com.liangjinhai.studydemo.Lottery.repository.LotteryCompareRepository;
import com.liangjinhai.studydemo.Lottery.service.LotteryCompareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LotteryCompareServiceImpl implements LotteryCompareService {
    @Resource
    private LotteryCompareRepository lotteryCompareRepository;

    @Override
    public LotteryCompare save(LotteryCompare lotteryCompare){
        return lotteryCompareRepository.save(lotteryCompare);
    }

    @Override
    public List<LotteryCompare> findAll() {
        return lotteryCompareRepository.findAll();
    }
}
