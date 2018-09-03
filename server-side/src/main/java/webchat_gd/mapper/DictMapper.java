package webchat_gd.mapper;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.entity.Dict;

@Mapper
public interface DictMapper {
    int insert(Dict record);

    int insertSelective(Dict record);
}