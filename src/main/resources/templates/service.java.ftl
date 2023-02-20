package ${package.Service};

import java.util.List;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* ${table.comment!}service
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

     ${entity} getById(String ${package.ModuleName}_id);

     List<${entity}> get(${entity} ${package.ModuleName});

     List<${entity}> get(${entity} ${package.ModuleName}, int pageNum, int pageSize);
}
</#if>

