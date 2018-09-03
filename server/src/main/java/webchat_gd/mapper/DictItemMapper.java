package webchat_gd.mapper;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.entity.DictItem;

@Mapper
public interface DictItemMapper {
    int insert(DictItem record);

    int insertSelective(DictItem record);
}