package cn.iocoder.yudao.module.bookstore.config;

import cn.iocoder.yudao.framework.datapermission.core.rule.DataPermissionRule;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import com.google.common.collect.Sets;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class XhsdDataPermissionRule implements DataPermissionRule {

    @Override
    public Set<String> getTableNames() {
        return Sets.newHashSet(
                "system_users",
                "infra_book_chapter",
                "infra_book_info",
                "infra_book_qtcode_info",
                "infra_book_qtcode_item",
                "infra_book_qtcode_source",
                "infra_source_info");
    }

    @Override
    public Expression getExpression(String tableName, Alias tableAlias) {
        Long xtenantId = WebFrameworkUtils.getXTenantId();
        if (xtenantId != null) {
            return new EqualsTo(MyBatisUtils.buildColumn(tableName, tableAlias, "dept_id"), new LongValue(xtenantId));
        }
        return null;
    }
}
