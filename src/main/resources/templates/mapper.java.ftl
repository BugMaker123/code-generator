package ${package.Mapper};

import java.util.List;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;
/**
* ${table.comment!} Mapper
* @author ${author}
* @since ${date}
*/
@Mapper
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

     ${entity} getById(String ${package.ModuleName}_id);

     List<${entity}> get(${entity} ${package.ModuleName});
}
</#if>

