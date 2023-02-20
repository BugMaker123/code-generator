package ${package.Controller};

import com.neko.seed.base.entity.Result;
import ${package.Entity}.${entity};
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

/**
* ${table.comment!} controller
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
@Api(value="${table.controllerName}",tags={"${table.comment!}操作接口"})
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Autowired
    private ${table.serviceName} ${package.ModuleName}Service;

    /**
    * 列表-分页查询
    * @param ${package.ModuleName}
    * @return
    */
    @ApiOperation(value="列表-分页查询")
    @PostMapping("/page")
    public Result page(${entity} ${package.ModuleName}, int pageNum, int pageSize) {
        List<${entity}> ${package.ModuleName}List = ${package.ModuleName}Service.get(${package.ModuleName}, pageNum, pageSize);
        PageInfo<${entity}> pageInfo = new PageInfo<>(${package.ModuleName}List);
        return new Result().success(pageInfo);
    }
    /**
    * 列表-不分页
    * @param ${package.ModuleName}
    * @return
    */
    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public Result list(@RequestBody ${entity} ${package.ModuleName}) {
        List<${entity}> ${package.ModuleName}List = ${package.ModuleName}Service.get(${package.ModuleName});
        return new Result().success(${package.ModuleName}List);
    }

    /**
    * 新增
    * @param  ${package.ModuleName}
    * @return
    */
    @ApiOperation(value="新增")
    @PostMapping("/add")
    public Result add(@RequestBody ${entity} ${package.ModuleName}) {
        ${package.ModuleName}Service.save(${package.ModuleName});
        return new Result().success();
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @ApiOperation(value="删除")
    @GetMapping("/deleteById")
    public Result deleteById(@RequestParam(value = "id",required = false)Long id) {
        ${package.ModuleName}Service.removeById(id);
        return new Result().success();
    }
    /**
    * 修改
    * @param  ${package.ModuleName}
    * @return
    */
    @ApiOperation(value="修改")
    @PostMapping("/update")
    public Result updateById(@RequestBody ${entity} ${package.ModuleName}) {
        ${package.ModuleName}Service.updateById(${package.ModuleName});
        return new Result().success();
    }
}
</#if>
