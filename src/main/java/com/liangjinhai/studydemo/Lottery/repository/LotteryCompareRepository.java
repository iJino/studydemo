package com.liangjinhai.studydemo.Lottery.repository;

import com.liangjinhai.studydemo.Lottery.domin.LotteryCompare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface LotteryCompareRepository extends JpaRepository<LotteryCompare,Integer>, JpaSpecificationExecutor<LotteryCompare> {

    @Query("select l from LotteryCompare l where l.time = ?1")
    LotteryCompare findByTime(String time);
}
