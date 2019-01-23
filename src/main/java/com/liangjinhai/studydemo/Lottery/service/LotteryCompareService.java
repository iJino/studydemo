package com.liangjinhai.studydemo.Lottery.service;

import com.liangjinhai.studydemo.Lottery.domin.LotteryCompare;

import java.util.List;

public interface LotteryCompareService {
    LotteryCompare save(LotteryCompare lotteryCompare);

    List<LotteryCompare> findAll();

    LotteryCompare findByTime(String time);

    Boolean exist(String time);
}
