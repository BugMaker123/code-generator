package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import java.util.List;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

/**
* ${table.comment!}serviceImpl
* @author ${author}
* @since ${date}
*/
@Service
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

    }
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${package.ModuleName}Mapper;

    @Override
    public ${entity} getById(String ${package.ModuleName}_id) {
        return ${package.ModuleName}Mapper.getById(${package.ModuleName}_id);
    }

    @Override
    public List<${entity}> get(${entity} ${package.ModuleName}) {
        List<${entity}> ${package.ModuleName}List = ${package.ModuleName}Mapper.get(${package.ModuleName});
        return ${package.ModuleName}List;
    }

    @Override
    public List<${entity}> get(${entity} ${package.ModuleName}, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<${entity}> ${package.ModuleName}List = ${package.ModuleName}Mapper.get(${package.ModuleName});
        return ${package.ModuleName}List;
    }
}
</#if>

