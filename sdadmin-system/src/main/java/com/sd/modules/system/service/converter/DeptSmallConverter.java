package com.sd.modules.system.service.converter;

import com.sd.base.BaseConverter;
import com.sd.modules.system.domain.Dept;
import com.sd.modules.system.service.dto.DeptSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptSmallConverter extends BaseConverter<DeptSmallDto, Dept> {

}