package com.shj.eids.dao;

import com.shj.eids.domain.AidInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: AidInformationMapper
 * @Description:
 * @Author: ShangJin
 * @Create: 2020-03-05 11:52
 **/
@Mapper
@Repository
public interface AidInformationMapper {
    public void addAidInformation(AidInformation info);
    public void deleteAidInformation(AidInformation info);
    public void updateAidInformation(AidInformation info);
    public AidInformation getInformationById(String id);
    public List<AidInformation> getInformation(@Param("start") Integer start, @Param("length") Integer length, @Param("content") String content);
    public Integer getCount(String content);
}