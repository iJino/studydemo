package com.liangjinhai.studydemo.Lottery.repository;

import com.liangjinhai.studydemo.Lottery.domin.LotteryCompare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LotteryCompareRepository extends JpaRepository<LotteryCompare,Integer>, JpaSpecificationExecutor<LotteryCompare> {
}
