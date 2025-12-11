<#-- このテンプレートに対応するデータモデルのクラスは org.seasar.doma.extension.gen.EntityDesc です -->
<#function convertDataType dataType>
    <#local result = dataType?replace("Byte", "Integer")>
    <#local result = result?replace("int", "Integer")>
    <#local result = result?replace("Short", "Integer")>
    <#return result>
</#function>

<#import "./lib.ftl" as lib>
<#if lib.copyright??>
/* ${lib.copyright} */
</#if>
<#if packageName??>
package ${packageName};
</#if>



import org.seasar.doma.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ${comment}
 *
<#if lib.since??>
 * @since ${lib.since}
</#if>
<#if lib.author??>
 * @author ${lib.author}
</#if>
 */
@Entity<#if useListener || namingType != "NONE">(</#if><#if useListener>listener = ${listenerClassSimpleName}.class</#if><#if namingType != "NONE"><#if useListener>, </#if>naming = ${namingType.referenceName}</#if><#if useListener || namingType != "NONE">)</#if>
<#if showCatalogName && catalogName?? || showSchemaName && schemaName?? || showTableName && tableName??>
@Table(<#if showCatalogName && catalogName??>catalog = "${catalogName}"</#if><#if showSchemaName && schemaName??><#if showCatalogName && catalogName??>, </#if>schema = "${schemaName}"</#if><#if showTableName><#if showCatalogName && catalogName?? || showSchemaName && schemaName??>, </#if>name = "${tableName}"</#if>)
</#if>
public class <#if entityPrefix??>${entityPrefix}</#if>${simpleName}<#if entitySuffix??>${entitySuffix}</#if> extends FsEntity
{
<#list ownEntityPropertyDescs as property>
    <#if property.name != "isDeleted"
    &&  property.name != "modifiedBy"
    &&  property.name != "modifiedAt"
    &&  property.name != "createdAt"
    &&  property.name != "createdBy" >
  <#if showDbComment && property.comment??>
    /** ${property.comment} */
  <#else>
    /** */
  </#if>
  <#if property.id>
    <#if property.version>
    @Version
    </#if>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    <#if !useAccessor>public </#if>${property.propertyClassSimpleName} ${property.name};
  <#else>
    <#if property.version>
    @Version
    </#if>
    <#if property.showColumnName && property.columnName??>
    @Column(name = "${property.columnName}")
    </#if>
    <#if !useAccessor>public </#if>${property.propertyClassSimpleName} ${property.name};
  </#if>
      </#if>
</#list>
<#if useAccessor>
    <#list ownEntityPropertyDescs as property>
        <#if property.name != "isDeleted"
        &&  property.name != "modifiedBy"
        &&  property.name != "modifiedAt"
        &&  property.name != "createdAt"
        &&  property.name != "createdBy" >

    /**
     * Returns the ${property.name}.
     *
     * @return the ${property.name}
     */
    public ${convertDataType(property.propertyClassSimpleName)} get${property.name?cap_first}() {
        return ${property.name};
    }

    /**
     * Sets the ${property.name}.
     *
     * @param ${property.name} the ${property.name}
     */
    public void set${property.name?cap_first}(${convertDataType(property.propertyClassSimpleName)} ${property.name}) {
        this.${property.name} = ${property.name};
    }
        </#if>
    </#list>
</#if>
}
